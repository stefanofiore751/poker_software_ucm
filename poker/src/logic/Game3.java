package logic;

import model.Card;
import model.Play;
import view.MainWindow;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Game3 extends Game2 {
    //In a table with between 2 and 9 players with 2 cards each and 5 on the table, put the players in order by hand strength

    LinkedList<Player> players = new LinkedList<>();

    LinkedList<Card> table = new LinkedList<>();

    MainWindow mainWindow;

    public void readInput(String handTable) {
        char value;
        char suit;

        mainWindow = new MainWindow();

        String[] parts = handTable.split(";");

        int numPlayers = Integer.parseInt(parts[0]);

        for (int i = 1; i <= numPlayers; i++) {
            Player player = getPlayer(parts[i]);
            players.add(player); // Add the player to the list
            mainWindow.cartasJugador(player.getCards());
        }

        String commonCards = parts[numPlayers + 1];
        for(int i = 0; i < 5; i++) {
             value = commonCards.charAt(i * 2); // Get the value of the common card
             suit = commonCards.charAt(i * 2 + 1); // Get the suit of the common card
            table.add(new Card(value, suit)); // Assuming commonCardsList is defined to store the common cards
        }

        mainWindow.cartasMesa(table);
    }

    private Player getPlayer(String parts) {
        String playerId = parts.substring(0, 2); // Extract player ID (e.g., "J1", "J2")
        char cardValue1 = parts.charAt(2); // First card value
        char cardSuit1 = parts.charAt(3); // First card suit
        char cardValue2 = parts.charAt(4); // Second card value
        char cardSuit2 = parts.charAt(5); // Second card suit
        // Create the player object with their cards
        return new Player(playerId, new Card(cardValue1, cardSuit1), new Card(cardValue2, cardSuit2));
    }

    public void rankPlayer(){
        for(Player player : players) {
            Game2 game2 = new Game2();
            game2.addCards(player.getCards());
            game2.addCards(table);
            player.setBestGame(game2.calculateBestHand());
        }
        // Sort players based on hand strength
        players.sort((p1, p2) -> {
            Game p1BestGame = p1.getBestGame();
            Game p2BestGame = p2.getBestGame();

            int p1Type = p1BestGame.getType();
            int p1Quality = p1BestGame.getQuality();
            int p2Type = p2BestGame.getType();
            int p2Quality = p2BestGame.getQuality();

            // Compare first by type, then by quality
            if (p1Type != p2Type) {
                return Integer.compare(p1Type, p2Type); // Ascending order
            } else {
                return Integer.compare(p1Quality, p2Quality); // Ascending order
            }
        });

        // The players list is now ordered from weakest to strongest, so if you want to reverse it:
        Collections.reverse(players); // Now it's strongest to weakest

        /*for(Player player : players) {
            Game playerBestGame = player.getBestGame();
             type = playerBestGame.getStrongestJugada(); //Get the play of the hand
             quality = playerBestGame.getJugadaQuality(type); //Get the quality of the play

            if (type > bestPlayType || (type == bestPlayType && quality > bestPlayQuality)){ //If the play is the best so far, update the best play and the index
                bestPlayType = type;
                bestPlayQuality = quality;
                bestPlayer = player;
            }
        }*/
    }

    public void writeoutput(String outputFile) {
        rankPlayer();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Write the table cards
            StringBuilder tableString = new StringBuilder();
            for (Card card : table) {
                tableString.append(card.toString());
            }

            writer.write(tableString + "\n");

            // Write the players with their best hands
            for (Player player : players) {
                Game playerBestGame = player.getBestGame();
                String playString = playerBestGame.getMano();
                String handType = playerBestGame.getBestPlay(); // Assuming you have a method to get the hand type
                writer.write(player.getPlayerID() + ": " + playString + " (" + handType + ")\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
