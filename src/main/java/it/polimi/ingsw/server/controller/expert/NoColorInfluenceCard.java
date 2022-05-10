package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class NoColorInfluenceCard extends CharacterCard{

    public NoColorInfluenceCard(){
        super(3);
        setDescription("...");
    }

    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
        int bannedColour = turnHandler.getCurrentClient().receiveChooseInt();
        turnHandler.getGameMoves().getIslandController().setBannedColour(bannedColour);
    }

    public void resetCard(TurnHandler turnHandler){
        turnHandler.getGameMoves().getIslandController().setBannedColour(6);
    }
}
