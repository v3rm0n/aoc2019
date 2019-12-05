package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent1Test {
    @Test
    fun testWithValue() {
        val advent1 = Advent1(listOf("100756"))
        assertEquals(33583, advent1.firstTask())
        assertEquals(50346, advent1.secondTask())
    }

    @Test
    fun testWithOtherValue() {
        val advent1 = Advent1(listOf("1969"))
        assertEquals(654, advent1.firstTask())
        assertEquals(966, advent1.secondTask())
    }
}
