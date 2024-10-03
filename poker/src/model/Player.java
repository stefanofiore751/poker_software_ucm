package model;

import logic.Game;

import java.util.LinkedList;

public class Player {
    private String playerID;
    private Hand _hand;
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

    public Player(Hand hand) {
        _hand = hand;
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
