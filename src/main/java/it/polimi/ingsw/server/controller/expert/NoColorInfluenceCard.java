package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.TurnHandler;

public class NoColorInfluenceCard extends CharacterCard{

    public NoColorInfluenceCard(TurnHandler turnHandler){
        super(turnHandler, 3);
    }

    public void useCard() throws Exception{
        buyCard();
        turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
        int bannedColour = turnHandler.getCurrentClient().receiveChooseInt();
        turnHandler.getGameMoves().getIslandController().setBannedColour(bannedColour);
    }

    public void resetCard(){
        turnHandler.getGameMoves().getIslandController().setBannedColour(6);
    }
}
