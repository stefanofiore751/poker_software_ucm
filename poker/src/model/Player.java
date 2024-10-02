package model;

import java.util.LinkedList;

public class Player {
	
    private final Hand _hand;

    public Player() {
        _hand = new Hand();
    }

    public Player(LinkedList<Card> cards) {
    	_hand = new Hand(cards);
    }

    public void addCard(Card card) {
        _hand.addCard(card);
    }

    public LinkedList<Card> getCards() {
        return _hand.getHand();
    }
}
