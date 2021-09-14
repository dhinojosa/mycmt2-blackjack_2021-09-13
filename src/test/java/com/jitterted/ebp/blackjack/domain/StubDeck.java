package com.jitterted.ebp.blackjack.domain;

import java.util.Arrays;
import java.util.ListIterator;

public class StubDeck extends Deck {

    private final ListIterator<Card> iterator;

    public StubDeck(Card... cards) {
       this.iterator = Arrays.asList(cards).listIterator();
    }

    @Override
    public Card draw() {
        return iterator.next();
    }
}
