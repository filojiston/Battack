package com.seid.Battack;

import java.util.*;

public class Player implements Dealer {
    private String playerName;
    private List<Card> hand;
    private int totalScore;
    private int roundScore;
    private int roundBet;

    private static final Scanner scanner = new Scanner(System.in);

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
        int[] dealOrder = Helper.orderCalculator(index);

        int cardIndex = 0;

        // deal the cards by 3-3-3-3-1 with dealing order
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    players.get(dealOrder[i]).getHand().add(cards.getCards().get(cardIndex));
                    cardIndex++;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            players.get(i).getHand().add(cards.getCards().get(cardIndex));
            cardIndex++;
        }
    }

    public String decideTrump(Deck deck) {
        String trump = "";
        do {
            System.out.print("Kozu seçiniz: ");
            trump = scanner.nextLine().toLowerCase();
        } while (!Arrays.asList(Deck.getTypes()).contains(trump));
        return trump;
    }

    public String getGuess(int biggerGuess) {
        String guess;
        do {
            System.out.print("Kaçtan giriyorsunuz: ");
            guess = scanner.nextLine();
        } while (!Helper.checkGuess(guess, biggerGuess));
        return guess;
    }

    // TODO resolve the logic for playing the turn - calculating scores etc.
    public boolean playCard(Map<Player, Card> playedCards, String currentTrump, boolean isTrumpPlayed) {
        String cardName = "";
        System.out.print("Hangi kartı oynayacaksınız?: ");
        cardName = scanner.nextLine();
        while (!Helper.checkPlayedCard(cardName, playedCards, currentTrump, hand, isTrumpPlayed)) {
            System.out.print("Hatalı kart seçimi. Hangi kartı oynayacaksınız?: ");
            cardName = scanner.nextLine();
        }

        Card cardToPlay = Helper.findCardByName(hand, cardName);
        // System.out.println(cardToPlay.getName() + " " + cardToPlay.getValue());
        playedCards.put(this, cardToPlay);

        return cardToPlay.getType().compareTo(currentTrump) == 0;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public String getPlayerName() { return playerName; }

    public List<Card> getHand() { return hand; }

    public int getTotalScore() {
        return totalScore;
    }

    public void resetTotalScore() {
        this.totalScore = 0;
    }

    public void changeTotalScore(int num) {
        this.totalScore += num;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void incrementRoundScore() {
        this.roundScore++;
    }

    public void resetRoundScore() {
        this.roundScore = 0;
    }

    public int getRoundBet() {
        return roundBet;
    }

    public void setRoundBet(int roundBet) {
        this.roundBet = roundBet;
    }
 }
