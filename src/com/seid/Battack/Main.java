package com.seid.Battack;

public class Main {

    public static void main(String[] args) {
	    Deck deck = new Deck();
	    deck.printDeck();
	    Player newPlayer = new Player("seidoglan");
        newPlayer.shuffleDeck(deck);
        deck.printDeck();
    }
}
