package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.VirtualView;

/**
 * This class represent a player of
 * the game. Every player has its own
 * board and deck.
 */
public class Player extends ManagerStudent implements Comparable<Player> {
    private final String nickname;
    private Board playerBoard;
    private Deck assistantCards;
    private Card currentCard;
    private VirtualView Client;

    public Player(String nickname)
    {
        this.nickname = nickname;
        currentCard = new Card();
    }

    public void setClient(VirtualView Client){
        this.Client = Client;
    }

    public VirtualView getClient(){
        return this.Client;
    }

    public String getNickname() {
        return nickname;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Deck getAssistantCards() {
        return assistantCards;
    }

    public void setDeck(Deck assistantCards)
    {
        this.assistantCards = assistantCards;
    }

    public void setPlayerBoard(Board playerBoard)
    {
        this.playerBoard = playerBoard;
    }

    public void setAssistantCards(Deck assistantCards) {
        this.assistantCards = assistantCards;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    /**
     * This method allows to use
     * an assistant card from the deck
     * and set it as the current card
     * @param cardNumber number of the card to play
     */
    public void useCard(int cardNumber)
    {
        setCurrentCard(getAssistantCards().useCard(cardNumber));
    }

    /**
     * This method allows comparing
     * two player by their current card
     * value
     * @param otherPlayer other player to compare
     * @return an int =0,>0 or <0 by the values
     *         of the two cards
     */
    public int compareTo(Player otherPlayer) {
        return Integer.compare(getCurrentCard().getValue(),otherPlayer.getCurrentCard().getValue());
    }
}
