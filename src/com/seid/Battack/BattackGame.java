package com.seid.Battack;

import java.util.ArrayList;
import java.util.List;

public class BattackGame {



    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("seid"));
        players.add(new Player("badir"));
        players.add(new Player("hus"));
        players.add(new Player("tk"));
        return players;
    }

    public Player setDealer(Player currentDealer, List<Player> players) {
        int currentIndex = players.indexOf(currentDealer);
        int dealerIndex = currentIndex >= 0 && currentIndex < 3 ? currentIndex + 1 : 0;

        return players.get(dealerIndex);
    }
}
