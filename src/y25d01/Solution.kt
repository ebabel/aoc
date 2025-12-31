package y25d01

import readInput
import solution

fun main() {
    class A; val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {

        var timesAtZero = 0

        input.mapIndexed { index, line ->
            line.substring(1).toLong().let {
                if (line.startsWith("L")) {
                    it * -1L
                } else it
            }

        }.fold(50L) { acc, lng ->
            (acc + lng).let {
                var a = it
                while (a !in 0..99) {
                    println("a = $a")
                    if (a < 0L) a += 100L
                    else a -= 100L

                }
                a
            }
                .also { if (it == 0L) timesAtZero++ }
                .also { println("dial is $acc + $lng = $it") }


        }
        return timesAtZero.toLong()
    }

    fun part2(input: List<String>): Long {

        var timesAtZero = 0

        val z = input.mapIndexed { index, line ->
            line.substring(1).toLong().let {
                if (line.startsWith("L")) {
                    it * -1L
                } else it
            }

        }.fold(50L) { acc, lng ->
            (acc + lng).let {
                var a = it

                while (true) {
                    println("a = $a $acc $lng")
                    if (a < 0L) {
                        timesAtZero++
                        println("adding 1 to timesAtZero because we're going under it $a $it because  $acc, $lng")
                        a += 100L
//                        if (a == 0L) {
//                            println("adding 1 to timesAtZero because we're at it $a $it because  $acc, $lng")
//                            timesAtZero++
//                        }
                    } else if (a > 99) {
                        println("adding 1 to timesAtZero because we're going over it $a $it because  $acc, $lng")
                        timesAtZero++
                        a -= 100L
                    } else {
                        if (a == 0L) {
                            println("adding 1 to timesAtZero because we're at it $a $it because  $acc, $lng")
                            timesAtZero++
                        }
                        break
                    }
//                    if (true) {
//                        timesAtZero++
//                        println("adding 1 to timesAtZero because we're going past it $a $it because  $acc, $lng")
//                    }
                }

                a
            }
//                .also { if (it == 0L) timesAtZero++ }
                .also { println("dial is $acc + $lng = $it") }

        }
//        if (z == 0L) timesAtZero++
        return timesAtZero.toLong()
    }
    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 3L, true) { part1(testInput) }
    solution("Part2 test: ", 6L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }

    // not 6671, but probably close
    // not 6779

}
