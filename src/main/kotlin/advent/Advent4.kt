package advent

class Advent4 : Advent {

    private fun range(input: List<String>): IntRange = input.first().let {
        val (start, end) = it.split("..").map(String::toInt)
        return start..end
    }

    override fun firstTask(input: List<String>) =
        range(input).count { hasAdjacentDigits(it.toString()) && isIncreasing(it.toString()) }

    override fun secondTask(input: List<String>) =
        range(input).count { isIncreasing(it.toString()) && hasLengthOfAdjacentDigitsTwo(it.toString()) }

    private fun isIncreasing(s: String) = s.zipWithNext().all { (prev, next) -> prev.toInt() <= next.toInt() }

    private fun hasAdjacentDigits(s: String) = s.zipWithNext().any { (prev, next) -> prev == next }

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





