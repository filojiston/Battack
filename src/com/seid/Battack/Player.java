package com.seid.Battack;

import java.util.Collections;

public class Player implements Dealer{
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void shuffleDeck(Deck deck) {
        Collections.shuffle(deck.getCards());
    }
}
