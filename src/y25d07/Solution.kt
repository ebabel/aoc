package y25d07

import println
import productOf
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName


    fun part1(input: List<String>): Long {

        var beams = mutableListOf<Int>()
        var splits = 0L

        input.forEach { inputLine ->
            val indexOfS = inputLine.indexOf('S')
            if (indexOfS != -1) {
                beams.add(indexOfS)
            }

            val newBeams = beams.toList()
            newBeams.forEach { newBeam ->

                if (inputLine[newBeam] == '^') {
                    println("removing $newBeam")
                    beams.remove(newBeam)
                    beams.add(newBeam - 1)
                    beams.add(newBeam + 1)
                    splits++
                }
            }

            beams = beams.distinct().toMutableList()
        }

        return splits


    }

    fun part2(input: List<String>): Long {

        var beams = input.first().map { 0L }.toMutableList()
        var splits = 0L

        input.forEachIndexed { index, inputLine ->
            println("line $index ${beams.size}")
            val indexOfS = inputLine.indexOf('S')
            if (indexOfS != -1) {
                beams[indexOfS] = 1
            }

            inputLine.forEachIndexed { index, ch ->
                if (ch == '^') {
                    val temp = beams[index]
                    beams[index] = 0L
                    beams[index-1] += temp
                    beams[index+1] += temp
                }
            }
//            val newBeams = beams.toList()
//            newBeams.forEach { newBeam ->
//
//                if (inputLine[newBeam] == '^') {
////                    println("removing $newBeam")
//                    beams.remove(newBeam)
//                    beams.add(newBeam - 1)
//                    beams.add(newBeam + 1)
//                    splits++
//                }
//            }

        }

        return beams.sum().toLong()

    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 21L, true) { part1(testInput) }
    solution("Part2 test: ", 40L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ") { part2(input) }


}
