package advent

class Advent6 : Advent {

    private fun orbits(input: List<String>) = input.map { it.split(')').let { (a, b) -> b to a } }.toMap()

    override fun firstTask(input: List<String>) = orbits(input).let { orbits ->
        orbits.values.map { planets(orbits, it).count() + 1 }.sum()
    }

    override fun secondTask(input: List<String>) =
        orbits(input).let { orbits ->
            (planets(orbits, "YOU") to planets(orbits, "SAN"))
                .let { (you, san) ->
                    you.count() + san.count() - 2 * you.intersect(san).count()
                }
        }

    private tailrec fun planets(
        orbits: Map<String, String>,
        planet: String,
        acc: List<String> = emptyList()
    ): List<String> {
        if (planet == "COM") return acc
        val orbitsPlanet = orbits[planet] ?: error("No planet")
        return planets(orbits, orbitsPlanet, listOf(orbitsPlanet) + acc)
    }
}
