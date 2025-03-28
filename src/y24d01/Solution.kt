package y24d01

import readInput
import solution
import kotlin.math.abs

fun main() {
    class A;
    val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {
        val pairList =  input.map { line ->
            line.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        }
        val first = pairList.map { it.first() }.sorted()
        val second = pairList.map { it.last() }.sorted()
        return first.mapIndexed { index, value ->
            abs(value - second[index])
        }.sum().toLong()
    }

    fun part2(input: List<String>): Long {
        val pairList =  input.map { line ->
            line.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        }
        val first = pairList.map { it.first() }.sorted()
        val second = pairList.map { it.last() }.sorted()
        return first.mapIndexed { index, value ->
            second.count { value == it } * value
        }.sum().toLong()
    }

    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 11L, true) { part1(testInput) }
//    solution("Part2 test: ", 1L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ", 1580061L) { part1(input) }
    solution("Part2 solution: ", 23046913L) { part2(input) }
}
