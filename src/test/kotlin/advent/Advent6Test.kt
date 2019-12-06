package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent6Test {
    @Test
    fun testWithDefaultValue() {
        val advent = Advent6()
        assertEquals(300598, advent.firstTask())
        assertEquals(520, advent.secondTask())
    }

    @Test
    fun testWithValue() {
        val advent = Advent6(
            listOf(
                "COM)B",
                "B)C",
                "C)D",
                "D)E",
                "E)F",
                "B)G",
                "G)H",
                "D)I",
                "E)J",
                "J)K",
                "K)L"
            )
        )
        assertEquals(42, advent.firstTask())
    }

    @Test
    fun testWithValueSecondTask() {
        val advent = Advent6(
            listOf(
                "COM)B",
                "B)C",
                "C)D",
                "D)E",
                "E)F",
                "B)G",
                "G)H",
                "D)I",
                "E)J",
                "J)K",
                "K)L",
                "K)YOU",
                "I)SAN"
            )
        )
        assertEquals(4, advent.secondTask())
    }
}
