package y25d10

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

    var asdf = 0

    fun part1(input: List<String>): Long {

        fun solveLine(line: String): Long {
            val desiredResult = line.substring(1).substringBefore("]")//.println()
            //            val joltage = line.substringAfter("{").substringBefore("}")//.println()

            val buttons = line
                .substringAfter("]")
                .substringBefore("{")
        //                .println()
                .split("(")
                .map {
                    it
                        .trim()
                        .replace(")", "")
                        .split(",")
                        .filter { it.isNotEmpty() }
                        .map { it.toInt() }
                }.filter { it.isNotEmpty() }
            //                .println()

            data class PressSequence(val current: List<Boolean>, val presses: List<Int>)

            val queue1 = LinkedList<PressSequence>()
            queue1.add(
                PressSequence(
                    MutableList<Boolean>(desiredResult.length) { false },
                    emptyList()
                )
            )

            while (queue1.isNotEmpty()) {
                val pressSequence = queue1.pop()
//                println("popped ${pressSequence.current} ${pressSequence.presses}")

                buttons.forEachIndexed { index, button ->
                    val workingSet = pressSequence.current.toMutableList()
                    button.forEach { press ->

                        workingSet[press] = !workingSet[press]
                    }
                    val resultNow = workingSet.map {
                        if (it) {
                            '#'
                        } else '.'
                    }.joinToString("")//.println()

                    if (resultNow == desiredResult) {
                        return pressSequence.presses.size + 1L
                    }

                    queue1.add(PressSequence(workingSet, pressSequence.presses.plus(index)))
                }
            }


            return 0L
        }

        return input.mapIndexed { index, line ->

            println("solved $index of ${input.size}")
            solveLine(line)


        }.sum()


    }

    fun part2(input: List<String>): Long {

        fun solveLine(line: String): Long {
            val desiredResult = line
                .substring(1)
                .substringBefore("]")//.println()
                .map { it == '#' }
            val desiredJoltage =
                line
                    .substringAfter("{")
                    .substringBefore("}")//.println()
                    .split(",")
                    .map { it.toInt() }

            val buttons = line
                .substringAfter("]")
                .substringBefore("{")
                //                .println()
                .split("(")
                .map {
                    it
                        .trim()
                        .replace(")", "")
                        .split(",")
                        .filter { it.isNotEmpty() }
                        .map { it.toInt() }
                }.filter { it.isNotEmpty() }
            //                .println()

            data class PressSequence(val current: List<Boolean>, val currentJoltage: List<Int>, val presses: List<Int>)

            val queue1 = LinkedList<PressSequence>()
            queue1.add(
                PressSequence(
                    MutableList(desiredResult.size) { false },
                    MutableList(desiredJoltage.size) { 0 },
                    emptyList()
                )
            )

            while (queue1.isNotEmpty()) {
                val pressSequence = queue1.pop()
//                println("popped ${pressSequence.currentJoltage} ${pressSequence.presses} ${queue1.size}")

                buttons.forEachIndexed { index, button ->
//                    val workingSet = pressSequence.current.toMutableList()
                    val workingJoltage = pressSequence.currentJoltage.toMutableList()//.println()
                    button.forEach { press ->

//                        workingSet[press] = !workingSet[press]
                        workingJoltage[press] += 1
                    }


//                    if (resultNow == desiredResult) {
//                        println("found a solution for part 1 $pressSequence")
//                    }
//                    if (resultNow == desiredResult && workingJoltage == desiredJoltage) {
//                        return pressSequence.presses.size + 1L
//                    }
                    if (workingJoltage == desiredJoltage) {
                        val workingSet = pressSequence.current.toMutableList()
                        pressSequence.presses.forEach { press ->
                            buttons[press].forEach {
                                workingSet[it] = !workingSet[it]
                            }
//                            workingSet.println()

                        }
                        workingSet.println()
                        button.forEach { press ->
                            workingSet[press] = !workingSet[press]
                        }



                        if (workingSet == desiredResult) {
                            return pressSequence.presses.size + 1L
                        } else {
                            println("asdf ${asdf++} $button")
                            require (workingSet == listOf(true,true,false,true))
//                            println("correct joltage but incorrect sequence ${queue1.size} $workingJoltage $workingSet ${pressSequence.presses}")
                        }
                    }
                    val allBelowDesired = desiredJoltage.mapIndexed { index, i ->
                        workingJoltage[index] <= i
                    }.all{it}

                    if (allBelowDesired) {
                        queue1.add(PressSequence(pressSequence.current, workingJoltage,pressSequence.presses.plus(index)))
                    }
                }
            }


            return 0L
        }

        return input.mapIndexed { index, line ->

            println("solved $index of ${input.size}")
            solveLine(line)


        }.sum()
    }


    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 7L, true) { part1(testInput) }
    solution("Part2 test: ", 33L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }


}
