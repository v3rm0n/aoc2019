package intcode

import java.util.concurrent.BlockingQueue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class Calculator(private val codes: List<Long>, private val phase: Long? = null) {

    private val executor = Executors.newSingleThreadExecutor()
    private val haltingLatch = CountDownLatch(1)
    var halted = false

    private fun isImmediate(opcode: String, param: Int) =
        opcode.reversed().drop(1)[param].toString().toInt() == 1

    private fun isRelative(opcode: String, param: Int) = opcode.reversed().drop(1)[param].toString().toInt() == 2

    fun calculate(inputs: List<Long>): List<Long> {
        val inputChannel = LinkedBlockingQueue<Long>(inputs)
        return calculate(inputChannel).let {
            haltingLatch.await()
            it.toList()
        }
    }

    fun calculate(input: BlockingQueue<Long>): BlockingQueue<Long> {
        val output = LinkedBlockingQueue<Long>()
        executor.execute {
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
                when (opcode.drop(3).toInt()) {
                    1 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        setValue(opcode, intcodes[i + 3]!!, param1 + param2, 3)
                        i += 4
                    }
                    2 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        setValue(opcode, intcodes[i + 3]!!, param1 * param2, 3)
                        i += 4
                    }
                    3 -> {
                        val value = if (phase != null && !phaseUsed) {
                            phaseUsed = true
                            phase
                        } else {
                            input.take()
                        }
                        setValue(opcode, intcodes[i + 1]!!, value, 1)
                        i += 2
                    }
                    4 -> {
                        val param1 = paramValue(opcode, i, 1)
                        output.put(param1)
                        i += 2
                    }
                    5 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        if (param1 != 0L) {
                            i = param2
                        } else {
                            i += 3
                        }
                    }
                    6 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        if (param1 == 0L) {
                            i = param2
                        } else {
                            i += 3
                        }
                    }
                    7 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        setValue(opcode, intcodes[i + 3]!!, if (param1 < param2) 1L else 0L, 3)
                        i += 4
                    }
                    8 -> {
                        val param1 = paramValue(opcode, i, 1)
                        val param2 = paramValue(opcode, i, 2)
                        setValue(opcode, intcodes[i + 3]!!, if (param1 == param2) 1L else 0L, 3)
                        i += 4
                    }
                    9 -> {
                        val param1 = paramValue(opcode, i, 1)
                        relativeBase += param1
                        i += 2
                    }
                    99 -> {
                        halted = true
                        haltingLatch.countDown()
                        break@loop
                    }

                }
            }
        }
        return output
    }
}
