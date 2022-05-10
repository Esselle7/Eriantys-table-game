package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.*;

import java.io.IOException;

public class EqualProfessorCard extends CharacterCard{

    public EqualProfessorCard(){
        super(2);
        setDescription("...");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getGameMoves().setPriorityPlayer(turnHandler.getCurrentPlayer());
    }

    @Override
    public void resetCard(TurnHandler turnHandler){
        turnHandler.getGameMoves().setPriorityPlayer(null);
    }
}
