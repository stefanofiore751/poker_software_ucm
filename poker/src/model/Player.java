package model;

import java.util.LinkedList;

public class Player {
    private String playerID;
    private final Hand _hand;
    private Game bestGame;

    public Player(String playerID, Card card1, Card card2) {
        this.playerID = playerID;
        _hand = new Hand();
        addCard(card1);
        addCard(card2);
    }

    public Player(LinkedList<Card> cards) {
        _hand = new Hand(cards);
    }

    public Player() {
        cards = new LinkedList<>();
    }

    public void addCard(Card card) {
        _hand = new Hand();
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
        return _hand.getHand();
    }
}
