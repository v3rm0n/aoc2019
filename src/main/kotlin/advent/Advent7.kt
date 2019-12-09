package advent

import intcode.Calculator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class Advent7 : Advent {
    override fun firstTask(input: List<String>) = phases(input)
    override fun secondTask(input: List<String>): Long {
        val intcodes = input.flatMap { it.split(',').map(String::toLong) }
        return forUniqueValues(5..9L) { a, b, c, d, e ->
            runBlocking {
                val firstChannel = Channel<Long>()
                val first = Calculator(intcodes, a).calculate(firstChannel)
                val second = Calculator(intcodes, b).calculate(first)
                val third = Calculator(intcodes, c).calculate(second)
                val fourth = Calculator(intcodes, d).calculate(third)
                val fifth = Calculator(intcodes, e).calculate(fourth)
                launch {
                    firstChannel.send(0L)
                }
                var lastValue = 0L
                for (fifthValue in fifth) {
                    lastValue = fifthValue
                    if (!fifth.isClosedForReceive) {
                        launch {
                            firstChannel.send(fifthValue)
                        }
                    }
                }
                coroutineContext.cancelChildren()
                lastValue
            }
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
        println(largestValue)
        return largestValue
    }
}

