package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.server.controller.*;
import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.Player;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class serves as a blueprint for all the character cards, it implements on its own
 * the card buying process and basic getters and setters.
 */
public abstract class CharacterCard implements Serializable {
    int price;
    boolean hasBeenUsed;
    String description = "";

    public CharacterCard(int price){
        this.hasBeenUsed = false;
        this.price = price;
    }

    /**
     * This method handles the buying process of a card by decreasing its buyer's coins and increasing
     * the card's price in case it has never been selected before.
     * @param turnHandler
     */
    public void buyCard(TurnHandler turnHandler) throws IOException, NotEnoughCoins {
        turnHandler.getCurrentPlayer().getPlayerBoard().decreaseCoins(price);
        if(!hasBeenUsed) {
            price++;
            //When the card is bought the first time, its price is increased and one coin is put on it
            turnHandler.getCurrentPlayer().getPlayerBoard().decreaseCoinReserve();
            hasBeenUsed = true;
        }
    }

    /**
     * This method initializes the attributes at the beginning of the game
     */
    public void initializeCard(TurnHandler turnHandler){
    }

    /**
     * This method handles the card's effects
     */
    public abstract void useCardImpl(TurnHandler turnHandler) throws chooseCharacterCardException, IOException, NotEnoughCoins, EmptyTowerYard,UnableToUseCardException, GameWonException;

    public void useCard(TurnHandler turnHandler) throws IOException, EmptyTowerYard, NotEnoughCoins, GameWonException, UnableToUseCardException {
        try {
            useCardImpl(turnHandler);
        }catch (chooseCharacterCardException ignored){}

    }

    public int getPrice() {
        return price;
    }

    /**
     * This method handles the card's effects reset
     */
    public void resetCard(TurnHandler turnHandler) {}

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
