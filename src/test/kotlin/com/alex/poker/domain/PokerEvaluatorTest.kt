package com.alex.poker.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PokerEvaluatorTest {

    private val evaluator = PokerEvaluator()

    @ParameterizedTest(name = "Combo should be {1} ")
    @MethodSource("provideHands")
    fun `the combos should evaluated correct`(hand: List<Card>, expectedCombo: PokerCombo) {
        assertEquals(evaluator.evaluate(hand).combo, expectedCombo)
    }

    companion object {
        @JvmStatic
        private fun provideHands(): List<Arguments> = listOf(
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.ACE),
                Card(Suit.SPADE, Value.JACK),
                Card(Suit.HEART, Value.JACK),
                Card(Suit.CLUB, Value.JACK),
                Card(Suit.DIAMOND, Value.JACK),
            ), PokerCombo.FOUR_OF_KIND),
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.ACE),
                Card(Suit.SPADE, Value.JACK),
                Card(Suit.HEART, Value.JACK),
                Card(Suit.CLUB, Value.JACK),
                Card(Suit.DIAMOND, Value.QUEEN),
            ), PokerCombo.THREE_OF_A_KIND),
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.ACE),
                Card(Suit.SPADE, Value.JACK),
                Card(Suit.HEART, Value.JACK),
                Card(Suit.CLUB, Value.ACE),
                Card(Suit.DIAMOND, Value.QUEEN),
            ), PokerCombo.TWO_PAIR),
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.ACE),
                Card(Suit.SPADE, Value.EIGHT),
                Card(Suit.HEART, Value.JACK),
                Card(Suit.CLUB, Value.NINE),
                Card(Suit.DIAMOND, Value.TEN),
            ), PokerCombo.HIGH_CARD),
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.ACE),
                Card(Suit.SPADE, Value.JACK),
                Card(Suit.HEART, Value.JACK),
                Card(Suit.CLUB, Value.ACE),
                Card(Suit.DIAMOND, Value.ACE),
            ), PokerCombo.FULL_HOUSE),
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.JACK),
                Card(Suit.SPADE, Value.THREE),
                Card(Suit.SPADE, Value.FOUR),
                Card(Suit.SPADE, Value.FIVE),
                Card(Suit.SPADE, Value.ACE),
            ), PokerCombo.FLUSH),
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
            Arguments.of(listOf(
                Card(Suit.HEART, Value.TWO),
                Card(Suit.HEART, Value.THREE),
                Card(Suit.HEART, Value.FOUR),
                Card(Suit.HEART, Value.FIVE),
                Card(Suit.HEART, Value.ACE),
            ), PokerCombo.STRAIGHT_FLUSH),
            Arguments.of(listOf(
                Card(Suit.SPADE, Value.TEN),
                Card(Suit.SPADE, Value.JACK),
                Card(Suit.SPADE, Value.QUEEN),
                Card(Suit.SPADE, Value.KING),
                Card(Suit.SPADE, Value.ACE),
            ), PokerCombo.ROYAL_FLUSH),
        )
    }
}