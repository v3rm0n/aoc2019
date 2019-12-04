package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent2Test {
    @Test
    fun testWithValue() {
        val advent2 = Advent2(listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50))
        assertEquals("3500", advent2.firstTask())
    }

    @Test
    fun testWithOtherValue() {
        val advent2 = Advent2(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99))
        assertEquals("30", advent2.firstTask())
    }
}
