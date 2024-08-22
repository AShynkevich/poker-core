package com.alex.poker.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `the deck should be different after each shuffling`() {
        val deck = CardDeck.getShuffledDeck()
        val nextDeck = CardDeck.getShuffledDeck()

        assertNotSame(deck, nextDeck)
    }
}