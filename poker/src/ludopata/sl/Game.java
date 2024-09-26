package ludopata.sl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.LinkedList;

import static ludopata.sl.Jugada.*;

public class Game {
    // Counter number of cards of each suit:
    // 0 : hearts, 1 : diamonds, 2 : clubs, 3 : spades
    private final int[] suits_cont;

    // Counter number of cards of each kind:
    int[] value_count;

    Jugada jugada;

    private final LinkedList<Carta> mano;

    private final LinkedList<Carta> play;

    public Game() {
        suits_cont = new int[4];
        value_count = new int[13];
        jugada = new Jugada();
        mano = new LinkedList<>();
        play = new LinkedList<>();
    }

    public Game(LinkedList<Carta> mano) {
        suits_cont = new int[4];
        value_count = new int[13];
        jugada = new Jugada();
        this.mano = mano;
    }

    void readInput() {
        // Main.class.getClassLoader().getResourceAsStream("entrada.txt");
        try {
            // Open input file
            FileInputStream in = new FileInputStream("..\\poker_software_ucm\\poker\\resources\\entrada.txt");
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
        String StrongestJugada = GetStrongestJugada();

        try {
            FileOutputStream out = new FileOutputStream("..\\poker_software_ucm\\poker\\resources\\output.txt");
            // Scrivere l'output su file
            String result = getMano() + "-Best Hand: " + StrongestJugada;
            if (jugada.getValue(Jugadas.GUTSHOT))
                result += "\n-DRAW: Straight Gutshot";
            if (jugada.getValue(Jugadas.FLUSH_DRAW))
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

    String getPlay() {
        StringBuilder playstring = new StringBuilder();
        for (Carta c : play)
            playstring.append(c.toString());
        return playstring.toString();
    }

    private String GetStrongestJugada() {
        straightFlush();
        if (jugada.getValue(Jugadas.STRAIGHT_FLUSH))
            return Jugadas.STRAIGHT_FLUSH.toString();
        poker();
        if (jugada.getValue(Jugadas.POKER))
            return Jugadas.POKER + " -- " + getPlay();
        full();
        if (jugada.getValue(Jugadas.FULL_HOUSE))
            return Jugadas.FULL_HOUSE + " -- " + getPlay();
        if (jugada.getValue(Jugadas.FLUSH))
            return Jugadas.FLUSH.toString();
        if (jugada.getValue(Jugadas.STRAIGHT))
            return Jugadas.STRAIGHT.toString();
        if (jugada.getValue(Jugadas.TRIO))
            return Jugadas.TRIO + " -- " + getPlay();
        if (jugada.getValue(Jugadas.PAIR))
            return Jugadas.PAIR + " -- " + getPlay();
        return Jugadas.HIGH_CARD.toString();
    }

    // I) Straight Flush
    private void straightFlush() {
        Color();
        Straight();
        if (jugada.getValue(Jugadas.FLUSH) && jugada.getValue(Jugadas.STRAIGHT))
            jugada.updateMap(Jugadas.STRAIGHT_FLUSH, true);
    }

    // II) Poker (four-of-a-kind)
    public void poker() {
        for (int i = 0 ; i< value_count.length; i++) {
            if (value_count[i] == 4) {
                jugada.updateMap(Jugadas.POKER, true);
                usedCards(i);
                return;
            }
        }
    }

    // III) Full House (El Barco)
    private void full() {
        trio();
        pair();
        if (jugada.getValue(Jugadas.TRIO) && jugada.getValue(Jugadas.PAIR))
            jugada.updateMap(Jugadas.FULL_HOUSE, true);
    }

    // IV) Flush (color)
    private void Color() {
        if (suits_cont[0] == 5 || suits_cont[1] == 5 || suits_cont[2] == 5 || suits_cont[3] == 5)
            jugada.updateMap(Jugadas.FLUSH, true);
        if (suits_cont[0] == 4 || suits_cont[1] == 4 || suits_cont[2] == 4 || suits_cont[3] == 4)
            jugada.updateMap(Jugadas.FLUSH_DRAW, true);

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
                    jugada.updateMap(Jugadas.STRAIGHT, true);
                    jugada.updateMap(Jugadas.OPEN_ENDED, false);
                    return;
                }
                if (consecutive == 4 && gaps == 1) { //if there's 4 card and a gap is a gutshot
                    jugada.updateMap(Jugadas.GUTSHOT, true);
                    return;
                } else if (consecutive == 4) { // if there's 4 card with no gap it can be a open ended draw it it's not a straight(that's why this doesn't have the return
                    jugada.updateMap(Jugadas.OPEN_ENDED, true);
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
        for (int i=0; i < value_count.length; i++) {
            if (value_count[i] == 3) {
                usedCards(i);
                jugada.updateMap(Jugadas.TRIO, true);
                return;
            }
        }
    }

    // VII) Two-pair (pareja)
    private void pair() {
        for (int i = 0; i < value_count.length; i++) {
            if (value_count[i] == 2) {
                usedCards(i);
                jugada.updateMap(Jugadas.PAIR, true);
                return;
            }
        }
    }

    private void usedCards(int i) {
        if(i >= 1 && i <= 8){
            for(Carta c : mano){
                if(c.getvalue() == i)
                    play.add(c);
            }
        }
        switch (i) {
            case 0:
                for(Carta c : mano){
                    if(c.getvalue() == 'A')
                        play.add(c);
                }
                break;
            case 9:
                for(Carta c : mano){
                    if(c.getvalue() == 'T')
                        play.add(c);
                }
                break;
            case 10:
                for(Carta c : mano){
                    if(c.getvalue() == 'J')
                        play.add(c);
                }
                break;
            case 11:
                for(Carta c : mano){
                    if(c.getvalue() == 'Q')
                        play.add(c);
                }
                break;
            case 12:
                for(Carta c : mano){
                    if(c.getvalue() == 'K')
                        play.add(c);
                }
                break;
        }
    }




















    /*public void measurePerformance(int numberOfHands) {
        long startTime = System.nanoTime();

        for (int i = 0; i < numberOfHands; i++) {
            mano.clear();
            resetCounters();

            readInput();

            Jugadas strongestHand = GetStrongestJugada();

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
*/
}
