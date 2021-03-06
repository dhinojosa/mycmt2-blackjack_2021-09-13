package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;

public class Game {
    private final Deck deck;
    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();
    private final GameMonitor gameMonitor;
    private boolean playerDone;

    public Game() {
        this(new Deck(), game -> {});
    }

    public Game(Deck deck) {
        this(deck, game -> {});
    }

    public Game(GameMonitor gameMonitor) {
        this(new Deck(), gameMonitor);
    }

    public Game(Deck deck, GameMonitor gameMonitor) {
        this.deck = deck;
        this.gameMonitor = gameMonitor;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
        if (playerHand.isBlackjack()) {
            this.playerDone = true;
            gameMonitor.roundCompleted(this);
        }
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public GameOutcome determineOutcome() {
        if (playerHand.isBusted()) {
            return GameOutcome.PLAYER_BUSTS;
        } else if (dealerHand.isBusted()) {
            return GameOutcome.DEALER_BUSTS;
        } else if (playerHand.isBlackjack()) {
            return GameOutcome.BLACKJACK;
        } else if (playerHand.beats(dealerHand)) {
            return GameOutcome.PLAYER_BEATS_DEALER;
        } else if (playerHand.pushes(dealerHand)) {
            return GameOutcome.PLAYER_PUSHES_DEALER;
        } else {
            return GameOutcome.DEALER_BEATS_PLAYER;
        }
    }

    public void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic (<=16 must hit, =>17 must stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
        gameMonitor.roundCompleted(this);
    }

    public Hand dealerHand() {
        return dealerHand;
    }

    public Hand playerHand() {
        return playerHand;
    }

    public void playerStands() {
        playerDone = true;
    }

    public void playerHits() {
        if (playerHand.isBlackjack()) throw new IllegalStateException("You're new here, aren't you?");
        playerHand.drawFrom(deck);
        playerDone = playerHand.isBusted();
        if (playerDone) {
            gameMonitor.roundCompleted(this);
        }
    }

    public boolean isPlayerDone() {
        return playerDone;
    }
}
