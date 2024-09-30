package ludopata.sl;

import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.io.FileInputStream;
import java.util.LinkedList;

import static org.paukov.combinatorics.CombinatoricsFactory.createSimpleCombinationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

public class Game2 extends Game {

    private final LinkedList<Carta> cards;

    private final LinkedList<Carta> play;

    public Game2() {
        jugada = new Jugada();
        cards = new LinkedList<>();
        play = new LinkedList<>();
    }

    void readInput() {
        try{
            // Open input file
            //FileInputStream in = new FileInputStream("entrada2.txt");
            FileInputStream in = new FileInputStream("..\\poker_software_ucm\\poker\\resources\\entrada2.txt");
            char value;
            char suit;


            for(int i = 0; i < 2; i++){ //read hand
                value = (char) in.read();
                suit = (char) in.read();

                cards.add(new Carta(value, suit));
            }
            in.read(); //";
            int j = in.read()  - '0' ; //how many cards are on the table
            in.read(); //";"
            for(int i = 0; i < j; i++){ //read table
                value = (char) in.read();
                suit = (char) in.read();

                cards.add(new Carta(value, suit));
            }

            // Close file
            in.close();
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void Test(){

    }
    void writeoutput() {
        int  bestPlayType = 0, bestPlayQuality = 0; //index of best hand and what the play is
        ICombinatoricsVector<Carta> vector = crearCombinaciones(cards);
        Game bestgame = null;
        Generator<Carta> gen = createSimpleCombinationGenerator(vector, 5);
        for (ICombinatoricsVector<Carta> combination : gen) {
            play.clear();
            System.out.print("Combinazione: ");


            for (Carta carta : combination) {
                play.add(carta);
                System.out.print(carta + " ");
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
    ICombinatoricsVector<Carta> crearCombinaciones(LinkedList<Carta>  cards) {

        ICombinatoricsVector<Carta> combinations = createVector();
        for(Carta i : cards) {
            combinations.addValue(i);
        }
        return combinations;
    }
}
