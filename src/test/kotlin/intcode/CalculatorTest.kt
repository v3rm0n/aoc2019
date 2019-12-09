package intcode

import org.junit.Test
import java.util.concurrent.LinkedBlockingQueue

class CalculatorTest {
    @Test
    fun test() {
        val input = listOf("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5")
        val intcodes = input.flatMap { it.split(',').map(String::toLong) }
        val firstChannel = LinkedBlockingQueue<Long>()
        val firstCalculator = Calculator(intcodes, 9)
        val first = firstCalculator.calculate(firstChannel)
        val second = Calculator(intcodes, 8).calculate(first)
        val third = Calculator(intcodes, 7).calculate(second)
        val fourth = Calculator(intcodes, 6).calculate(third)
        val fifth = Calculator(intcodes, 5).calculate(fourth)
        firstChannel.offer(0)
        while (!firstCalculator.halted) {
            val value = fifth.take()
            firstChannel.offer(value)
        }
    }
}
