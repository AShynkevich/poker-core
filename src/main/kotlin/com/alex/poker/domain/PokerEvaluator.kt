package com.alex.poker.domain

class PokerEvaluator {
    private var kindMatches = 0
    private var pairs = 0
    private var straight = false
    private var flush = false
    private var royal = false

    var order = 0
    var startFrom = 0

    fun evaluate(case: List<Card>): PokerCombo {
        val sorted = case.sortedWith { c1, c2 -> c2.value.number.compareTo(c1.value.number) }
        for (i in 1..<sorted.size) {

        }

        if (straight && flush && sorted[0].value.number == 10) {
            royal = true
        }

        return PokerCombo.findCombo(setting = ComboSetting(
            kindMatches = kindMatches,
            pairs = pairs,
            straight = straight,
            flush = flush,
            royal = royal,
        ))
    }
}

data class ComboSetting(
    val kindMatches: Int = 0,
    val pairs: Int = 0,
    val straight: Boolean = false,
    val flush: Boolean = false,
    var royal: Boolean = false
)

enum class PokerCombo(val weight: Int, private val setting: ComboSetting) {
    HIGH_CARD(1, ComboSetting()),
    PAIR(2, ComboSetting(kindMatches = 1, pairs = 1)),
    TWO_PAIR(3, ComboSetting(kindMatches = 1, pairs = 2)),
    THREE_OF_A_KIND(4, ComboSetting(kindMatches = 2, pairs = 2)),
    STRAIGHT(5, ComboSetting(straight = true)),
    FLUSH(6, ComboSetting(flush = true)),
    FULL_HOUSE(7, ComboSetting(kindMatches = 2, pairs = 2)),
    FOUR_OF_KIND(8, ComboSetting(kindMatches = 3, pairs = 2)),
    STRAIGHT_FLUSH(9, ComboSetting(straight = true, flush = true)),
    ROYAL_FLUSH(10, ComboSetting(straight = true, flush = true, royal = true));

    companion object {
        fun findCombo(setting: ComboSetting) = entries.find { it.setting == setting }!!
    }
}