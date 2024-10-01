package logic;

import java.util.LinkedList;

public class Hand {
	
    private final LinkedList<Card> _hand;
    
    public Hand() {
    	_hand = new LinkedList<>();
    }
    
    public LinkedList<Card> getHand() {
    	return _hand;
    }
}
