package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.server.controller.*;
import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.Player;

import java.io.IOException;
import java.io.Serializable;

public abstract class CharacterCard implements Serializable {
    int price;
    boolean hasBeenUsed;
    String description = "";


    public CharacterCard(int price){
        this.hasBeenUsed = false;
        this.price = price;
    }

    public void buyCard(TurnHandler turnHandler) throws IOException, NotEnoughCoins {
        turnHandler.getCurrentPlayer().getPlayerBoard().decreaseCoins(price);
        if(!hasBeenUsed) {
            price++;
            //When the card is bought the first time, its price is increased and one coin is put on it
            turnHandler.getCurrentPlayer().getPlayerBoard().decreaseCoinReserve();
            hasBeenUsed = true;
        }
    }

    public abstract void useCardImpl(TurnHandler turnHandler) throws chooseCharacterCardException, IOException, NotEnoughCoins, EmptyTowerYard,UnableToUseCardException, GameWonException;

    public void useCard(TurnHandler turnHandler) throws IOException, EmptyTowerYard, NotEnoughCoins, GameWonException, UnableToUseCardException {
        try {
            useCardImpl(turnHandler);
        }catch (chooseCharacterCardException ignored){}

    }

    public int getPrice() {
        return price;
    }

    public void resetCard(TurnHandler turnHandler) {}

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
