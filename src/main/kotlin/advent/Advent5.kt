package advent

class Advent5(
    private val firstInput: Int = 1,
    private val secondInput: Int = 5
) : Advent {

    override fun firstTask(input: List<String>) =
        calculate(input.flatMap { it.split(',').map(String::toInt) }.toMutableList(), firstInput)

    override fun secondTask(input: List<String>) =
        calculate(input.flatMap { it.split(',').map(String::toInt) }.toMutableList(), secondInput)

    private fun isImmediate(opcode: String, param: Int) = opcode.reversed().drop(1)[param].toString().toInt() == 1

    private fun calculate(intcodes: MutableList<Int>, input: Int): List<Int> {
        debugPrint("Initial intcodes: $intcodes")

        fun paramValue(opcode: String, i: Int, param: Int) =
            if (isImmediate(opcode, param)) intcodes[i + param] else intcodes[intcodes[i + param]]

        var i = 0
        val output = mutableListOf<Int>()
        loop@ while (i < intcodes.size) {
            val opcode = intcodes[i].toString().padStart(5, '0')
            debugPrint("Opcode: $opcode")
            when (opcode.drop(3).toInt()) {
                1 -> {
                    val param1 = paramValue(opcode, i, 1)
                    val param2 = paramValue(opcode, i, 2)
                    debugPrint("Setting value on index ${intcodes[i + 3]} to $param1+$param2")
                    intcodes[intcodes[i + 3]] = param1 + param2
                    i += 4
                }
                2 -> {
                    val param1 = paramValue(opcode, i, 1)
                    val param2 = paramValue(opcode, i, 2)
                    debugPrint("Setting value on index ${intcodes[i + 3]} to $param1*$param2")
                    intcodes[intcodes[i + 3]] = param1 * param2
                    i += 4
                }
                3 -> {
                    debugPrint("Setting value on index ${intcodes[i + 1]} to $input")
                    intcodes[intcodes[i + 1]] = input
                    i += 2
                }
                4 -> {
                    val param1 = paramValue(opcode, i, 1)
                    debugPrint("Outputting value $param1")
                    output.add(param1)
                    i += 2
                }
                5 -> {
                    val param1 = paramValue(opcode, i, 1)
                    val param2 = paramValue(opcode, i, 2)
                    debugPrint("Jumping to $param2? ${param1 != 0}")
                    if (param1 != 0) {
                        i = param2
                    } else {
                        i += 3
                    }
                }
                6 -> {
                    val param1 = paramValue(opcode, i, 1)
                    val param2 = paramValue(opcode, i, 2)
                    debugPrint("Jumping to ${param2}? ${param1 == 0}")
                    if (param1 == 0) {
                        i = param2
                    } else {
                        i += 3
                    }
                }
                7 -> {
                    val param1 = paramValue(opcode, i, 1)
                    val param2 = paramValue(opcode, i, 2)
                    debugPrint("$param1 < $param2? ${param1 < param2}")
                    intcodes[intcodes[i + 3]] = if (param1 < param2) 1 else 0
                    i += 4
                }
                8 -> {
                    val param1 = paramValue(opcode, i, 1)
                    val param2 = paramValue(opcode, i, 2)
                    debugPrint("$param1 == $param2? ${param1 == param2}")
                    intcodes[intcodes[i + 3]] = if (param1 == param2) 1 else 0
                    i += 4
                }
                99 -> break@loop
            }
            debugPrint("Intcodes: $intcodes")
        }
        debugPrint("Final intcodes: $intcodes")
        if (output.isEmpty()) {
            output.add(intcodes[0])
        }
        return output
    }
}
