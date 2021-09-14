package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class GameOutcomeTest {

    @Test
    void playerBustsAndLoses() {
        StubDeck stubDeck = new StubDeck(
                new Card(Suit.HEARTS, Rank.TEN), //Player's First Card
                new Card(Suit.SPADES, Rank.EIGHT), //Dealer's First Card
                new Card(Suit.DIAMONDS, Rank.EIGHT), //Player's Second Card
                new Card(Suit.DIAMONDS, Rank.KING), //Dealer's Second Card
                new Card(Suit.CLUBS, Rank.FIVE) //Player's Third Card
        );
        Game game = new Game(stubDeck);
        game.initialDeal();
        game.playerHits();
        assertThat(game.determineOutcome()).isEqualTo(GameOutcome.PLAYER_BUSTS);
    }

    @Test
    public void playerBeatsDealer() {
        Deck stubDeck = new StubDeck(new Card(Suit.HEARTS, Rank.TEN), //Player's First Card
                                     new Card(Suit.SPADES, Rank.EIGHT), //Dealer's First Card
                                     new Card(Suit.DIAMONDS, Rank.KING), //Player's Second Card
                                     new Card(Suit.CLUBS, Rank.KING)); //Dealer's Second Card
        Game game = new Game(stubDeck);
        game.initialDeal();
        game.playerStands();
        game.dealerTurn();
        assertThat(game.determineOutcome()).isEqualTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    public void playerGetsBlackjack() {
        Deck stubDeck = new StubDeck(new Card(Suit.HEARTS, Rank.TEN), //Player's First Card
                                     new Card(Suit.SPADES, Rank.EIGHT), //Dealer's First Card
                                     new Card(Suit.DIAMONDS, Rank.ACE), //Player's Second Card
                                     new Card(Suit.CLUBS, Rank.KING)); //Dealer's Second Card
        Game game = new Game(stubDeck);
        game.initialDeal();
        assertThat(game.determineOutcome()).isEqualTo(GameOutcome.BLACKJACK);
    }

    @Test
    public void playerGetsBlackjackThenDoesAHit() {
        Deck stubDeck = new StubDeck(new Card(Suit.HEARTS, Rank.TEN), //Player's First Card
                                     new Card(Suit.SPADES, Rank.EIGHT), //Dealer's First Card
                                     new Card(Suit.DIAMONDS, Rank.ACE), //Player's Second Card
                                     new Card(Suit.CLUBS, Rank.KING)); //Dealer's Second Card
        Game game = new Game(stubDeck);
        game.initialDeal();
        assertThatThrownBy(() -> game.playerHits()).isInstanceOf(IllegalStateException.class);
    }
}
