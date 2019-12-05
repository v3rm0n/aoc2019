package advent

import kotlin.math.abs

class Advent3(
    wires: List<List<String>> =
        readFile("/advent3")
            .map { it.split(',') }
) : Advent {

    private val mappedWires = wires.map { mapWireToCoordinates(it) }

    private val first = mappedWires[0]
    private val second = mappedWires[1]

    private val intersections = first.intersect(second).drop(1)

    override fun firstTask() = intersections.map { (x, y) -> abs(0 - x) + abs(0 - y) }.min() ?: "Not found!"

    override fun secondTask() = intersections.map { first.indexOf(it) + second.indexOf(it) }.min() ?: "Not found!"

    private fun mapWireToCoordinates(wire: List<String>): List<Pair<Int, Int>> {
        return wire.fold(listOf(0 to 0)) { result, i ->
            val amount = i.drop(1).toInt()
            result + when (i[0]) {
                'D' -> move(0, -amount, result.last())
                'U' -> move(0, amount, result.last())
                'L' -> move(-amount, 0, result.last())
                'R' -> move(amount, 0, result.last())
                else -> throw IllegalStateException("Illegal move")
            }
        }
    }

    private fun move(xd: Int, yd: Int, start: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (x1, y1) = start
        val (x2, y2) = x1 + xd to y1 + yd
        return (if (xd < 0) x1.downTo(x2) else x1..x2).flatMap { x ->
            (if (yd < 0) y1.downTo(y2) else y1..y2).mapNotNull { y ->
                if (y != y1 || x != x1) x to y else null
            }
        }
    }
}


