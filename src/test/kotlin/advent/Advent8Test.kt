package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent8Test {
    @Test
    fun testWithValue() {
        val advent = Advent8(3, 2)
        assertEquals(1, advent.firstTask(listOf("123456789012")))
    }

    @Test
    fun testWithSecondValue() {
        val advent = Advent8(2, 2)
        assertEquals(listOf("01", "10"), advent.secondTask(listOf("0222112222120000")))
    }
}
