package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.server.controller.*;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;

import java.io.IOException;

public abstract class CharacterCard {
    int price;
    boolean hasBeenUsed;
    protected TurnHandler turnHandler;
    protected GameMoves gameMoves;

    public CharacterCard(TurnHandler turnHandler){
        this.turnHandler = turnHandler;
        this.hasBeenUsed = false;
        this.gameMoves = turnHandler.getGameMoves();
    }

    public void buyCard() throws Exception {
        turnHandler.getCurrentPlayer().getPlayerBoard().decreaseCoins(price);
        if(!hasBeenUsed)
            price++;
    }
}
