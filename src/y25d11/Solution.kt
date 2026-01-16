package y25d11

import Point
import PointL
import areaWith
import iterateBetween
import println
import readInput
import solution
import takeAfter
import java.util.LinkedList
import kotlin.collections.forEachIndexed
import kotlin.math.max
import kotlin.math.min

fun main() {
    class A;
    val dir = A().javaClass.packageName


    fun part1(input: List<String>): Long {

        val map = HashMap<String, List<String>>()

        val queue = LinkedList<String>()
        queue.add("fft")
        var outCounter = 0L

        input.map {
            val source = it.substringBefore(":")
            val destinations = it.substringAfter(":").split(" ").filter { it.isNotEmpty() }
            map[source] = destinations

        }

        map.println()

        while (queue.isNotEmpty()) {
            val popped = queue.pop()

//            println("popped $popped")

            map[popped]?.forEach { resolved ->
                if (resolved == "dac") {
                    outCounter++
                } else {
                    queue.add(resolved)
                }
            }

        }

        return outCounter
    }
    fun part2(input: List<String>): Long {

        val map = HashMap<String, List<String>>()
        val map2 = HashMap<String, Int>()
        val set = HashSet<String>()

        val queue = LinkedList<String>()
        queue.add("dac")
        var outCounter = 0L

        input.map {
            val source = it.substringBefore(":")
            val destinations = it.substringAfter(":").split(" ").filter { it.isNotEmpty() }
            map[source] = destinations

        }
        while (queue.isNotEmpty()) {
            val popped = queue.pop()
            map[popped]?.forEach { resolved ->
                val destCount = if (map2[resolved] == null) {
                    1
                } else {
                    map2[resolved]!! + 1
                }
                map2[resolved] = destCount
                if (!set.contains(resolved)) {
                    set.add(resolved)
                    queue.add(resolved)
                }
            }
        }

        map2.println()
        map.println()
//
//        map2.entries.toList().forEach { (key, value) ->
//            map[key]!!.forEach { resolved ->
//                val destCount = if (map2[resolved] == null) {
//                    1
//                } else {
//                    map2[resolved]!! + value
//                }
//                map2[resolved] = destCount
//                if (!set.contains(resolved)) {
//                    set.add(resolved)
//                    queue.add(resolved)
//                }
//            }
//        }
//
//        map2.println()
//        map2.keys.size.println()
//        map2.keys.distinct().size.println()
//        map.println()

//        map2.entries.toList().forEach { (key, value) ->
//            map[key]?.forEach { resolved ->
//                val destCount = if (map2[resolved] == null) {
//                    1
//                } else {
//                    map2[resolved]!! + value
//                }
//                map2[resolved] = destCount
//            } ?: 1.let {
//                if (key == "out") {
////                    outCounter += map2[key]!!
//                }
//            }
//        }

        println("out = ${map2}")
        println("out = ${map2["out"]}")

        map2.println()
        map2.keys.size.println()
        map2.keys.distinct().size.println()
        map.println()

//        while (queue.isNotEmpty()) {
//            val popped = queue.pop()
//
////            println("popped $popped")
//
//            map[popped]?.forEach { resolved ->
//                if (resolved == "fft") {
//                    outCounter++
//                } else {
//                    queue.add(resolved)
//                }
//            }
//
//        }

        return map2["out"]!!.toLong()
    }

    fun part2x(input: List<String>): Long {

        val map = HashMap<String, List<String>>()

        input.map {
            val source = it.substringBefore(":")
            val destinations = it.substringAfter(":").split(" ").filter { it.isNotEmpty() }
            map[source] = destinations

        }

//        map.entries.toList().filter { it.value.contains("out") }.println().forEach { entry ->
//            map.entries.toList().filter { it.value.contains(entry.key) }.println()
//
//        }

        data class NodePath(val current: String, val foundDac: Boolean, val foundFft: Boolean)

        val queue = LinkedList<NodePath>()
        queue.add(NodePath("fft",false, false))
        var outCounter = 0L


        map.println()

        while (queue.isNotEmpty()) {
            val popped = queue.pop()

//            println("popped $popped")

            map[popped.current]?.forEach { resolved ->
                if (resolved == "out" && popped.foundDac && popped.foundFft) {
                    outCounter++
                } else {
                    println("found one! ${queue.size}")
                    queue.add(NodePath(resolved,popped.foundDac || resolved == "dac", popped.foundFft || resolved == "fft"))
                }
            }

        }

        return outCounter
    }



    val testInput = readInput("$dir/test_input")
    val testInput2 = readInput("$dir/test_input2")

//    solution("Part1 test: ", 5L, true) { part1(testInput) }
//    solution("Part2 test: ", 2L, true) { part2(testInput2) }
    val input = readInput("$dir/input")
    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }

    // svr to: fft 2, dac 1
    // fft to: dac 1, out 23
    // dac to: fft 0, out 23
}
