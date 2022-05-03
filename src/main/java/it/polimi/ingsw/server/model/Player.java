package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
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
    private VirtualViewConnection Client;
    private int motherNatureSteps;

    public Player(VirtualViewConnection Client)
    {
        this.nickname = Client.getNickname();
        currentCard = new Card();
        this.Client = Client;
        this.motherNatureSteps = 0;
    }

    public int getMotherNatureSteps() {
        return motherNatureSteps;
    }

    public void setMotherNatureSteps(int motherNatureSteps) {
        this.motherNatureSteps = motherNatureSteps;
    }

    public void setClient(VirtualViewConnection Client){
        this.Client = Client;
    }

    public VirtualViewConnection getClient(){
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
        setMotherNatureSteps(currentCard.getMotherNatureSteps() + getMotherNatureSteps());
    }

    /**
     * This method allows to use
     * an assistant card from the deck
     * and set it as the current card
     * @param cardNumber number of the card to play
     */
    public boolean useCard(int cardNumber)
    {
        Card toUse = getAssistantCards().useCard(cardNumber);
        if(toUse != null)
        {
            setCurrentCard(toUse);
            return true;
        }
        else
            return false;

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
