package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent4Test {
    @Test
    fun testWithDefaultValue() {
        val advent = Advent4()
        assertEquals(594, advent.firstTask(listOf("347312..805915")))
        assertEquals(364, advent.secondTask(listOf("347312..805915")))
    }
}
