package ludopata.sl;

public class Carta {

    private char value;

    private char suit;

    public Carta (char v, char s) {
        value = v;
        suit = s;
    }

    public String toString() {
        return ""+value+suit;
    }

    public char Getvalue(){
        return value;
    }

    public char Getsuit(){
        return suit;
    }

}


