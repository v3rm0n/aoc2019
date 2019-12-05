package advent

class Advent1(private val lines: List<Int> = readFile("/advent1").map { it.toInt() }) : Advent {

    override fun firstTask() = lines.map { it / 3 - 2 }.sum()

    override fun secondTask() = lines.map { getAllFuelForCargo(it) }.sum()

    private tailrec fun getAllFuelForCargo(cargo: Int, acc: Int = 0): Int {
        val fuel = cargo / 3 - 2
        if (fuel <= 0.0) {
            return acc
        }
        return getAllFuelForCargo(fuel, acc + fuel)
    }
}

