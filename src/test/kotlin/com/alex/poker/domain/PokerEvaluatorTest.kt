package com.alex.poker.domain

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class PokerEvaluatorTest {

    @ParameterizedTest
    @MethodSource("provideHands")
    fun process(hand: List<Card>, expectedCombo: PokerCombo) {
        assertEquals(PokerEvaluator().evaluate(hand), expectedCombo)
    }

    companion object {
        @JvmStatic
        private fun provideHands(): List<Arguments> = listOf(
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.TWO),
                Card(Suit.SPADE, Value.THREE),
                Card(Suit.HEART, Value.FOUR),
                Card(Suit.SPADE, Value.FIVE),
                Card(Suit.HEART, Value.ACE),
            ), PokerCombo.STRAIGHT),
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.TEN),
                Card(Suit.SPADE, Value.JACK),
                Card(Suit.HEART, Value.QUEEN),
                Card(Suit.SPADE, Value.KING),
                Card(Suit.HEART, Value.ACE),
            ), PokerCombo.STRAIGHT),
        )
    }

}