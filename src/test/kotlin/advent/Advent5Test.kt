package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent5Test {
    @Test
    fun testWithDefaultValue() {
        val advent = Advent5()
        assertEquals(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 9938601), advent.firstTask())
        assertEquals(listOf(4283952), advent.secondTask())
    }

    @Test
    fun testWithValue() {
        val advent = Advent5(listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), 0)
        assertEquals(listOf(3500), advent.firstTask())
    }

    @Test
    fun testOutputsInputValue() {
        val advent = Advent5(listOf(3, 0, 4, 0, 99), 10)
        assertEquals(listOf(10), advent.firstTask())
    }

    @Test
    fun testParameterMode() {
        val advent = Advent5(listOf(1002, 4, 3, 4, 33), 0)
        assertEquals(listOf(1002), advent.firstTask())
    }

    @Test
    fun testNegativeValues() {
        val advent = Advent5(listOf(1101, 100, -1, 4, 0), 0)
        assertEquals(listOf(1101), advent.firstTask())
    }

    @Test
    fun testEquals() {
        val advent = Advent5(listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 8)
        assertEquals(listOf(1), advent.firstTask())
    }

    @Test
    fun testLessThan() {
        val advent = Advent5(listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 7)
        assertEquals(listOf(1), advent.firstTask())
    }

    @Test
    fun testEqualsImmediate() {
        val advent = Advent5(listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 8)
        assertEquals(listOf(1), advent.firstTask())
    }

    @Test
    fun testLessThanImmediate() {
        val advent = Advent5(listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 7)
        assertEquals(listOf(1), advent.firstTask())
    }

    @Test
    fun testJumping() {
        val advent = Advent5(listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 10)
        assertEquals(listOf(1), advent.firstTask())
    }

    @Test
    fun testJumpingImmediate() {
        val advent = Advent5(listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 0)
        assertEquals(listOf(0), advent.firstTask())
    }

    @Test
    fun testLargerExample() {
        assertEquals(
            listOf(999),
            Advent5(
                listOf(
                    3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
                ), 7
            ).firstTask()
        )
        assertEquals(
            listOf(1000),
            Advent5(
                listOf(
                    3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
                ), 8
            ).firstTask()
        )
        assertEquals(
            listOf(1001),
            Advent5(
                listOf(
                    3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
                ), 10
            ).firstTask()
        )
    }
}
