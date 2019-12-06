package advent

class Advent6(text: List<String> = readFile("/advent6")) : Advent {

    private val orbits = text.map { it.split(')').let { (a, b) -> b to a } }.toMap()

    override fun firstTask() = orbits.values.map { planets(it).count() + 1 }.sum()

    override fun secondTask() = (planets("YOU") to planets("SAN"))
        .let { (you, san) ->
            you.count() + san.count() - 2 * you.intersect(san).count()
        }

    private tailrec fun planets(planet: String, acc: List<String> = emptyList()): List<String> {
        if (planet == "COM") return acc
        val orbitsPlanet = orbits[planet] ?: error("No planet")
        return planets(orbitsPlanet, listOf(orbitsPlanet) + acc)
    }
}
