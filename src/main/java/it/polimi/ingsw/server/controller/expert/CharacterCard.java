package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.server.controller.*;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.model.Player;

import java.io.IOException;

public abstract class CharacterCard {
    int price;
    boolean hasBeenUsed;
    protected TurnHandler turnHandler;
    protected GameMoves gameMoves;
    Player currentPlayer;

    public CharacterCard(TurnHandler turnHandler, int price){
        this.turnHandler = turnHandler;
        this.hasBeenUsed = false;
        this.gameMoves = turnHandler.getGameMoves();
        this.price = price;
        this.currentPlayer = turnHandler.getCurrentPlayer();
    }

    public void buyCard() throws Exception {
        turnHandler.getCurrentPlayer().getPlayerBoard().decreaseCoins(price);
        if(!hasBeenUsed)
            price++;
    }

    public void useCard() throws Exception{

    }
}
