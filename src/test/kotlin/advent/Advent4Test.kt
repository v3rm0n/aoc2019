package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent4Test {
    @Test
    fun testWithDefaultValue() {
        val advent = Advent4()
        assertEquals(594, advent.firstTask())
        assertEquals(364, advent.secondTask())
    }
}
