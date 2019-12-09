package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent5Test {

    @Test
    fun `outputs input value`() {
        val advent = Advent5(10)
        assertEquals(listOf(10L), advent.firstTask(listOf("3,0,4,0,99")))
    }

    @Test
    fun `parameter mode functional`() {
        val advent = Advent5(null, null)
        assertEquals(emptyList(), advent.firstTask(listOf("1002,4,3,4,33")))
    }

    @Test
    fun `negative values supported`() {
        val advent = Advent5(null, null)
        assertEquals(emptyList(), advent.firstTask(listOf("1101,100,-1,4,0")))
    }

    @Test
    fun `equals supported`() {
        val advent = Advent5(8)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,9,8,9,10,9,4,9,99,-1,8")))
    }

    @Test
    fun `less than supported`() {
        val advent = Advent5(7)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,9,7,9,10,9,4,9,99,-1,8")))
    }

    @Test
    fun `equals with immediate mode supported`() {
        val advent = Advent5(8)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,3,1108,-1,8,3,4,3,99")))
    }

    @Test
    fun `less than with immediate mode supported`() {
        val advent = Advent5(7)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,3,1107,-1,8,3,4,3,99")))
    }

    @Test
    fun `jumping supported`() {
        val advent = Advent5(10)
        assertEquals(listOf(1L), advent.firstTask(listOf("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9")))
    }

    @Test
    fun `jumping with immediate mode supported`() {
        val advent = Advent5(0)
        assertEquals(listOf(0L), advent.firstTask(listOf("3,3,1105,-1,9,1101,0,0,12,4,12,99,1")))
    }

    @Test
    fun `comparison with eight works`() {
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
