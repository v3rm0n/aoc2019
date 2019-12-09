package advent

import intcode.Calculator

class Advent5(
    private val firstInput: Long? = 1L,
    private val secondInput: Long? = 5L
) : Advent {
    override fun firstTask(input: List<String>) = calculate(input, firstInput)

    override fun secondTask(input: List<String>) = calculate(input, secondInput)

    private fun calculate(input: List<String>, inputValue: Long?): List<Long> {
        val calculator = Calculator(input.flatMap { it.split(',').map(String::toLong) })
        return calculator.calculate(if (inputValue != null) listOf(inputValue) else emptyList())
    }
}
