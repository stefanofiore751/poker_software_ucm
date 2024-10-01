package logic;

public class Card {

    private char _value;

    private char _suit;

    private boolean used;

    public Card(char v, char s) {
        _value = v;
        _suit = s;
        used = false;
    }

    public char getvalue() {
        return _value;
    }

    public char getsuit() {
        return _suit;
    }

    public String toString() {
        return "" + _value + _suit;
    }
}