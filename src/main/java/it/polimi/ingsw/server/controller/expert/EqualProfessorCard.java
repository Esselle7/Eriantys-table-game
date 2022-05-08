package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.*;

public class EqualProfessorCard extends CharacterCard{

    public EqualProfessorCard(TurnHandler turnHandler){
        super(turnHandler, 2);
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
        turnHandler.getGameMoves().setPriorityPlayer(currentPlayer);
    }

    @Override
    public void resetCard(){
        turnHandler.getGameMoves().setPriorityPlayer(null);
    }
}
