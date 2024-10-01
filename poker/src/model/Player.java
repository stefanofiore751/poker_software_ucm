package logic;

import java.util.LinkedList;

public class Player {
    private final LinkedList<Card> cards;

    public Player() {
        cards = new LinkedList<>();
    }

    public Player(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public LinkedList<Card> getCards() {
        return cards;
    }
}
