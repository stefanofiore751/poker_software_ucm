package logic;

import model.Card;

import model.Hand;
import model.Play;
import model.Play.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.LinkedList;

public class Game {
    // Counter number of cards of each suit:
    // 0 : hearts, 1 : diamonds, 2 : clubs, 3 : spades
    private final int[] suits_cont;

    String strongestJugada;
    // Counter number of cards of each kind:
    int[] value_count;

    private int type = 0, quality = 0;

    Play jugada;

    private final LinkedList<Card> mano;

    private final LinkedList<Card> play;

    public Game() {
        suits_cont = new int[4];
        value_count = new int[13];
        jugada = new Play();
        mano = new LinkedList<>();
        play = new LinkedList<>();
    }

    public Game(LinkedList<Card> mano) {
        suits_cont = new int[4];
        value_count = new int[13];
        jugada = new Play();
        this.mano = new LinkedList<>(mano);
        assignCounts(mano);
        play = new LinkedList<>();
    }

    public String getBestPlay(){
        return strongestJugada;
    }

    public int getType(){
        return type;
    }

    public int getQuality(){
        return quality;
    }

    public Play getJugada(){
        return jugada;
    }
    public void readInput(String inputFile) {
        try {
            // Open input file
            FileInputStream in = new FileInputStream(inputFile);
            //FileInputStream in = new FileInputStream("..\\poker_software_ucm\\poker\\resources\\entrada.txt");
            char value;
            char suit;

            // Read file
            while (in.available() > 0) {
                value = (char) in.read();
                suit = (char) in.read();

                countSuits_Value(value, suit);

                mano.add(new Card(value, suit));
            }

            // Close file
            in.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void assignCounts(LinkedList<Card> mano){
        for(Card c : mano){
            countSuits_Value(c.getvalue(), c.getsuit());
        }
    }

    public void writeoutput(String outputFile, String line) {
        if(strongestJugada == null)
            getStrongestJugada();
        try {
            FileOutputStream out = new FileOutputStream(outputFile,true);
            // Scrivere l'output su file
            String result = line + "\n-Best Hand: " + strongestJugada + "\n";
            if (jugada.getValue(Plays.GUTSHOT))
                result += "-DRAW: Straight Gutshot\n";
            if (jugada.getValue(Plays.FLUSH_DRAW))
                result += "-DRAW: flush\n";
            result += "\n";
            out.write(result.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkStrongestJugada() {
        if(strongestJugada == null)
            getStrongestJugada();
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

    void updateHand(Hand hand){
        mano.addAll(hand.getHand());
    }

    String getMano() {
        StringBuilder manostring = new StringBuilder();
        for (Card c : mano)
            manostring.append(c.toString());
        return manostring.toString();
    }

    String getPlay() {
        StringBuilder playstring = new StringBuilder();
        for (Card c : play)
            playstring.append(c.toString());
        return playstring.toString();
    }

    public int getStrongestJugada() {
        straightFlush();
        if (jugada.getValue(Plays.STRAIGHT_FLUSH)){
            strongestJugada = Plays.STRAIGHT_FLUSH.toString();
            type = 8;
            return 8;
        }
        poker();
        if (jugada.getValue(Plays.POKER)) {
            strongestJugada = Plays.POKER + " -- " + getPlay();
            type = 7;
            return 7;
        }
        full();
        if (jugada.getValue(Plays.FULL_HOUSE)){
            strongestJugada = Plays.FULL_HOUSE + " -- " + getPlay();
            type = 6;
            return 6;
        }
        if (jugada.getValue(Plays.FLUSH)) {
            strongestJugada = Plays.FLUSH.toString();
            type = 5 ;
            return 5;
        }
        if (jugada.getValue(Plays.STRAIGHT)) {
            strongestJugada = Plays.STRAIGHT.toString();
            type = 4;
            return 4;
        }
        if (jugada.getValue(Plays.TRIO)){
            strongestJugada = Plays.TRIO + " -- " + getPlay();
            type = 3;
            return 3;
        }
        if (jugada.getValue(Plays.PAIR)) {
            strongestJugada = Plays.PAIR + " -- " + getPlay();
            type = 2;
            return 2;
        }
        strongestJugada =Plays.HIGH_CARD.toString();
        type = 1;
        return 1;
    }

    protected int getJugadaQuality(int playType) {
        return switch (playType) {
            case 8,6, 4 -> straightValue(mano); //straight and straight flush
            case 7, 2, 3 -> straightValue(play); // poker  trio and pair
            default -> straightValue(mano); // flush and high card
        };
    }

    // I) Straight Flush
    private void straightFlush() {
        Color();
        Straight();
        if (jugada.getValue(Plays.FLUSH) && jugada.getValue(Plays.STRAIGHT))
            jugada.updateMap(Plays.STRAIGHT_FLUSH, true);
    }

    // II) Poker (four-of-a-kind)
    public void poker() {
        for (int i = 0 ; i< value_count.length; i++) {
            if (value_count[i] == 4) {
                jugada.updateMap(Plays.POKER, true);
                usedCards(i);
                return;
            }
        }
    }

    // III) Full House (El Barco)
    private void full() {
        trio();
        pair();
        if (jugada.getValue(Plays.TRIO) && jugada.getValue(Plays.PAIR))
            jugada.updateMap(Plays.FULL_HOUSE, true);
    }

    // IV) Flush (color)
    private void Color() {
        if (suits_cont[0] == 5 || suits_cont[1] == 5 || suits_cont[2] == 5 || suits_cont[3] == 5)
            jugada.updateMap(Plays.FLUSH, true);
        if (suits_cont[0] == 4 || suits_cont[1] == 4 || suits_cont[2] == 4 || suits_cont[3] == 4)
            jugada.updateMap(Plays.FLUSH_DRAW, true);

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
                    jugada.updateMap(Plays.STRAIGHT, true);
                    jugada.updateMap(Plays.OPEN_ENDED, false);
                    return;
                }
                if (consecutive == 4 && gaps == 1) { //if there's 4 card and a gap is a gutshot
                    jugada.updateMap(Plays.GUTSHOT, true);
                    return;
                } else if (consecutive == 4) { // if there's 4 card with no gap it can be a open ended draw it it's not a straight(that's why this doesn't have the return
                    jugada.updateMap(Plays.OPEN_ENDED, true);
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
                jugada.updateMap(Plays.TRIO, true);
                return;
            }
        }
    }

    // VII) Two-pair (pareja)
    private void pair() {
        for (int i = 0; i < value_count.length; i++) {
            if (value_count[i] == 2) {
                usedCards(i);
                jugada.updateMap(Plays.PAIR, true);
                return;
            }
        }
    }

    private void usedCards(int i) {
        if(i >= 1 && i <= 8){
            for(Card c : mano){
                if(c.getvalue() - '0' == (i + 1))
                    play.add(c);
            }
        }
        switch (i) {
            case 0:
                for(Card c : mano){
                    if(c.getvalue() == 'A')
                        play.add(c);
                }
                break;
            case 9:
                for(Card c : mano){
                    if(c.getvalue() == 'T')
                        play.add(c);
                }
                break;
            case 10:
                for(Card c : mano){
                    if(c.getvalue() == 'J')
                        play.add(c);
                }
                break;
            case 11:
                for(Card c : mano){
                    if(c.getvalue() == 'Q')
                        play.add(c);
                }
                break;
            case 12:
                for(Card c : mano){
                    if(c.getvalue() == 'K')
                        play.add(c);
                }
                break;
        }
    }
    
    int straightValue(LinkedList<Card> mano) {
    	int aux = 0;
    	
        for(Card c : mano) {
            switch (c.getvalue()) {
                case 'A':
                    if(aux < 14){
                        quality = 14;
                        aux = 14;}
                    break;
                case 'K':
                    if(aux < 13){
                        quality = 13;
                        aux = 13;}
                    break;
                case 'Q':
                    if(aux < 12){
                        quality = 12;
                        aux = 12;}
                    break;
                case 'J':
                    if(aux < 11){
                        quality = 11;
                        aux = 11;}
                    break;
                case 'T':
                    if(aux < 10){
                        quality = 10;
                         aux = 10;}
                    break;
                default:
                    if(aux < c.getvalue() - '0'){
                        quality = c.getvalue() - '0';
                        aux = c.getvalue() - '0';}
                    break;
            }
        }
        return aux;
    }









    /*public void measurePerformance(int numberOfHands) {
        long startTime = System.nanoTime();

        for (int i = 0; i < numberOfHands; i++) {
            mano.clear();
            resetCounters();

            readInput();

            Plays strongestHand = GetStrongestJugada();

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
