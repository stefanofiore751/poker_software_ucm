package model;

import model.Card;

import java.util.LinkedList;

public class Hand {

    private final LinkedList<Card> _hand;
    
    public Hand() {
    	_hand = new LinkedList<>();
    }
    
    public Hand(LinkedList<Card> cards) {
    	_hand = cards;
    }
    
    public LinkedList<Card> getHand() {
    	return _hand;
    }
    
    public void addCard(Card card) {
        _hand.add(card);
    }
}
