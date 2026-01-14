package y25d09

import Point
import PointL
import areaWith
import iterateBetween
import readInput
import solution
import takeAfter
import kotlin.collections.forEachIndexed
import kotlin.math.max
import kotlin.math.min

fun main() {
    class A;
    val dir = A().javaClass.packageName


    fun part1(input: List<String>): Long {

        val points =
            input.map { it.split(",") }
                .map { PointL(it.first().toLong(), it.last().toLong()) }

        println("${points.sortedBy { it.x }}")
        println("${points.sortedBy { it.y }}")

        var max = 0L
        points.forEachIndexed { index, point ->
            points.takeAfter(index + 1).forEachIndexed { indexInner, pointInner ->
                val area = point.areaWith(pointInner)
//                println("checking $point $pointInner $area")
                if (area > max) {
                    max = maxOf(max, area.toLong())
                    println("now using $point $pointInner $area")
                }
            }
        }

        return max


    }

    fun part2(input: List<String>): Long {

        val points =
            input.map { it.split(",") }
                .map { Point(it.first().toInt(), it.last().toInt()) }

        val map = Array(points.maxBy { it.y }.y + 2) {
            LongArray(points.maxBy { it.x }.x + 2) { 0L }
        }


        var prevPoint: Point = points.last()
        points.forEachIndexed { index, pointL ->
            map[pointL.y][pointL.x] = 1

            prevPoint.iterateBetween(pointL) { x, y ->
                map[y][x] = 1
            }

            prevPoint = pointL
        }



        map.forEach {
            println(it.joinToString(""))
        }

        return 0L
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 50L, true) { part1(testInput) }
//    solution("Part2 test: ", 24L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ") { part2(input) }

    // 2147477792 too low

}
