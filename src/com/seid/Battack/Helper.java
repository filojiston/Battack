package com.seid.Battack;

import java.util.List;

public class Helper {
    public static boolean checkGuess(String guess, int biggerGuess) {
        // guess must be an integer between 5 (or bigger than last guess) - 13
        // or simply pass
        if (guess.compareTo("pas") == 0)    return true;

        if (guess.length() < 1 || guess.length() > 2) return false;

        for (int i = 0; i < guess.length(); i++) {
            int ascii = guess.charAt(i);
            if (ascii < 48 || ascii > 57)   return false;
        }

        int theGuess = Integer.parseInt(guess);

        return theGuess > biggerGuess && theGuess <= 13;
    }

    public static int[] orderCalculator(int index) {
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
            default:
                dealOrder = new int[] {0, 1, 2, 3};
        }
        return dealOrder;
    }

    public static boolean checkPlayedCard(String cardToPlay, List<Card> playerHand, boolean isTrumpPlayed) {
        return true;
    }

    public static Card findCardByName(List<Card> playerHand, String cardName) {
        for (Card c: playerHand) {
            if (c.getName().compareTo(cardName) == 0)   return c;
        }

        return null;
    }
}
