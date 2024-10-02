package logic;

public enum Suits {
    // 0 : hearts, 1 : diamonds, 2 : clubs, 3 : spades
	h, d, c, s;
	
	
	public Suits suitIndex(char suit) {
		switch (suit) {
		case 'h':
			return h;
		case 'd':
        	return d;
        case 'c':
        	return c;
        case 's':
        	return s;
		}
	}
	
	public int valueIndex(char value) {
		switch (value) {
    	case 'A':
    		return 0;
    	case '2':
    		return 1;
    	case '3':
    		return 2;
    	case '4':
    		return 3;
    	case '5':
    		return 4;
    	case '6':
    		return 5;
    	case '7':
    		return 6;
    	case '8':
    		return 7;
    	case '9':
    		return 8;
    	case 'T':
    		return 9;
    	case 'J':
    		return 10;
    	case 'Q':
    		return 11;
    	case 'K':
    		return 12;
		}
	}
}