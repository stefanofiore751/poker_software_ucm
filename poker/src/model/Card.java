package model;

import logic.Suits;

public class Card {

    private char _value;

    private char _suit;
    
    private int _num;
    
    private Suits suit;

    private boolean _used;

    public Card(char v, char s) {
        _value = v;
        _suit = s;
        parseValue(v);
        assignSuit(s);
        _used = false;
    }

    public char getvalue() {
        return _value;
    }

    public char getsuit() {
        return _suit;
    }
    
    private void parseValue(char value) {
    	switch (value) {
    		case 'A':
    			_num = 14;
    			break;
			
    		case 'K':
    			_num = 13;
    			break;
    			
    		case 'Q':
    			_num = 12;
    			break;
    			
    		case 'J':
    			_num = 11;
    			break;
    			
    		case 'T':
    			_num = 10;
    			break;
    			
    		case '9':
    			_num = 9;
    			break;
    			
    		case '8':
    			_num = 8;
    			break;
    			
    		case '7':
    			_num = 7;
    			break;
    			
    		case '6':
    			_num = 6;
    			break;
    			
    		case '5':
    			_num = 5;
    			break;
    			
    		case '4':
    			_num = 4;
    			break;
    			
    		case '3':
    			_num = 3;
    			break;
    			
    		case '2':
    			_num = 2;
    			break;
		}
    }
    
    private void assignSuit(char s) {
    	switch(s) {
			case 'h':
				suit = Suits.h;
				break;
			
			case 'd':
				suit = Suits.d;
				break;
				
			case 'c':
				suit = Suits.c;
				break;
			
			case 's':
				suit = Suits.s;
				break;
		}
	}

    public String toString() {
        return "" + _value + _suit;
    }
}