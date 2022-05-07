package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.*;

public class EqualProfessorCard extends CharacterCard{
    PlayGround playGround;

    public EqualProfessorCard(TurnHandler turnHandler){
        super(turnHandler, 2);
        playGround = turnHandler.getGameMoves().getCurrentGame();
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
        int colorCounter = 0;
        turnHandler.getGameMoves().setPriorityPlayer(currentPlayer);
    }

    @Override
    public void resetCard(){
        turnHandler.getGameMoves().setPriorityPlayer(null);
    }
}
