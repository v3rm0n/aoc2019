package advent

import kotlin.test.Test
import kotlin.test.assertEquals

class Advent6Test {

    private val advent = Advent6()

    @Test
    fun `calculates all orbits`() {
        assertEquals(
            42, advent.firstTask(
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
        )
    }

    @Test
    fun `calculates orbits from YOU to SAN`() {
        assertEquals(
            4, advent.secondTask(
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
        )
    }
}
