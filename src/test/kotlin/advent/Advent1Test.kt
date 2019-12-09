package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent1Test {

    private val advent1 = Advent1()

    @Test
    fun `required fuel for 100756 is 33583`() {
        assertEquals(33583, advent1.firstTask(listOf("100756")))
    }

    @Test
    fun `required cumulative fuel for 100756 is 50346`() {
        assertEquals(50346, advent1.secondTask(listOf("100756")))
    }

    @Test
    fun `required fuel for 1969 is 654`() {
        assertEquals(654, advent1.firstTask(listOf("1969")))
    }

    @Test
    fun `required cumulative fuel for 1969 is 966`() {
        assertEquals(966, advent1.secondTask(listOf("1969")))
    }
}
