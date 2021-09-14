package com.jitterted.ebp.blackjack.domain;

public class Game {
    private final Deck deck;
    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();
    private boolean playerDone;

    public Game() {
        deck = new Deck();
    }

    public Game(Deck deck) {
        this.deck = deck;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
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
        playerHand.drawFrom(deck);
        playerDone = playerHand.isBusted();
    }

    public boolean isPlayerDone() {
        return playerDone;
    }
}
