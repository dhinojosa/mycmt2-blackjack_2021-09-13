package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.StubDeck;
import com.jitterted.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BlackjackControllerTest {
    private String heartsString = Suit.HEARTS.symbol();
    private String clubString = Suit.CLUBS.symbol();
    @Test
    void startGamePlayerHasCardsInHand() {
        Game game = new Game();
        BlackjackController blackjackController = new BlackjackController(() -> game);
        String result = blackjackController.startGame();
        assertThat(result).isEqualTo("redirect:/game");
        assertThat(game.playerHand().cards()).hasSize(2);
    }

    @Test
    void testViewCurrentlyGameForPlayer() {
        Game game = new Game(new StubDeck(
                new Card(Suit.CLUBS, Rank.TWO),
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.CLUBS, Rank.FIVE)
        ));
        BlackjackController blackjackController = new BlackjackController(() -> game);
        blackjackController.startGame(); //needed the cards dealt

        Model concurrentModel = new ConcurrentModel();
        String result  = blackjackController.getGame(concurrentModel);
        GameView gameView = (GameView) concurrentModel.getAttribute("gameView");

        assertThat(gameView.getPlayerCards()).containsExactly("2♣", "5♦");
        assertThat(result).isEqualTo("blackjack");
    }

    @Test
    void testViewCurrentlyGameForDealer() {
        Game game = new Game(new StubDeck(
                new Card(Suit.CLUBS, Rank.TWO),
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.CLUBS, Rank.FIVE)
        ));
        BlackjackController blackjackController = new BlackjackController(() -> game);
        blackjackController.startGame(); //needed the cards dealt

        Model concurrentModel = new ConcurrentModel();
        String result  = blackjackController.getGame(concurrentModel);
        GameView gameView = (GameView) concurrentModel.getAttribute("gameView");

        assertThat(gameView.getDealerCards()).containsExactly("10♠", "5♣");
        assertThat(result).isEqualTo("blackjack");
    }
}
