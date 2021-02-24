package com.seid.Battack;

import java.util.ArrayList;
import java.util.List;

public class BattackGame {

    private static final int MIN_GUESS = 5;

    private List<Player> players;
    private Player currentDealer;
    private Player decider;
    private Deck deck;
    private String currentTrump;
    private int currentMaxGuest = MIN_GUESS - 1;
    private boolean isTrumpPlayed = false;

    public void initGame() {
        players = createPlayers();
        currentDealer = getNextPlayer(null, players);
        deck = new Deck();
    }

    private void playTurn() {
        List<Card> playedCards = new ArrayList<>();
        decider.playCard(playedCards, currentTrump, false);
    }

    public void playGame() {
        currentDealer.shuffleDeck(deck);
        currentDealer.dealCards(deck, players);

        sortPlayersHand(players);

        // get player's guesses
        // highest guess starts the game and sets the 'koz'
        decider = decideStarter(players);
        currentTrump = decider.decideTrump(deck);

        System.out.println(decider.getPlayerName());
        System.out.println(currentTrump);

        for (Player p: players) {
            System.out.println(p.getPlayerName() + "'s hand");
            for (Card c: p.getHand()) {
                System.out.print(c.getName() + " | ");
            }
            System.out.println();
        }

        playTurn();
    }

    /* TODO when implementing multiplayer, add something like timer
     and make this deciding process like real game */
    private Player decideStarter(List<Player> players) {
        Player maxGuesser = null;
        int index = players.indexOf(currentDealer);
        int[] order = Helper.orderCalculator(index);
        for (int i : order) {
            Player p = players.get(i);
            System.out.println("Current player : " + p.getPlayerName());
            String guess = p.getGuess(currentMaxGuest);
            if (guess.compareTo("pas") != 0) {
                currentMaxGuest = Integer.parseInt(guess);
                maxGuesser = p;
            }
        }
        return maxGuesser != null ? maxGuesser : players.get(order[0]);
    }

    private void sortPlayersHand(List<Player> players) {
        for (Player p: players) {
            p.getHand().sort((o1, o2) -> {
                if (o1.getType().compareTo(o2.getType()) == 0) {
                    return o1.getValue() - o2.getValue();
                }
                return o1.getType().compareTo(o2.getType());
            });
        }
    }

    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("seid"));
        players.add(new Player("badir"));
        players.add(new Player("hus"));
        players.add(new Player("tk"));
        return players;
    }

    private Player getNextPlayer(Player currentPlayer, List<Player> players) {
        int currentIndex = players.indexOf(currentPlayer);
        int nextIndex = currentIndex >= 0 && currentIndex < 3 ? currentIndex + 1 : 0;

        return players.get(nextIndex);
    }
}
