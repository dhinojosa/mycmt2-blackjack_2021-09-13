package com.jitterted.ebp.blackjack.adapter.out.gamemonitor;

import com.jitterted.ebp.blackjack.domain.Game;

public class GameResultDTO {
    private final String playerName;
    private final String outcome;
    private final String playerHandValue;
    private final String dealerHandValue;

    public GameResultDTO(String playerName, String outcome, String playerHandValue, String dealerHandValue) {
        this.playerName = playerName;
        this.outcome = outcome;
        this.playerHandValue = playerHandValue;
        this.dealerHandValue = dealerHandValue;
    }

    public static GameResultDTO of(Game game) {
        return new GameResultDTO("Danno!",
                          game.determineOutcome().toString(),
                          game.playerHand().displayValue(),
                          game.dealerHand().displayValue());
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getOutcome() {
        return outcome;
    }

    public String getPlayerHandValue() {
        return playerHandValue;
    }

    public String getDealerHandValue() {
        return dealerHandValue;
    }
}
