package com.jitterted.ebp.blackjack.domain;
import com.jitterted.ebp.blackjack.domain.port.GameMonitor;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class GameMonitorTest {

    @Test
    public void playerStandsThenGameIsOverAndResultsSentToMonitor() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        // the rest of the setup...
        Game game = new Game(gameMonitorSpy);
        game.initialDeal();
        game.playerStands();
        game.dealerTurn();
        // verify that the roundCompleted method was called with any instance of a Game class
        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitsAndGoesBustAndResultsSentToMonitor() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(new StubDeck(
                new Card(Suit.CLUBS, Rank.TEN), //player
                new Card(Suit.SPADES, Rank.TEN), //dealer
                new Card(Suit.DIAMONDS, Rank.EIGHT), //player
                new Card(Suit.CLUBS, Rank.SEVEN), //dealer
                new Card(Suit.HEARTS, Rank.NINE) //player
        ), gameMonitorSpy);
        // the rest of the setup...
        game.initialDeal();
        game.playerHits();
        // verify that the roundCompleted method was called with any instance of a Game class
        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitsAndDoesntGoesBustAndResultsNotSentToMonitor() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(new StubDeck(
                new Card(Suit.CLUBS, Rank.TEN), //player
                new Card(Suit.SPADES, Rank.TEN), //dealer
                new Card(Suit.DIAMONDS, Rank.EIGHT), //player
                new Card(Suit.CLUBS, Rank.SEVEN), //dealer
                new Card(Suit.HEARTS, Rank.TWO) //player
        ), gameMonitorSpy);
        // the rest of the setup...
        game.initialDeal();
        game.playerHits();
        verify(gameMonitorSpy, never()).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitBlackjack() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(new StubDeck(
                new Card(Suit.CLUBS, Rank.KING), //player
                new Card(Suit.SPADES, Rank.TEN), //dealer
                new Card(Suit.DIAMONDS, Rank.ACE), //player
                new Card(Suit.CLUBS, Rank.SEVEN) //dealer
        ), gameMonitorSpy);
        // the rest of the setup...
        game.initialDeal();
        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }
}
