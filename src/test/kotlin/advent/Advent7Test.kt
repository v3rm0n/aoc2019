package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent7Test {
    @Test
    fun testWithDefaultValue() {
        val advent = Advent7()
        assertEquals(0, advent.firstTask(listOf()))
        assertEquals(0, advent.secondTask(listOf()))
    }
}
