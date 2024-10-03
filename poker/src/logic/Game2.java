package logic;

import model.Play;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import model.Card;
import java.util.LinkedList;

import static org.paukov.combinatorics.CombinatoricsFactory.createSimpleCombinationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

public class Game2 extends Game {

    private final LinkedList<Card> cards;


    private int numPlayers;

    public Game2() {
        jugada = new Play();
        cards = new LinkedList<>();
    }

    public void addCards(LinkedList<Card> cards) {
        this.cards.addAll(cards);
    }

    public void readInput(String handTable) {

        char value;
        char suit;

                String[] parti = handTable.split(";");
                int numCardsTable = parti[1].charAt(0) - '0'; //how many card are on the table

                int j = 0;

                for (int i = 0; i < 2; i++) {
                    value = parti[0].charAt(j++);
                    suit = parti[0].charAt(j++);
                    cards.add(new Card(value, suit));
                }

                j = 0;

                for (int i = 0; i < numCardsTable; i++) {
                    value = parti[2].charAt(j++);
                    suit = parti[2].charAt(j++);
                    cards.add(new Card(value, suit));
                }
            }

    public void writeoutput(String outputFIle, String line){
        Game bestgame = calculateBestHand();
        bestgame.writeoutput(outputFIle, line);
    }
    public Game calculateBestHand() {
        int  bestPlayType = 0, bestPlayQuality = 0; //index of best hand and what the play is
        ICombinatoricsVector<Card> vector = crearCombinaciones(cards);
        Game bestgame = null;
        LinkedList <Card> play = new LinkedList<>();
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
            LinkedList<Card> play2 = new LinkedList<>();
            play2 = play;
            Game game = new Game(play2);
            int type = game.getStrongestJugada(); //Get the play of the hand
            int quality = game.getJugadaQuality(type); //Get the quality of the play

            if (type > bestPlayType || (type == bestPlayType && quality > bestPlayQuality)){ //If the play is the best so far, update the best play and the index
                bestPlayType = type;
                bestPlayQuality = quality;
                bestgame = game;
            }
        }
        return bestgame;
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
