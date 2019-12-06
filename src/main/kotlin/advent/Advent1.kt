package advent

class Advent1 : Advent {

    override fun firstTask(input: List<String>) = input.map(String::toInt).map { it / 3 - 2 }.sum()

    override fun secondTask(input: List<String>) = input.map(String::toInt).map { getAllFuelForCargo(it) }.sum()

    private tailrec fun getAllFuelForCargo(cargo: Int, acc: Int = 0): Int {
        val fuel = cargo / 3 - 2
        if (fuel <= 0.0) {
            return acc
        }
        return getAllFuelForCargo(fuel, acc + fuel)
    }
}

