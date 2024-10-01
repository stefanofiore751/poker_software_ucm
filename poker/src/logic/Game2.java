package logic;

import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import static org.paukov.combinatorics.CombinatoricsFactory.createSimpleCombinationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

public class Game2 extends Game {

    private final LinkedList<Card> cards;

    private final LinkedList<Card> play;

    private int numPlayers;

    public Game2() {
        jugada = new Jugada();
        cards = new LinkedList<>();
        play = new LinkedList<>();
    }

    public void readInput(String handTable) {

        char value;
        char suit;
       // String percorsoFile = "..\\\\poker_software_ucm\\\\poker\\\\resources\\\\entrada2.txt";  // Sostituisci con il percorso reale del file

                // Dividi la riga utilizzando il delimitatore ";"
                String[] parti = handTable.split(";");
                numPlayers = parti[0].charAt(0); //how many players are playing

                int j = 0;

                for (int i = 0; i < 2; i++) {
                    value = parti[0].charAt(j++);
                    suit = parti[0].charAt(j++);
                    cards.add(new Card(value, suit));
                }

                j = 0;

                for (int i = 0; i < numPlayers; i++) {
                    value = parti[2].charAt(j++);
                    suit = parti[2].charAt(j++);
                    cards.add(new Card(value, suit));
                }
            }


        /*try{
            // Open input file
            FileInputStream in = new FileInputStream("entrada2.txt");
            //FileInputStream in = new FileInputStream("..\\poker_software_ucm\\poker\\resources\\entrada2.txt");
            char value;
            char suit;


            for(int i = 0; i < 2; i++){ //read hand
                value = (char) in.read();
                suit = (char) in.read();

                cards.add(new Card(value, suit));
            }
            in.read(); //";
            int j = in.read()  - '0' ; //how many cards are on the table the -0 is to get the int from the ascii
            in.read(); //";"
            for(int i = 0; i < j; i++){ //read table
                value = (char) in.read();
                suit = (char) in.read();

                cards.add(new Card(value, suit));
            }

            // Close file
            in.close();
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    void Test(){

    }
    public void writeoutput() {
        int  bestPlayType = 0, bestPlayQuality = 0; //index of best hand and what the play is
        ICombinatoricsVector<Card> vector = crearCombinaciones(cards);
        Game bestgame = null;
        Generator<Card> gen = createSimpleCombinationGenerator(vector, 5);
        for (ICombinatoricsVector<Card> combination : gen) {
            play.clear();
            // just to check if the combination are correct
            //System.out.print("Combination: ");


            for (Card carta : combination) {
                play.add(carta);
                //System.out.print(carta + " ");
            }

            //System.out.println();
            Game game = new Game(play);
            int type = game.getStrongestJugada(); //Get the play of the hand
            int quality = game.getJugadaQuality(type); //Get the quality of the play

            if (type > bestPlayType || (type == bestPlayType && quality > bestPlayQuality)){ //If the play is the best so far, update the best play and the index
                bestPlayType = type;
                bestPlayQuality = quality;
                bestgame = game;
            }
        }
        if (bestgame != null) {
            bestgame.writeoutput();
            }
    }

    /*Creates all possible card combinations*/
    ICombinatoricsVector<Card> crearCombinaciones(LinkedList<Card>  cards) {

        ICombinatoricsVector<Card> combinations = createVector();
        for(Card i : cards) {
            combinations.addValue(i);
        }
        return combinations;
    }
}
