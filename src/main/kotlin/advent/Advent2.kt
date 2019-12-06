package advent

class Advent2(private val inputs: List<Int> = listOf(12, 2)) : Advent {

    override fun firstTask(input: List<String>) =
        calculate(input.flatMap { it.split(',').map(String::toInt) }.toMutableList(), inputs)

    override fun secondTask(input: List<String>) =
        findNounAndVerb(input.flatMap { it.split(',').map(String::toInt) }.toMutableList())

    private fun findNounAndVerb(intcodes: MutableList<Int>): Int {
        for (i in (0..100)) {
            for (j in (0..100)) {
                if (calculate(intcodes.toMutableList(), listOf(i, j)) == 19690720) return 100 * i + j
            }
        }
        return -1
    }

    private fun calculate(intcodes: MutableList<Int>, inputs: List<Int>): Int {
        if (inputs.isNotEmpty()) {
            val (first, second) = inputs
            intcodes[1] = first
            intcodes[2] = second
        }
        loop@ for (i in (0 until intcodes.size) step 4) {
            when (intcodes[i]) {
                1 -> intcodes[intcodes[i + 3]] = intcodes[intcodes[i + 1]] + intcodes[intcodes[i + 2]]
                2 -> intcodes[intcodes[i + 3]] = intcodes[intcodes[i + 1]] * intcodes[intcodes[i + 2]]
                99 -> break@loop
            }
        }
        return intcodes[0]
    }
}


