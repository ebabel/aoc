package y25d03

import println
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {


        return input.map { bank ->
            mapBank(bank,2).also { println("$it $bank") }
        }.sum().toLong()

    }

    fun part2(input: List<String>): Long {


        return input.map { bank ->
            mapBank(bank,12).also { println("$it $bank") }
        }.sum().toLong()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 357L, true) { part1(testInput) }
//    solution("Part2 test: ", 3121910778619L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ", 17359) { part1(input) }

    solution("Part2 solution: ") { part2(input) }

}

fun mapBank(bank: String): Int {
    (9 downTo 1).forEach { a ->
        val indexOfA = bank.indexOf(a.toString())
        if (indexOfA >= 0) {
            (9 downTo 1).forEach { b ->
                val indexOfB = bank.indexOf(b.toString(), indexOfA+1)
                if (indexOfB >= 0 && indexOfA < indexOfB) {
                    return a * 10 + b
                }

            }

        }
    }
    throw error("not found? $bank")
}
fun mapBank(bank: String, digits: Int): Long {

    var workingIndex = 0

    val arr = bank.toCharArray()

    val withIndex = arr.withIndex()



    return (digits.downTo(1)).map { digit ->
        val maxIndexedValue = withIndex
            .filter { it.index >= workingIndex && it.index < arr.size - (digit-1)}
            .maxBy { it.value.code }
        println("max ${bank[maxIndexedValue.index]} $maxIndexedValue")
        workingIndex = maxIndexedValue.index + 1
        maxIndexedValue.value.toString()
    }.also {
        it.println()
    }.joinToString("").toLong()

}