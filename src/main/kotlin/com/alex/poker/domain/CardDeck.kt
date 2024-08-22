package com.alex.poker.domain

import java.util.LinkedList
import java.util.Queue

enum class Suit {
    DIAMOND, SPADE, HEART, CLUB;
}

enum class Value(val number: Int) {
    ACE(14), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(11), QUEEN(12), KING(13);
}

data class Card(val suit: Suit, val value: Value)

object CardDeck {
    private val deck = generateDeck()

    private fun generateDeck(): MutableList<Card> {
        val cards = mutableListOf<Card>()
        for (suit in Suit.entries) {
            for (value in Value.entries) {
                cards.add(Card(suit, value))
            }
        }
        return cards
    }

    fun getShuffledDeck(): Queue<Card> {
        deck.shuffle()
        return LinkedList(deck)
    }
}