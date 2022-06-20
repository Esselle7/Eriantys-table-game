package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import java.io.Serializable;

/**
 * This class represent a player, each player has a nickname, a deck, a board, a current card, its client and
 * the corresponding motherNatureSteps he can do for this turn.
 * There is also the extraInfluence attribute which stores the extra influence points that
 * TwoExtraInfluenceCard (CharacterCard) might have added.
 */
public class Player extends ManagerStudent implements Serializable {
    private final String nickname;
    private Board playerBoard;
    private Deck assistantCards;
    private Card currentCard;
    private VirtualViewConnection Client;
    private int motherNatureSteps;
    private int extraInfluence;

    /**
     * Public constructor
     */
    public Player(String nickname)
    {
        this.nickname = nickname;
        currentCard = new Card();
        this.motherNatureSteps = 0;
        this.extraInfluence = 0;
    }

    public void setExtraInfluence(int extraInfluence) {
        this.extraInfluence = extraInfluence;
    }

    public int getExtraInfluence(){
        return extraInfluence;
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


    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
        setMotherNatureSteps(currentCard.getMotherNatureSteps() + getMotherNatureSteps());
    }

    /**
     * This method sets an assistant card corresponding to the selected value as the current card
     * @param cardNumber selected value
     * @return true in case the procedure was successful, false in case it was not (mainly due to the fact
     * that this method calls Deck.useCard, which returns null in case there's no card corresponding to the selected value
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
}
