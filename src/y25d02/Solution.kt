package y25d02

import isEven
import readInput
import solution

fun main() {
    class A; val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {

        var sumOfInvalids = 0L

        input.mapIndexed { index, line ->
            line.split(",").forEach {

                val a = it.substringBefore("-")
                val b = it.substringAfter("-")
                if (a.isNotEmpty() && b.isNotEmpty()) {
                    a.toLong().rangeTo(b.toLong()).forEach {
                        val c = it.toString()
//                        println("checking $c")
                        if (c.length.isEven() && c.isNotEmpty() && c.take(c.length / 2) == c.takeLast(c.length / 2)) {
                            sumOfInvalids += c.toLong()
//                            println("adding ")
                        }
                    }

                }

            }

        }
        return sumOfInvalids
    }

    fun part2(input: List<String>): Long {

        val invalids = mutableListOf<Long>()
        var sumOfInvalids = 0L

        input.mapIndexed { index, line ->
            line.split(",").forEach {

                val a = it.substringBefore("-")
                val b = it.substringAfter("-")
                if (a.isNotEmpty() && b.isNotEmpty()) {
                    a.toLong().rangeTo(b.toLong()).forEach {
                        val c = it.toString()
//                        println("checking $c")
                        if (c.isNotEmpty() && !isValid(it, c)){
                            sumOfInvalids += it
                            invalids.add(it)
                            println("adding $c")
                        }

                    }

                }

            }

        }
        return invalids.distinct().sum()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 1227775554L, true) { part1(testInput) }
//    solution("Part2 test: ", 4174379265L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ") { part2(input) }
    // 43872163599 too high

}

fun isValid(number: Long, c: String): Boolean {

    if (number < 11) return true
    if (c.toCharArray().distinct().size==1) return false
    (1..c.length/2).forEach { chunkSize ->
        if (c.length % chunkSize == 0) {
            val chunked = c.chunked(chunkSize.toInt())
            if (chunked.size > 1 && chunked.distinct().size == 1) {
                return false
            }

        }
    }

    return true
}
