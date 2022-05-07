package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * An assistant Card of the game.
 * Every card belongs to a single Deck.
 * There are at maximum 10 card instances
 * for each deck.
 *
 */
public class Card implements Serializable {
    private final int value;
    private final int motherNatureSteps;

   public Card()
    {
        value = 0;
        motherNatureSteps = 0;
    }

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
     * This method verificate if the value
     * of the card in input is equal to this
     *
     * @param toCompare is the card to compare with
     * @return a true boolean if the value
     *         of toCompare is equals to this card value
     */
    public boolean equals(Card toCompare)
    {
        return toCompare.getValue() == this.getValue();
    }

    /**
     * This method verificate if the value
     * of the card in input is greater to this
     *
     * @param toCompare is the card to compare with
     * @return a true boolean if the value
     *         of toCompare is greater to this card value
     */
    public boolean isGreater(Card toCompare) {
        return toCompare.getValue() > this.getValue();
    }

}
