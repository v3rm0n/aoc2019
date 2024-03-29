package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent9Test {

    private val advent = Advent9()

    @Test
    fun `outputs quine`() {
        assertEquals(
            listOf(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99).map(Int::toLong).toList(),
            advent.firstTask(listOf("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"))
        )
    }

    @Test
    fun `supports large values - 1`() {
        assertEquals(
            listOf(1125899906842624L),
            advent.firstTask(listOf("104,1125899906842624,99"))
        )
    }

    @Test
    fun `supports large values - 2`() {
        assertEquals(
            listOf(1219070632396864L),
            advent.firstTask(listOf("1102,34915192,34915192,7,4,7,99,0"))
        )
    }
}
