package y25d05

import println
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName


    fun part1(input: List<String>): Long {

        val freshIngredients = mutableListOf<LongRange>()

        return input.mapIndexed { indexY, string ->
            if (string.contains("-")) {
                val a = string.split("-")
                freshIngredients.add(a.first().toLong()..a.last().toLong())

                0
            } else if (string.isNotEmpty()) {
                val a = string.toLong()
                if (freshIngredients.any { it.contains(a) }) 1 else 0
            } else 0
        }.sum().toLong()

    }

    fun part2(input: List<String>): Long {

        val freshIngredients = mutableListOf<LongRange>()

        input.mapIndexed { indexY, string ->
            if (string.contains("-")) {
                val a = string.split("-")
                val start = a.first().toLong()
                val end = a.last().toLong()

                if (start > end) error("bad data?")

                freshIngredients.add(start..end)
            }
        }

        freshIngredients.println()
        val copy: List<LongRange> = freshIngredients.toList()


        var overlapFound: IndexedValue<LongRange>? = freshIngredients.withIndex()
            .firstOrNull { it.value.last > freshIngredients[it.index + 1].first }

        do {
            freshIngredients.sortBy { it.first() }
            overlapFound = freshIngredients.withIndex()
                .firstOrNull {
                    it.index < freshIngredients.size -1 &&
                    it.value.last >= freshIngredients[it.index + 1].first }
            println("first? $overlapFound")
            if (overlapFound != null) {

                val storedSecondRange = freshIngredients[overlapFound.index + 1]

                println("removing ${freshIngredients[overlapFound.index + 1]}")
                freshIngredients.remove(freshIngredients[overlapFound.index + 1])
                println("removing ${freshIngredients[overlapFound.index]}")
                freshIngredients.remove(freshIngredients[overlapFound.index])
                val newMax = maxOf( overlapFound.value.endInclusive, storedSecondRange.endInclusive)
                val newRange =
                    overlapFound.value.first..newMax
                println("adding ${newRange}")
                freshIngredients.add(newRange)

            } else {
                overlapFound = null
            }


            freshIngredients.sortBy { it.first() }
            freshIngredients.println()

        } while (overlapFound != null)


        copy.forEach { copyItem: LongRange ->
            if (!freshIngredients.any { it.contains(copyItem.first) }) {
                error("not contained! ${copyItem.first()}")
            }
            if (!freshIngredients.any { it.contains(copyItem.last) }) {
                error("not contained! ${copyItem.last()}")
            }
        }


        return freshIngredients.map { it.last - it.first + 1L }.sum().toLong()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 3L, true) { part1(testInput) }
//    solution("Part2 test: ", 14L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ", 640L) { part1(input) }

    solution("Part2 solution: ") { part2(input) }
    // 338710402211149 too low
    // 339335997788043 too low

}

//removing
//1752048538382..
//3919577713656
//removing
//112873707283..
//2588072976190
//adding
//112873707283..
//3919577713656
//112873707283..
//2588072976190

//[112873707283..
//9874556852261,
//10529281755859..
//11482054476269,
//11719715825754..
//11891948224878,
//13873322097363..
//14285954255194,
//14789680081867..
//16270031310196,
//16691967595915..
//18282199072199,
//18974910015890..
//19436864629631,
//22491266733591..
//22491266733591,
//22491266733592..
//27309934454174,
//31057494209505..
//39177332066824,
// 42262220332700..45713896507228,
// 51916414292070..59240999927250,
// 62533303204105..67825553222762,
// 71543464222777..78090762827058,
// 81283750912059..89949992362428, 89949992362429..89949992362429, 92340278236275..98052923471049, 101012613040912..110109748831416, 113000677818465..115525954016206, 115525954016208..119300139269163, 122387896271845..128724674777679, 131907151087339..135942661671260, 135942661671261..138826794052738, 143005819408722..145401547845947, 145401547845948..147919354604983, 151973005926579..156512797333693, 156512797333695..158826055474391, 164333237787352..164333237787352, 171817542783488..178510357316731, 181676143413441..189391540551098, 192326821191117..196591500761696, 201681752200444..210920021810153, 212520780616681..220017483730661, 222610347197017..226761529035272, 226761529035273..226761529035273, 231841401282010..231841401282010, 231841401282011..239645488436474, 241681816381073..243371231641831, 243609259739363..243857642907182, 244969360673134..245168014960248, 245433062704698..247012266312972, 247236949150615..248247173644637, 249666852741241..250278025046055, 252255429293010..259064149874903, 264595086882696..268900975410491, 275788255335869..280442644807279, 282018373816974..290935019550276, 290935019550277..290935019550277, 293643445039097..300251037943656, 302723808391950..311265807541651, 314689366686850..318409435305114, 322744224589760..324255140817928, 325325747744144..325877713874747, 326127049183255..327436267464330, 327646151858731..328836803805980, 329530178458593..330635178092489, 330807634683642..331302629896521, 334820819940854..334820819940854, 334820819940855..339146512275577, 343473027916816..347325314236846, 353125715903228..356463066637153, 356463066637154..359027334485929, 363321531321063..367392394301803, 375693739857609..377886335848362, 384013134608808..390143577258709, 392723744538717..395741420645979, 395741420645981..400368761478414, 402250640280403..412063170731063, 413266123757676..419275913131686, 424177362954936..428646358296115, 434324052980656..438104292748636, 446361586500378..450416147859760, 455316354837082..460495879445666, 465604211486206..465604211486206, 465604211486207..468062434617162, 472619837183282..473202226508093, 473353945130508..474291566530776, 474844528095631..475929089336059, 476786713392008..477355327881092, 478785319091156..479684172807966, 479906576714841..480842610925116, 481198606285875..481739141991847, 485062599643151..489473141511900, 489473141511901..489473141511901, 493178119244148..496917339512734, 496917339512736..501990926477060, 504498824182245..507316038365589, 513352841946732..521907911083669, 522977891214923..532553773929606, 534783469330199..538242012046523,
// 538242012046524..541578569027953,
// 544801412500266..552189629171754,
// 553688776691385..561636050660162]
