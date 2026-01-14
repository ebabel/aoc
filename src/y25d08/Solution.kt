package y25d08

import powLong
import println
import productOf
import readInput
import solution
import takeAfter
import kotlin.math.sqrt

fun main() {
    class A;
    val dir = A().javaClass.packageName




    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 40L, true) { part1(testInput) }
    solution("Part2 test: ", 25272L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input, 1000) }
    solution("Part2 solution: ") { part2(input) }


}

fun part1(input: List<String>, pairsRequested: Int = 10): Long {
    val list = input.map {
        it
            .split(",")
            .map { it.toInt() }
    }.map {
        Point3(it[0], it[1], it[2])
    }

    val circuitDistances = list.mapIndexed { index, outer ->
        list.takeAfter(index+1).map { inner ->
            val distanceTo = outer.distanceTo(inner)
            println("distance from $outer to $inner is $distanceTo")
            CircuitDistance(outer, inner, distanceTo)
        }
    }.flatten().sortedBy { it.distance }

    val worklingList =
        circuitDistances.subList(0, pairsRequested)
            .map { Circuit(mutableListOf(it.one, it.two), 1) }
            .toMutableList()
    var intersectingLists =
        worklingList
            .findIntersectingLists()
    while (intersectingLists != null) {
        println("workingList $worklingList")
        println("move from ${intersectingLists.second} to ${intersectingLists.first}")
        worklingList[intersectingLists.first].connections += worklingList[intersectingLists.second].connections
        worklingList[intersectingLists.first].junctions.addAll(worklingList[intersectingLists.second].junctions)
        worklingList.removeAt(intersectingLists.second)
        println("now workingList $worklingList")

        intersectingLists =
            worklingList
                .findIntersectingLists()
    }

    worklingList.forEach {
        val copy = it.junctions.distinct()
        it.junctions.clear()
        it.junctions.addAll(copy)

    }

    println()
    println()
    println()
    worklingList.sortedByDescending { it.junctions.size }.forEach {
        println("size ${it.junctions.size}")
        it.junctions.println()
    }


    return worklingList
        .sortedByDescending { it.junctions.size }
        .take(3)
        .map { it.junctions.size }
        .productOf().toLong()


}


fun part2(input: List<String>, pairsRequested: Int = 10): Long {
    val list = input.map {
        it
            .split(",")
            .map { it.toInt() }
    }.map {
        Point3(it[0], it[1], it[2])
    }

    val circuitDistances = list.mapIndexed { index, outer ->
        list.takeAfter(index+1).map { inner ->
            val distanceTo = outer.distanceTo(inner)
            CircuitDistance(outer, inner, distanceTo)
        }
    }.flatten().sortedBy { it.distance }

    val set = mutableSetOf<Point3>()
    circuitDistances.forEachIndexed { index, distance ->
        set.add(distance.one)
        set.add(distance.two)
        if (set.size == input.size) {
            return (distance.one.x * distance.two.x).toLong()
        }
    }
    return 0L
}

private fun List<Circuit>.findIntersectingLists(): Pair<Int, Int>? {
    forEachIndexed { outerIndex, outer ->
        forEachIndexed { innerIndex, inner ->
            if (innerIndex > outerIndex && outer.junctions.any { inner.junctions.contains(it) }) {
                println("$innerIndex $outerIndex")
                return outerIndex to innerIndex
            }

        }
    }
    return null
}

data class Point3(val x: Int, val y: Int, val z: Int) {
    fun distanceTo(inner: Point3): Double = sqrt(((x - inner.x).powLong(2) + (y - inner.y).powLong(2) + (z - inner.z).powLong(2)).toDouble())
}

class Circuit(val junctions: MutableList<Point3>, var connections: Int)


data class CircuitDistance(val one: Point3, val two: Point3, val distance: Double)
