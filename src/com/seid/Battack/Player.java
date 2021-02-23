package com.seid.Battack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Dealer {
    private String playerName;

    private List<Card> hand;

    public Player(String playerName) {
        this.playerName = playerName;
        hand = new ArrayList<>();
    }

    @Override
    public void shuffleDeck(Deck deck) {
        Collections.shuffle(deck.getCards());
    }

    @Override
    public void dealCards(Deck cards, List<Player> players) {
        int index = players.indexOf(this);

        // calculate dealing order
        int[] dealOrder;
        switch (index) {
            case 0:
                dealOrder = new int[] {1, 2, 3, 0};
                break;
            case 1:
                dealOrder = new int[] {2, 3, 0, 1};
                break;
            case 2:
                dealOrder = new int[] {3, 0, 1, 2};
                break;
            case 3:
                dealOrder = new int[] {0, 1, 2, 3};
                break;
        }

        int cardIndex = 0;

        // deal the cards by 3-3-3-3-1 with dealing order
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    players.get(i).getHand().add(cards.getCards().get(cardIndex));
                    cardIndex++;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            players.get(i).getHand().add(cards.getCards().get(cardIndex));
            cardIndex++;
        }
    }


    public String getPlayerName() { return playerName; }

    public List<Card> getHand() { return hand; }
 }
