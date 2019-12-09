package advent

import intcode.Calculator
import java.util.concurrent.LinkedBlockingQueue

class Advent7 : Advent {
    override fun firstTask(input: List<String>) = phases(input)
    override fun secondTask(input: List<String>): Long {
        val intcodes = input.flatMap { it.split(',').map(String::toLong) }
        return forUniqueValues(5..9L) { a, b, c, d, e ->
            val firstChannel = LinkedBlockingQueue<Long>(1)
            firstChannel.put(0L)
            val firstCalculator = Calculator(intcodes, a)
            val first = firstCalculator.calculate(firstChannel)
            val second = Calculator(intcodes, b).calculate(first)
            val third = Calculator(intcodes, c).calculate(second)
            val fourth = Calculator(intcodes, d).calculate(third)
            val fifth = Calculator(intcodes, e).calculate(fourth)
            var lastValue = 0L
            while (!firstCalculator.halted) {
                lastValue = fifth.take()
                firstChannel.put(lastValue)
            }
            lastValue
        }
    }

    private fun phases(input: List<String>): Long {
        val intcodes = input.flatMap { it.split(',').map(String::toLong) }
        return forUniqueValues(0..4L) { a, b, c, d, e ->
            val first = Calculator(intcodes).calculate(listOf(a, 0)).first()
            val second = Calculator(intcodes).calculate(listOf(b, first)).first()
            val third = Calculator(intcodes).calculate(listOf(c, second)).first()
            val fourth = Calculator(intcodes).calculate(listOf(d, third)).first()
            Calculator(intcodes).calculate(listOf(e, fourth)).first()
        }
    }

    private fun forUniqueValues(range: LongRange, func: (Long, Long, Long, Long, Long) -> Long): Long {
        var largestValue = 0L
        for (a in range) {
            for (b in range) {
                if (a != b) {
                    for (c in range) {
                        if (c != b && c != a) {
                            for (d in range) {
                                if (d != c && d != b && d != a) {
                                    for (e in range) {
                                        if (e != d && e != b && e != c && e != a) {
                                            val newValue = func(a, b, c, d, e)
                                            if (newValue > largestValue) {
                                                largestValue = newValue
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return largestValue
    }
}

