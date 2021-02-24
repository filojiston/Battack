package com.seid.Battack;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Helper {
    public static boolean checkGuess(String guess, int biggerGuess) {
        // guess must be an integer between 5 (or bigger than last guess) - 13
        // or simply pass
        if (guess.compareTo("pas") == 0)
            return true;

        if (guess.length() < 1 || guess.length() > 2)
            return false;

        for (int i = 0; i < guess.length(); i++) {
            int ascii = guess.charAt(i);
            if (ascii < 48 || ascii > 57)
                return false;
        }

        int theGuess = Integer.parseInt(guess);

        return theGuess > biggerGuess && theGuess <= 13;
    }

    public static int[] orderCalculator(int index) {
        int[] dealOrder;
        switch (index) {
            case 0:
                dealOrder = new int[] { 1, 2, 3, 0 };
                break;
            case 1:
                dealOrder = new int[] { 2, 3, 0, 1 };
                break;
            case 2:
                dealOrder = new int[] { 3, 0, 1, 2 };
                break;
            default:
                dealOrder = new int[] { 0, 1, 2, 3 };
        }
        return dealOrder;
    }

    public static boolean checkPlayedCard(String cardToPlay, Map<Player, Card> playedCards, String currentTrump,
            List<Card> playerHand, boolean isTrumpPlayed) {
        // card cannot be a trump if still not a trump played
        // if its the first card to be played, it can be anything but not a trump
        // it its not, it must be from same card type or a trump

        // and card has to be in players hand etc.

        // first, check if player has the card
        boolean hasCard = false;
        for (Card card : playerHand) {
            if (card.getName().compareTo(cardToPlay) == 0)
                hasCard = true;
        }

        if (!hasCard)
            return false;

        Card theCard = findCardByName(playerHand, cardToPlay);

        // check if it is the first played card
        if (playedCards.size() == 0) {
            return isTrumpPlayed || theCard.getType().compareTo(currentTrump) != 0;
        } else {
            Card firstPlayed = getFirstPlayedCard(playedCards);

            var sameTypeCards = playerHand.stream()
                    .filter(card -> card.getType().compareTo(firstPlayed.getType()) == 0);
            if (sameTypeCards.count() == 0) {
                // player doesnt have same card type in hand, so has to play trump card
                var trumpCards = playerHand.stream().filter(card -> card.getType().compareTo(currentTrump) == 0);
                if (trumpCards.count() == 0) // if player does not have any trump card, play any card on hand
                    return true;
                else {
                    // if any trump card played before, player has to play a card with bigger value
                    boolean isTrumpPlayedOnTurn = false;
                    for (Map.Entry<Player, Card> entry : playedCards.entrySet()) {
                        if (entry.getValue().getType().compareTo(currentTrump) == 0)
                            isTrumpPlayedOnTurn = true;
                    }
                    if (isTrumpPlayedOnTurn) {
                        // find max valued trump card
                        // TODO finish this function
                        var playedTrumps = playedCards.entrySet().stream()
                                .filter(entry -> entry.getValue().getType().compareTo(currentTrump) == 0);
                        int maxValue = 0;

                        // for every card
                        boolean hasBigger = trumpCards.map(card -> card.getValue() > maxValue).count() > 0;
                    } else { // else, player can play any trump card in hand
                        return theCard.getType().compareTo(currentTrump) == 0;
                    }
                }
            } else { // player have same card type in hand

            }

            // you have the card with same type - this is the first played card
            // you dont have a card with same type, but you have a trump card
            // you dont have a card with same type and you dont have a trump card

        }

        return true;

    }

    public static Card findCardByName(List<Card> playerHand, String cardName) {
        for (Card c : playerHand) {
            if (c.getName().compareTo(cardName) == 0)
                return c;
        }

        return null;
    }

    public static int[] orderCalculatorPlay(int index) {
        int[] playOrder;
        switch (index) {
            case 0:
                playOrder = new int[] { 0, 1, 2, 3 };
                break;
            case 1:
                playOrder = new int[] { 1, 2, 3, 0 };
                break;
            case 2:
                playOrder = new int[] { 2, 3, 0, 1 };
                break;
            default:
                playOrder = new int[] { 3, 0, 1, 2 };
        }
        return playOrder;
    }

    public static Card getFirstPlayedCard(Map<Player, Card> playedCards) {
        return playedCards.entrySet().iterator().next().getValue();
    }

    public static Card getLastPlayedCard(Map<Player, Card> playedCards) {
        return ((Map.Entry<Player, Card>) playedCards.entrySet().toArray()[playedCards.size() - 1]).getValue();
    }
}
