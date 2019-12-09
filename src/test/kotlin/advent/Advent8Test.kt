package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent8Test {
    @Test
    fun `calculates digits`() {
        val advent = Advent8(3, 2)
        assertEquals(1, advent.firstTask(listOf("123456789012")))
    }

    @Test
    fun `decodes message`() {
        val advent = Advent8(2, 2)
        assertEquals(listOf(listOf(0, 1), listOf(1, 0)), advent.secondTask(listOf("0222112222120000")))
    }
}
