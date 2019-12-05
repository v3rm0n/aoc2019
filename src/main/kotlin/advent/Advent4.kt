package advent

class Advent4(private val range: IntRange = (347312..805915)) : Advent {

    override fun firstTask() = range.fold(0)
    { acc, it ->
        if (hasAdjacentDigits(it.toString()) && isIncreasing(
                it.toString()
            )
        ) acc + 1 else acc
    }

    override fun secondTask() = range.fold(0)
    { acc, it ->
        if (isIncreasing(it.toString()) && hasLengthOfAdjacentDigitsTwo(
                it.toString()
            )
        ) acc + 1 else acc
    }

    private fun isIncreasing(s: String) =
        s.foldIndexed(true) { i, result, c -> result && (i == 0 || c.toInt() >= s[i - 1].toInt()) }

    private fun hasAdjacentDigits(s: String) =
        s.foldIndexed(false) { i, result, c -> result || (i == 0 || c == s[i - 1]) }

    private fun hasLengthOfAdjacentDigitsTwo(s: String) =
        s.foldIndexed(0) { i, result, c ->
            when {
                i == 0 -> 1
                c == s[i - 1] -> result + 1
                result == 2 -> return true
                else -> 1
            }
        } == 2
}





