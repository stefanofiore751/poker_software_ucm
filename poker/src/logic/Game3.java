package logic;

import model.Card;

import java.util.LinkedList;

public class Game3 {
    //In a table with between 2 and 9 players with 2 cards each and 5 on the table, put the players in order by hand strength

    LinkedList<Player> players = new LinkedList<Player>();

    LinkedList<Card> table = new LinkedList<Card>();

    public void readInput(String handTable) {
        char value;
        char suit;

        String[] parti = handTable.split(";");

        for (int i = 1; i < parti[0].charAt(0) + 1; i++) {
            Player player = new Player();
            value = parti[i].charAt(2);
            suit = parti[i].charAt(3);
            player.addCard(new Card(value, suit));
            value = parti[i].charAt(4);
            suit = parti[i].charAt(5);
            player.addCard(new Card(value, suit));
            players.add(player);
        }

        for(int i = 0; i < 5; i++) {
            value = parti[parti.length - 1].charAt(2 * i);
            suit = parti[parti.length - 1].charAt(2 * i + 1);
            table.add(new Card(value, suit));
        }
    }
}
