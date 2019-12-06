package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent3Test {
    @Test
    fun testWithValue() {
        val advent3 = Advent3()
        val input = listOf(
            "R75,D30,R83,U83,L12,D49,R71,U7,L72",
            "U62,R66,U55,R34,D71,R55,D58,R83"
        )
        assertEquals(159, advent3.firstTask(input))
        assertEquals(610, advent3.secondTask(input))
    }

    @Test
    fun testWithOther() {
        val advent3 = Advent3()
        val input = listOf(
            "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
        )
        assertEquals(135, advent3.firstTask(input))
        assertEquals(410, advent3.secondTask(input))
    }
}
