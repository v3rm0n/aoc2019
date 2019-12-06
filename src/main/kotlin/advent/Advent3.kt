package advent

import kotlin.math.abs

class Advent3 : Advent {

    override fun firstTask(input: List<String>) =
        intersections(wires(input)).map { (x, y) -> abs(0 - x) + abs(0 - y) }.min() ?: "Not found!"

    override fun secondTask(input: List<String>) = wires(input).let { (first, second) ->
        intersections(first to second).map { first.indexOf(it) + second.indexOf(it) }.min() ?: "Not found!"
    }

    private fun intersections(wires: Pair<List<Pair<Int, Int>>, List<Pair<Int, Int>>>) =
        wires.first.intersect(wires.second).drop(1)

    private fun wires(input: List<String>): Pair<List<Pair<Int, Int>>, List<Pair<Int, Int>>> {
        val (first, second) = input.map { mapWireToCoordinates(it.split(',')) }
        return first to second
    }

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


