package com.seid.Battack;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Deck deck = new Deck();
        BattackGame game = new BattackGame();
        List<Player> players = game.createPlayers();
        Player currentDealer = game.setDealer(null, players);
        currentDealer.shuffleDeck(deck);
        currentDealer.dealCards(deck, players);

        for (Player p: players) {
            System.out.println(p.getPlayerName() + "'s hand");
            for (Card c: p.getHand()) {
                System.out.print(c.getName() + " | ");
            }
            System.out.println();
        }
    }
}
