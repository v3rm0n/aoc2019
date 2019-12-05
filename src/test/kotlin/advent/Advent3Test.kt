package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent3Test {
    @Test
    fun testWithDefaultValue() {
        val advent = Advent3()
        assertEquals(1431, advent.firstTask())
        assertEquals(48012, advent.secondTask())
    }

    @Test
    fun testWithValue() {
        val advent3 = Advent3(
            listOf(
                "R75,D30,R83,U83,L12,D49,R71,U7,L72".split(','),
                "U62,R66,U55,R34,D71,R55,D58,R83".split(',')
            )
        )
        assertEquals(159, advent3.firstTask())
        assertEquals(610, advent3.secondTask())
    }

    @Test
    fun testWithOther() {
        val advent3 = Advent3(
            listOf(
                "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(','),
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(',')
            )
        )
        assertEquals(135, advent3.firstTask())
        assertEquals(410, advent3.secondTask())
    }
}
