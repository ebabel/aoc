package y25d04

import Point
import day10.Dir
import isEven
import println
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName

    fun checkInput(y: Int, x: Int, dir: y25d04.Dirextion, input: List<String>): Int {
        val newY = y + dir.mod.y
        val newX = x + dir.mod.x
        if (newY !in input.indices || newX !in input[0].indices) return 0//.also { println("${dir.name} $newX,$newY z") }
        if (input[newY][newX] == '@') return 1//.also { println("${dir.name} $newX,$newY 1") }
        else return 0//.also { println("${dir.name} $newX,$newY 0") }

    }

    fun checks(y: Int, x: Int, input: List<String>): Int {
//            println("$x,$y")
        return y25d04.Dirextion.entries.map {
            checkInput(y, x, it, input = input)
        }.sum()
    }

    fun part1(input: List<String>): Long {


        return input.mapIndexed { indexY, string ->
            string.mapIndexed { indexX, ch ->
                val c = if (ch == '@') {
                    if (checks(indexY, indexX, input) < 4) 1 else 0
                } else 0
                print(c)
                c
            }.sum().also { println() }

        }.sum().toLong()

    }

    fun part2(input: List<String>): Long {

        var removed = 0
        var newRemoved = 0
        var newInput = input

        do {
            newRemoved = 0
            newInput = newInput.mapIndexed { indexY, string ->
                string.mapIndexed { indexX, ch ->

                    if (ch == '@' || ch == 'x') {
                        if (checks(indexY, indexX, newInput) < 4) {
                            newRemoved += 1
                            "."
                        } else {
                            "@"
                        }
                    } else "."
                }.joinToString("")
            }
            removed += newRemoved

            newInput.forEach {
                println(it)
            }
            println("newRemoved $newRemoved")
            println()
        } while (newRemoved > 0)

        return removed.toLong()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 13L, true) { part1(testInput) }
    solution("Part2 test: ", 43L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ", 1516L) { part1(input) }

    solution("Part2 solution: ") { part2(input) }

}

enum class Dirextion(val mod: Point) {
    North(Point(0, -1)),
    NorthEast(Point(-1, -1)),
    NorthWest(Point(1, -1)),
    South(Point(0, 1)),
    SouthEast(Point(-1, 1)),
    SouthWest(Point(1, 1)),
    East(Point(1, 0)),
    West(Point(-1, 0));

}