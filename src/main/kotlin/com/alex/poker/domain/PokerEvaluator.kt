package com.alex.poker.domain

class PokerEvaluator {

    fun evaluate(case: List<Card>): HandResult {
        var straight = false
        var flush = false
        var royal = false
        var isAceAsOne = false

        val sorted = case.sortedWith { c1, c2 -> c1.value.number.compareTo(c2.value.number) }
        val groupedByValue = sorted.groupingBy { it.value }.eachCount().toList().sortedByDescending { it.second }

        val groups = groupedByValue.size
        val kindMatches = groupedByValue[0].second

        if (groups == sorted.size) {
            flush = sorted.groupBy { it.suit }.size == 1

            straight = if (sorted.first().value == Value.TWO && sorted.last().value == Value.ACE) {
                isAceAsOne = true
                sorted[sorted.lastIndex - 1].value.number - 1 == 4
            } else {
                sorted.last().value.number - sorted.first().value.number == 4
            }

            if (straight && flush && sorted[0].value.number == 10) {
                royal = true
            }
        }

        val combo = PokerCombo.findCombo(
            setting = ComboSetting(
                kindMatches = kindMatches,
                groups = groups,
                straight = straight,
                flush = flush,
                royal = royal,
            )
        )

        return HandResult(
            combo,
            sorted.sumOf { it.value.number }.also { if (isAceAsOne) it - 13 },
        )
    }
}

data class HandResult(
    val combo: PokerCombo,
    val extraWeight: Int,
)

data class ComboSetting(
    val kindMatches: Int = 1,
    val groups: Int = 5,
    val straight: Boolean = false,
    val flush: Boolean = false,
    var royal: Boolean = false
)

enum class PokerCombo(val weight: Int, private val setting: ComboSetting) {
    HIGH_CARD(1, ComboSetting()),
    PAIR(2, ComboSetting(kindMatches = 2, groups = 4)),
    TWO_PAIR(3, ComboSetting(kindMatches = 2, groups = 3)),
    THREE_OF_A_KIND(4, ComboSetting(kindMatches = 3, groups = 3)),
    STRAIGHT(5, ComboSetting(straight = true)),
    FLUSH(6, ComboSetting(flush = true)),
    FULL_HOUSE(7, ComboSetting(kindMatches = 3, groups = 2)),
    FOUR_OF_KIND(8, ComboSetting(kindMatches = 4, groups = 2)),
    STRAIGHT_FLUSH(9, ComboSetting(straight = true, flush = true)),
    ROYAL_FLUSH(10, ComboSetting(straight = true, flush = true, royal = true));

    companion object {
        fun findCombo(setting: ComboSetting) = entries.find { it.setting == setting }!!
    }
}
