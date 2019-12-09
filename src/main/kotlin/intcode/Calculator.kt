package intcode

import advent.debugPrint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class Calculator(private val codes: List<Long>, private val phase: Long? = null) {

    private fun isImmediate(opcode: String, param: Int) =
        opcode.reversed().drop(1)[param].toString().toInt() == 1

    private fun isRelative(opcode: String, param: Int) = opcode.reversed().drop(1)[param].toString().toInt() == 2

    fun calculate(inputs: List<Long>): List<Long> {
        return runBlocking {
            val inputChannel = Channel<Long>()
            inputs.map { launch { inputChannel.send(it) } }
            calculate(inputChannel).toList().also {
                coroutineContext.cancelChildren()
            }
        }
    }

    fun calculate(input: Channel<Long>): Channel<Long> {
        val output = Channel<Long>()
        GlobalScope.launch {
            val intcodes = codes.mapIndexed { index, i -> index.toLong() to i }
                .toMap().toMutableMap()

            var relativeBase = 0L

            fun getValue(i: Long): Long {
                return intcodes[i] ?: 0L
            }

            fun setValue(opcode: String, i: Long, value: Long, param: Int) {
                when {
                    isRelative(opcode, param) -> intcodes[relativeBase + i] = value
                    else -> intcodes[i] = value
                }
            }

            fun paramValue(opcode: String, i: Long, param: Int) =
                when {
                    isImmediate(opcode, param) -> getValue(i + param)
                    isRelative(opcode, param) -> getValue(relativeBase + intcodes[i + param]!!)
                    else -> getValue(intcodes[i + param]!!)
                }

            var i = 0L
            var phaseUsed = false
            loop@ while (true) {
                val opcode = intcodes[i].toString().padStart(5, '0')
                debugPrint("Opcode: $opcode")
                when (opcode.drop(3).toInt()) {
                    1 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        debugPrint("Setting value on index ${intcodes[i + 3]} to $param1+$param2")
                        setValue(opcode, intcodes[i + 3]!!, param1 + param2, 3)
                        i += 4
                    }
                    2 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        debugPrint("Setting value on index ${intcodes[i + 3]} to $param1*$param2")
                        setValue(opcode, intcodes[i + 3]!!, param1 * param2, 3)
                        i += 4
                    }
                    3 -> {
                        val value = if (phase != null && !phaseUsed) {
                            debugPrint("Using phase $phase")
                            phaseUsed = true
                            phase
                        } else {
                            input.receive()
                        }
                        debugPrint("Setting value on index ${intcodes[i + 1]} to $value")
                        setValue(opcode, intcodes[i + 1]!!, value, 1)
                        i += 2
                    }
                    4 -> {
                        val param1 = paramValue(opcode, i, 1)
                        debugPrint("Outputting value $param1")
                        output.send(param1)
                        i += 2
                    }
                    5 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        debugPrint("Jumping to $param2? ${param1 != 0L}")
                        if (param1 != 0L) {
                            i = param2
                        } else {
                            i += 3
                        }
                    }
                    6 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        debugPrint("Jumping to ${param2}? ${param1 == 0L}")
                        if (param1 == 0L) {
                            i = param2
                        } else {
                            i += 3
                        }
                    }
                    7 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        debugPrint("$param1 < $param2? ${param1 < param2}")
                        setValue(opcode, intcodes[i + 3]!!, if (param1 < param2) 1L else 0L, 3)
                        i += 4
                    }
                    8 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        debugPrint("$param1 == $param2? ${param1 == param2}")
                        setValue(opcode, intcodes[i + 3]!!, if (param1 == param2) 1L else 0L, 3)
                        i += 4
                    }
                    9 -> {
                        val param1 = paramValue(opcode, i, 1)
                        debugPrint("Setting relative base to $param1")
                        relativeBase += param1
                        i += 2
                    }
                    99 -> {
                        debugPrint("Halting")
                        output.close()
                        break@loop
                    }

                }
            }
        }
        return output
    }
}
