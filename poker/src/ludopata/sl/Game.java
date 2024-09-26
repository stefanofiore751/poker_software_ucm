package ludopata.sl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;

public class Game {
    // Counter number of cards of each suit:
    // 0 : hearts, 1 : diamonds, 2 : clubs, 3 : spades
    private final int[] suits_cont;

    // Counter number of cards of each kind:
    int[] value_count;

    Jugada jugada;

    private final LinkedList<Carta> mano;

    public Game() {
        suits_cont = new int[4];
        value_count = new int[13];
        jugada = new Jugada();
        mano = new LinkedList<>();
    }

    void readInput() {
        // InputStream in =
        // Main.class.getClassLoader().getResourceAsStream("entrada.txt");
        try {
            // Open input file
            FileInputStream in = new FileInputStream("entrada.txt");
            char value;
            char suit;

            // Read file
            while (in.available() > 0) {
                value = (char) in.read();
                suit = (char) in.read();

                countSuits_Value(value, suit);

                mano.add(new Carta(value, suit));
            }

            // Close file
            in.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void writeoutput() {
        Jugada.Jugadas StrongestJugada = GetStrongestJugada();

        try {
            FileOutputStream out = new FileOutputStream("output.txt");
            // Scrivere l'output su file
            String result = getMano() + "-Best Hand: " + StrongestJugada;
            if (jugada.getValue(Jugada.Jugadas.GUTSHOT))
                result += "\n-DRAW: Straight Gutshot";
            if (jugada.getValue(Jugada.Jugadas.FLUSH_DRAW))
                result += "\n-DRAW: flush";
            out.write(result.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void countSuits_Value(char value, char suit) {
        switch (suit) {
            case 'h':
                suits_cont[0]++;
                break;
            case 'd':
                suits_cont[1]++;
                break;
            case 'c':
                suits_cont[2]++;
                break;
            case 's':
                suits_cont[3]++;
                break;
        }
        switch (value) {
            case 'A':
                value_count[0]++;
                break;
            case '2':
                value_count[1]++;
                break;
            case '3':
                value_count[2]++;
                break;
            case '4':
                value_count[3]++;
                break;
            case '5':
                value_count[4]++;
                break;
            case '6':
                value_count[5]++;
                break;
            case '7':
                value_count[6]++;
                break;
            case '8':
                value_count[7]++;
                break;
            case '9':
                value_count[8]++;
                break;
            case 'T':
                value_count[9]++;
                break;
            case 'J':
                value_count[10]++;
                break;
            case 'Q':
                value_count[11]++;
                break;
            case 'K':
                value_count[12]++;
                break;
        }
    }

    String getMano() {
        StringBuilder manostring = new StringBuilder();
        for (Carta c : mano)
            manostring.append(c.toString());
        return manostring.toString();
    }

    private Jugada.Jugadas GetStrongestJugada() {
        straightFlush();
        if (jugada.getValue(Jugada.Jugadas.STRAIGHT_FLUSH))
            return Jugada.Jugadas.STRAIGHT_FLUSH;
        poker();
        if (jugada.getValue(Jugada.Jugadas.POKER))
            return Jugada.Jugadas.POKER;
        full();
        if (jugada.getValue(Jugada.Jugadas.FULL_HOUSE))
            return Jugada.Jugadas.FULL_HOUSE;
        if (jugada.getValue(Jugada.Jugadas.FLUSH))
            return Jugada.Jugadas.FLUSH;
        if (jugada.getValue(Jugada.Jugadas.STRAIGHT))
            return Jugada.Jugadas.STRAIGHT;
        if (jugada.getValue(Jugada.Jugadas.TRIO))
            return Jugada.Jugadas.TRIO;
        if (jugada.getValue(Jugada.Jugadas.PAIR))
            return Jugada.Jugadas.PAIR;
        return Jugada.Jugadas.HIGH_CARD;
    }

    // I) Straight Flush
    private void straightFlush() {
        Color();
        Straight();
        if (jugada.getValue(Jugada.Jugadas.FLUSH) && jugada.getValue(Jugada.Jugadas.STRAIGHT))
            jugada.updateMap(Jugada.Jugadas.STRAIGHT_FLUSH, true);
    }

    // II) Poker (four-of-a-kind)
    public void poker() {
        for (int j : value_count) {
            if (j == 4) {
                jugada.updateMap(Jugada.Jugadas.POKER, true);
                return;
            }
        }
    }

    // III) Full House (El Barco)
    private void full() {
        trio();
        pair();
        if (jugada.getValue(Jugada.Jugadas.TRIO) && jugada.getValue(Jugada.Jugadas.PAIR))
            jugada.updateMap(Jugada.Jugadas.FULL_HOUSE, true);
    }

    // IV) Flush (color)
    private void Color() {
        if (suits_cont[0] == 5 || suits_cont[1] == 5 || suits_cont[2] == 5 || suits_cont[3] == 5)
            jugada.updateMap(Jugada.Jugadas.FLUSH, true);
        if (suits_cont[0] == 4 || suits_cont[1] == 4 || suits_cont[2] == 4 || suits_cont[3] == 4)
            jugada.updateMap(Jugada.Jugadas.FLUSH_DRAW, true);

    }

    // V) Straight (escalera)
    void Straight(){
        int[] new_value_count = new int[value_count.length + 1]; // new array to have the ace also at the end
        System.arraycopy(value_count, 0, new_value_count, 0, value_count.length); //copy of value count
        new_value_count[new_value_count.length - 1] = value_count[0];//adding the ace

        boolean twoFound = false;
        int consecutive = 0,gaps = 0;
        for (int i = 0; i < new_value_count.length; i++) {
            int j = new_value_count[i];
            //if there's 2 time a card with 2 copy it can't be a straight or a draw
            if (j == 2) {
                if (!twoFound)
                    twoFound = true;
                else if(i != new_value_count.length - 1) return; // if there was already a card with 2 copy and we are checking the last one it's the ace again so you don't have to exit
            } else if (j > 2) return;
            if (j == 1 || j == 2) { //if the card has one or two copy you start the consecutive counter
                consecutive++;
                if (consecutive == 5 && gaps == 0) { // if there are five consecutive it's a straight
                    jugada.updateMap(Jugada.Jugadas.STRAIGHT, true);
                    jugada.updateMap(Jugada.Jugadas.OPEN_ENDED, false);
                    return;
                }
                if (consecutive == 4 && gaps == 1) { //if there's 4 card and a gap is a gutshot
                    jugada.updateMap(Jugada.Jugadas.GUTSHOT, true);
                    return;
                } else if (consecutive == 4) { // if there's 4 card with no gap it can be a open ended draw it it's not a straight(that's why this doesn't have the return
                    jugada.updateMap(Jugada.Jugadas.OPEN_ENDED, true);
                }

            } else if (consecutive != 0) gaps++; // if the consecutive already started if there is  hole ++gaps
            if (gaps == 2) {
                consecutive = 0;
                gaps = 0;
            }
        }
    }

    // VI) Three-of-a-kind (trio)
    private void trio() {
        for (int j : value_count) {
            if (j == 3) {
                jugada.updateMap(Jugada.Jugadas.TRIO, true);
                return;
            }
        }
    }

    // VII) Two-pair (pareja)
    private void pair() {
        for (int j : value_count) {
            if (j == 2) {
                jugada.updateMap(Jugada.Jugadas.PAIR, true);
                return;
            }
        }
    }

    public void measurePerformance(int numberOfHands) {
        long startTime = System.nanoTime();

        for (int i = 0; i < numberOfHands; i++) {
            mano.clear();
            resetCounters();

            readInput();

            Jugada.Jugadas strongestHand = GetStrongestJugada();

        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("Processed " + numberOfHands + " hands in " + (duration / 1_000_000) + " milliseconds");
    }

    private void resetCounters() {
        Arrays.fill(suits_cont, 0);
        Arrays.fill(value_count, 0);
        jugada = new Jugada();
    }

}
