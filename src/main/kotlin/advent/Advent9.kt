package advent

import intcode.Calculator

class Advent9 : Advent {
    override fun firstTask(input: List<String>): List<Long> {
        val intcodes = input.flatMap { it.split(',').map(String::toLong) }
        return Calculator(intcodes).calculate(listOf(1L))
    }

    override fun secondTask(input: List<String>): Any {
        val intcodes = input.flatMap { it.split(',').map(String::toLong) }
        return Calculator(intcodes).calculate(listOf(2L))
    }
}
