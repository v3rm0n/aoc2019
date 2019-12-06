package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent1Test {
    @Test
    fun testWithValue() {
        val advent1 = Advent1()
        assertEquals(33583, advent1.firstTask(listOf("100756")))
        assertEquals(50346, advent1.secondTask(listOf("100756")))
    }

    @Test
    fun testWithOtherValue() {
        val advent1 = Advent1()
        assertEquals(654, advent1.firstTask(listOf("1969")))
        assertEquals(966, advent1.secondTask(listOf("1969")))
    }
}
