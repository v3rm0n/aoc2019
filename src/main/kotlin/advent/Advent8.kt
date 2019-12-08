package advent

class Advent8(private val width: Int = 25, private val height: Int = 6) : Advent {
    override fun firstTask(input: List<String>): Int {
        val layers = layers(input.first().map { it.toString().toInt() })
        val leastZeroes = layers.fold(listOf<Int>()) { acc, a ->
            when {
                acc.isEmpty() -> a
                countZeroes(acc) < countZeroes(a) -> acc
                else -> a
            }
        }
        return leastZeroes.count { it == 1 } * leastZeroes.count { it == 2 }
    }

    private fun countZeroes(list: List<Int>) = list.count { it == 0 }

    private fun layers(input: List<Int>) = input.chunked(width * height)

    override fun secondTask(input: List<String>): Any {
        val layers = layers(input.first().map { it.toString().toInt() })
        return layers.reduce(this::combine).chunked(width)
    }

    private fun combine(bottom: List<Int>, next: List<Int>): List<Int> {
        return bottom.mapIndexed { index, bottomValue ->
            val nextValue = next[index]
            if (bottomValue == 2) nextValue
            else bottomValue
        }
    }
}
