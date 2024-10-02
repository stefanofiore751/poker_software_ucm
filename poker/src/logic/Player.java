package logic;

import model.Card;

import java.util.LinkedList;

public class Player {

    private String playerID;
    private final LinkedList<Card> cards;
    private Game bestGame;
    public Player(String playerID, Card card1, Card card2) {
        this.playerID = playerID;
        cards = new LinkedList<>();
        addCard(card1);
        addCard(card2);
    }

    public Player(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public Player() {
        cards = new LinkedList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getPlayerID() {
        return playerID;
    }

    public Game getBestGame() {
        return bestGame;
    }

    public void setBestGame(Game bestGame) {
        this.bestGame = bestGame;
    }

    public LinkedList<Card> getCards() {
        return cards;
    }
}
