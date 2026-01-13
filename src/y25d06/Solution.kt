package y25d06

import println
import productOf
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName


    fun part1(input: List<String>): Long {

        val numbers = input
            .take(input.size-1)
            .map { it
                .split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toLong() }
            }
        val operators = input
            .last()
            .split(" ")
            .filter { it.isNotEmpty() }

        return operators.mapIndexed { index, string ->
            if (string == "*") {
                numbers.map { it[index] }.productOf()
            } else {
                numbers.sumOf { it[index] }
            }
        }.sum()


    }

    fun part2(input: List<String>): Long {

        var operator: Char?  = null
        var runningTotal: Long = 0
        var total = 0L

        input.first().indices.map { index ->
            val col = input.map { it[index] }
            if (operator == null) {
                total += runningTotal
                operator = col.last()
                if (col.last() == '*') {
                    runningTotal = 1
                } else if (col.last() == '+') {
                    runningTotal = 0
                } else {
                    error("unexpected $index ${col.last()}")
                }
            }

            if (col.all { it.isWhitespace() }) {
                operator = null
            } else {
                val newLong = col.filter { it.isDigit() }.joinToString("").toLong()
                if (operator == '*') {
                    runningTotal *= newLong
                } else if (operator == '+') {
                    runningTotal += newLong
                }

            }



        }
        total += runningTotal

        return total
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 4277556L, true) { part1(testInput) }
    solution("Part2 test: ", 3263827L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ", 5361735137219L) { part1(input) }

    solution("Part2 solution: ") { part2(input) }


}
