package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent2Test {
    @Test
    fun `calculates 3500`() {
        val advent2 = Advent2(emptyList())
        assertEquals(3500, advent2.firstTask(listOf("1,9,10,3,2,3,11,0,99,30,40,50")))
    }

    @Test
    fun `calculates 30`() {
        val advent2 = Advent2(emptyList())
        assertEquals(30, advent2.firstTask(listOf("1,1,1,4,99,5,6,0,99")))
    }
}
