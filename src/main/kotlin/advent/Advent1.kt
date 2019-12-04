package advent

import kotlin.math.floor

class Advent1 : Advent {
    private val lines = readFile("/advent1")

    override fun firstTask() = getFuel(lines).toString()

    override fun secondTask() = getCumulativeFuel(lines).toString()

    private fun getFuel(lines: List<String>): Int =
        lines.fold(0.0) { acc, s -> acc + getFuelForCargo(s.toDouble()) }.toInt()

    private fun getFuelForCargo(cargo: Double): Double = floor(cargo / 3) - 2

    private fun getCumulativeFuel(lines: List<String>): Int =
        lines.fold(0.0) { acc, s -> acc + getAllFuelForCargo(s.toDouble()) }.toInt()

    private fun getAllFuelForCargo(cargo: Double): Double {
        val fuel = getFuelForCargo(cargo)
        if (fuel <= 0.0) {
            return 0.0
        }
        return fuel + getAllFuelForCargo(fuel)
    }
}

