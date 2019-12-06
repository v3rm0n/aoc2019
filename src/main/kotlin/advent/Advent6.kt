package advent

class Advent6(text: List<String> = readFile("/advent6")) : Advent {

    private val orbits = text.map { val (a, b) = it.split(')'); b to a }.toMap()

    override fun firstTask() = orbits.values.map { planets(it).count() }.sum()

    override fun secondTask() = transfers()

    private fun transfers(): Int {
        val myPlanets = planets("YOU")
        val sanPlanets = planets("SAN")
        return myPlanets.intersect(sanPlanets).let {
            myPlanets.count { m -> !it.contains(m) } + sanPlanets.count { s -> !it.contains(s) }
        }
    }

    private fun planets(planet: String): List<String> {
        if (planet == "COM") return listOf("COM")
        val orbitsPlanet = orbits[planet] ?: error("No planet")
        return listOf(orbitsPlanet) + planets(orbitsPlanet)
    }
}
