package com.jitterted.ebp.blackjack.domain;

public enum GameOutcome {
    PLAYER_BUSTS("You Busted, so you lose. 💸"),
    PLAYER_BEATS_DEALER("You beat the Dealer! 💵"),
    DEALER_BUSTS("Dealer went BUST, Player wins! Yay for you!! 💵"),
    PLAYER_PUSHES_DEALER("Push: Nobody wins, we'll call it even."),
    DEALER_BEATS_PLAYER("You lost to the Dealer. 💸");

    private final String displayString;

    GameOutcome(String displayString) {
        this.displayString = displayString;
    }

    public String displayString() {
        return displayString;
    }
}
