package com.seid.Battack;

import java.util.*;

public class BattackGame {

    private static final int MIN_GUESS = 5;

    private List<Player> players;
    private Player currentDealer;
    private Player decider;
    private Deck deck;
    private String currentTrump;
    private int currentMaxGuest = MIN_GUESS - 1;
    private boolean isTrumpPlayed = false;
    private boolean isRunning = true;
    private static final int LIMIT = 69;

    public void initGame() {
        players = createPlayers();
        currentDealer = getNextPlayer(null, players);
        deck = new Deck();
    }

    private void playTurn() {
        Map<Player, Card> playedCards = new LinkedHashMap<>();
        int[] playOrder = Helper.orderCalculatorPlay(players.indexOf(decider));
        for (int i : playOrder) {
            isTrumpPlayed = players.get(i).playCard(playedCards, currentTrump, isTrumpPlayed);
        }

        // for every card played, check the most valueable card
        // and add 1 point to the player's score who played the card
        int maxValue = 0;
        Player winnerOfTheTurn = null;
        for (Map.Entry<Player, Card> playerCardEntry : playedCards.entrySet()) {
            if (playerCardEntry.getValue().getValue() > maxValue) {
                maxValue = playerCardEntry.getValue().getValue();
                winnerOfTheTurn = playerCardEntry.getKey();
            }
        }

        winnerOfTheTurn.incrementRoundScore();
    }

    private void playRound() {
        currentDealer.shuffleDeck(deck);
        currentDealer.dealCards(deck, players);

        sortPlayersHand(players);

        // get player's guesses
        // highest guess starts the game and sets the 'koz'
        decider = decideStarter(players);
        currentTrump = decider.decideTrump(deck);

        for (int i = 0; i < 13; i++) {
            playTurn();
        }

        for (Player p : players) {
            if (p.getRoundScore() < p.getRoundBet() || p.getRoundScore() == 0) {
                p.changeTotalScore(-p.getRoundBet());
            } else {
                p.changeTotalScore(p.getRoundBet());
            }

            if (p.getTotalScore() >= LIMIT)
                isRunning = false;
        }
    }

    public void playGame() {
        // while (isRunning) {
        // resetPlayerRoundScores(players);
        // currentDealer = getNextPlayer(currentDealer, players);
        // playRound();
        // }

        currentDealer.shuffleDeck(deck);
        currentDealer.dealCards(deck, players);
        sortPlayersHand(players);

        for (Player player : players) {
            System.out.println(player.getPlayerName());
            for (Card card : player.getHand()) {
                System.out.print(card.getName() + " | ");
            }
            System.out.println();
        }

        // get player's guesses
        // highest guess starts the game and sets the 'koz'
        decider = decideStarter(players);
        currentTrump = decider.decideTrump(deck);
        playTurn();
    }

    /*
     * TODO when implementing multiplayer, add something like timer and make this
     * deciding process like real game
     */
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
                p.setRoundBet(currentMaxGuest);
                maxGuesser = p;
            } else {
                p.setRoundBet(0);
            }
        }
        if (maxGuesser == null) {
            players.get(order[0]).setRoundBet(4);
            return players.get(order[0]);
        }
        return maxGuesser;
    }

    private void sortPlayersHand(List<Player> players) {
        for (Player p : players) {
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

    private void resetPlayerRoundScores(List<Player> players) {
        for (Player p : players) {
            p.resetRoundScore();
        }
    }
}
