package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class NoColorInfluenceCard extends CharacterCard{

    public NoColorInfluenceCard(){
        super(3);
        setDescription("you can choose a color that won't be included in influence calculations for this round");
    }

    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
        int bannedColour = turnHandler.getCurrentClient().receiveChooseInt();
        turnHandler.getGameMoves().getIslandController().setBannedColour(bannedColour);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Colour " + bannedColour + " successfully excluded from the influence count"));
    }

    public void resetCard(TurnHandler turnHandler){
        turnHandler.getGameMoves().getIslandController().setBannedColour(6);
    }
}
