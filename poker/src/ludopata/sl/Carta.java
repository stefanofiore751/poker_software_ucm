package ludopata.sl;

public class Carta {

    private char value;

    private char suit;

    public Carta(char v, char s) {
        value = v;
        suit = s;
    }

    public char getvalue() {
        return value;
    }

    public char getsuit() {
        return suit;
    }

    public String toString() {
        return "" + value + suit;
    }
}
