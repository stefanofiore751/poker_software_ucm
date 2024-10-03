package model;

public enum Suits {
    // 0 : hearts, 1 : diamonds, 2 : clubs, 3 : spades
	h, d, c, s;
	
	
	public Suits suitIndex(char suit) {
        return switch (suit) {
            case 'h' -> h;
            case 'd' -> d;
            case 'c' -> c;
            case 's' -> s;
            default -> null;
        };
    }
	
	public int valueIndex(char value) {
        return switch (value) {
            case 'A' -> 0;
            case '2' -> 1;
            case '3' -> 2;
            case '4' -> 3;
            case '5' -> 4;
            case '6' -> 5;
            case '7' -> 6;
            case '8' -> 7;
            case '9' -> 8;
            case 'T' -> 9;
            case 'J' -> 10;
            case 'Q' -> 11;
            case 'K' -> 12;
            default -> 0;
        };
    }
}