package advent

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class Advent5Test {

    @Test
    fun testOutputsInputValue() {
        val advent = Advent5(10)
        assertEquals(listOf(10L), advent.firstTask(listOf("3,0,4,0,99")))
    }

    @Test
    fun testParameterMode() {
        val advent = Advent5(null, null)
        assertEquals(emptyList(), advent.firstTask(listOf("1002,4,3,4,33")))
    }

    @Test
    fun testNegativeValues() {
        val advent = Advent5(null, null)
        assertEquals(emptyList(), advent.firstTask(listOf("1101,100,-1,4,0")))
    }

    @Test
    fun testEquals() {
        val advent = Advent5(8)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,9,8,9,10,9,4,9,99,-1,8")))
    }

    @Test
    fun testLessThan() {
        val advent = Advent5(7)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,9,7,9,10,9,4,9,99,-1,8")))
    }

    @Test
    fun testEqualsImmediate() {
        val advent = Advent5(8)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,3,1108,-1,8,3,4,3,99")))
    }

    @Test
    fun testLessThanImmediate() {
        val advent = Advent5(7)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,3,1107,-1,8,3,4,3,99")))
    }

    @Test
    fun testJumping() {
        val advent = Advent5(10)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9")))
    }

    @Test
    fun testJumpingImmediate() {
        val advent = Advent5(0)
        assertEquals(listOf(0L), advent.firstTask(listOf("3,3,1105,-1,9,1101,0,0,12,4,12,99,1")))
    }

    @Test
    fun testLargerExample() {
        assertEquals(
            listOf(999L),
            Advent5(
                7
            ).firstTask(
                listOf(
                    """3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"""
                )
            )
        )
        assertEquals(
            listOf(1000L),
            Advent5(
                8
            ).firstTask(
                listOf(
                    """3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"""
                )
            )
        )
        assertEquals(
            listOf(1001L),
            Advent5(
                10
            ).firstTask(
                listOf(
                    """3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"""
                )
            )
        )
    }
}
