package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * Assistant Card, it has a value and a corresponding number of mother nature steps given to the player if selected
 */
public class Card implements Serializable {
    private final int value;
    private final int motherNatureSteps;

    /**
     * Plain constructor
     */
   public Card()
    {
        value = 0;
        motherNatureSteps = 0;
    }

    /**
     * Card constructor
     * @param value the value of the card
     * @param motherNatureSteps mothernature steps given by the card to the player
     */
    public Card(int value, int motherNatureSteps){
        this.value = value;
        this.motherNatureSteps = motherNatureSteps;
    }

    public int getValue() {
        return value;
    }

    public int getMotherNatureSteps() {
        return motherNatureSteps;
    }

    /**
     * This method checks if this card has the same value of a selected card
     * @param toCompare selected card
     * @return true if the value is the same, false if not
     */
    public boolean equals(Card toCompare)
    {
        return toCompare.getValue() == this.getValue();
    }

    /**
     * This method checks if the value of this card is greater than a selected card's
     * @param toCompare the selected card
     * @return true if the value is greater, false if not
     */
    public boolean isGreater(Card toCompare) {
        return toCompare.getValue() > this.getValue();
    }

}
