package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent3Test {
    @Test
    fun testWithValue() {
        val advent3 = Advent3(
            listOf(
                listOf("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"),
                listOf("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83")
            )
        )
        assertEquals(159, advent3.firstTask())
        assertEquals(610, advent3.secondTask())
    }
}
