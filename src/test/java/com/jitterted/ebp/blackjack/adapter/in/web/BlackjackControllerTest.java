package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BlackjackControllerTest {

    @Test
    void startGamePlayerHasCardsInHand() {
        Game game = new Game();
        BlackjackController blackjackController = new BlackjackController(game);
        String result = blackjackController.startGame();
        assertThat(result).isEqualTo("redirect:/game");
        assertThat(game.playerHand().cards()).hasSize(2);
    }
}
