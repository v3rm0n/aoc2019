package advent

class Advent2(
    private val intcodes: List<Int> = readFile("/advent2")
        .flatMap { it.split(',').map { it.toInt() } }.toMutableList().also {
            it[1] = 12
            it[2] = 2
        }
) : Advent {

    override fun firstTask() = calculate(intcodes.toMutableList()).toString()

    override fun secondTask() = findNounAndVerb(intcodes.toMutableList()).toString()

    private fun findNounAndVerb(intcodes: MutableList<Int>): Int? {
        for (i in (0..100)) {
            for (j in (0..100)) {
                if (calculate(intcodes.toMutableList(), i, j) == 19690720) return 100 * i + j
            }
        }
        return null
    }

    private fun calculate(intcodes: MutableList<Int>, first: Int = intcodes[1], second: Int = intcodes[2]): Int {
        intcodes[1] = first
        intcodes[2] = second
        var i = 0
        loop@ while (i < intcodes.size) {
            when (intcodes[i]) {
                1 -> intcodes[intcodes[i + 3]] = intcodes[intcodes[i + 1]] + intcodes[intcodes[i + 2]]
                2 -> intcodes[intcodes[i + 3]] = intcodes[intcodes[i + 1]] * intcodes[intcodes[i + 2]]
                99 -> break@loop
            }
            i += 4
        }
        return intcodes[0]
    }
}


