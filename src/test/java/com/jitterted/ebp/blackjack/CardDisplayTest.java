package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDisplayTest {

    @Test
    void displayTenAsString() {
        Card card = new Card(Suit.CLUBS, Rank.TEN);
        assertThat(ConsoleCard.display(card)).isEqualTo("[30m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    void displayNonTenAsString() {
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        assertThat(ConsoleCard.display(card)).isEqualTo("[31m┌─────────┐[1B[11D│A        │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│        A│[1B[11D└─────────┘");
    }
}