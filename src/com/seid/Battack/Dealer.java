package com.seid.Battack;

import java.util.List;

public interface Dealer {
    void shuffleDeck(Deck cards);
    void dealCards(Deck cards, List<Player> players);
}
