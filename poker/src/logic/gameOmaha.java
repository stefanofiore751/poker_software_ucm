package logic;
import model.Play;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.LinkedList;
import model.Card;

import static org.paukov.combinatorics.CombinatoricsFactory.createSimpleCombinationGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;
public class gameOmaha extends Game2{

    private  LinkedList<Card> playerCards;
    private  LinkedList<Card> tableCards;

    public gameOmaha() {
        this.playerCards = new LinkedList<>();
        this.tableCards = new LinkedList<>();
    }

    public void readInput(String handTable) {

        char value;
        char suit;

        String[] parti = handTable.split(";");
        int numCardsTable = parti[1].charAt(0) - '0'; // how many cards are on the table

        int j = 0;

        // Parse 4 cards for the player's hand
        for (int i = 0; i < 4; i++) {
            value = parti[0].charAt(j++);
            suit = parti[0].charAt(j++);
            playerCards.add(new Card(value, suit));
        }

        j = 0;

        // Parse n cards for the table (3 to 5)
        for (int i = 0; i < numCardsTable; i++) {
            value = parti[2].charAt(j++);
            suit = parti[2].charAt(j++);
            tableCards.add(new Card(value, suit));
        }
    }
    public Game calculateBestHand() {
        int bestPlayType = 0, bestPlayQuality = 0; // index of best hand and what the play is
        Game bestgame = null;

        // Generate combinations of exactly 2 cards from the player's hand
        Generator<Card> playerCombinations = createCombinations(playerCards, 2);

        // Generate combinations of exactly 3 cards from the table
        Generator<Card> tableCombinations = createCombinations(tableCards, 3);

        // Loop through all combinations of 2 player cards and 3 table cards
        for (ICombinatoricsVector<Card> playerCombination : playerCombinations) {
            for (ICombinatoricsVector<Card> tableCombination : tableCombinations) {
                LinkedList<Card> play = new LinkedList<>();
                play.addAll(playerCombination.getVector()); // Add 2 player cards
                play.addAll(tableCombination.getVector());  // Add 3 table cards

                // Evaluate the hand
                Game game = new Game(play);
                int type = game.getStrongestJugada();  // Get the play type of the hand
                int quality = game.getJugadaQuality(type);  // Get the quality of the play

                // If the current hand is better, update the best hand
                if (type > bestPlayType || (type == bestPlayType && quality > bestPlayQuality)) {
                    bestPlayType = type;
                    bestPlayQuality = quality;
                    bestgame = game;
                }
            }
        }
        return bestgame;
    }

    /* Creates combinations of exactly 'r' cards from a given set of cards */
    Generator<Card> createCombinations(LinkedList<Card> cards, int r) {
        ICombinatoricsVector<Card> vector = createVector();
        for (Card card : cards) {
            vector.addValue(card);
        }
        // Return the generator, not the vector
        return createSimpleCombinationGenerator(vector, r);
    }


}
