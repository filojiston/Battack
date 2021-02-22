package com.seid.Battack;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    private static final String[] types = {"Sinek", "Maça", "Kupa", "Karo"};
    private static final String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Bacak", "Kız", "Papaz", "As"};

    public Deck() {
        initializeDeck();
    }

    private void initializeDeck() {
        for (String type: types) {
            for (int i = 0; i < names.length; i++) {
                cards.add(new Card(type + " " + names[i], i + 2));
            }
        }
    }

    // TODO remove this method
    public void printDeck() {
        for (Card c: cards) {
            System.out.println(c.getName() + " " + c.getValue());
        }
    }

    public List<Card> getCards() {
        return cards;
    }

}
