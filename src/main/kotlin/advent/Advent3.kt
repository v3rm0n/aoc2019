package advent

import kotlin.math.abs

private typealias Wire = LinkedHashSet<Coordinates>
private typealias Coordinates = Pair<Int, Int>

class Advent3(
    wires: List<List<String>> =
        readFile("/advent3")
            .map { it.split(',') }
) : Advent {

    private val mappedWires = wires.map { mapWireToCoordinates(it) }

    private val first = mappedWires[0]
    private val second = mappedWires[1]

    private val intersections = first.intersect(second).drop(1)

    override fun firstTask() = intersections.map(::distance).min().toString()

    override fun secondTask() = intersections.map { steps(first, second, it) }.min().toString()

    private fun distance(coordinates: Coordinates) = abs(0 - coordinates.first) + abs(0 - coordinates.second)

    private fun steps(first: Wire, second: Wire, intersection: Coordinates) =
        first.indexOf(intersection) + second.indexOf(intersection)

    companion object {
        private fun mapWireToCoordinates(wire: List<String>): Wire {
            var position = Coordinates(0, 0)
            val result = Wire()
            result.add(position)
            for (i in wire) {
                val amount = i.drop(1).toInt()
                position = when (i[0]) {
                    'D' -> move(position, position.copy(second = position.second - amount), result)
                    'U' -> move(position, position.copy(second = position.second + amount), result)
                    'L' -> move(position, position.copy(first = position.first - amount), result)
                    'R' -> move(position, position.copy(first = position.first + amount), result)
                    else -> throw IllegalStateException("Illegal move")
                }
            }
            return result
        }

        private fun move(start: Coordinates, end: Coordinates, result: Wire): Coordinates {
            val xRange = if (start.first > end.first) start.first.downTo(end.first) else start.first..end.first
            val yRange = if (start.second > end.second) start.second.downTo(end.second) else start.second..end.second
            for (x in xRange) {
                for (y in yRange) {
                    if (y != start.second || x != start.first) {
                        result.add(Coordinates(x, y))
                    }
                }
            }
            return end
        }
    }
}


