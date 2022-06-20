package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.ChooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import java.io.IOException;

/**
 * Specific Character Card Class, its effect is listed in its description and its methods implement it
 */
public class NoColorInfluenceCard extends CharacterCard{

    public NoColorInfluenceCard(){
        super(3,8);
        setDescription("you can choose a color that won't be included in influence calculations for this round");
    }

    /**
     * The card sets the designated (by the player) colour as the banned colour for the islandController
     */
    public void useCardImpl(TurnHandler turnHandler) throws IOException, ChooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Choose a student colour"));
        turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
        int bannedColour = turnHandler.getCurrentClient().receiveChooseInt();
        turnHandler.getGameMoves().getIslandController().setBannedColour(bannedColour);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Colour " + bannedColour + " successfully excluded from the influence count"));
    }

    /**
     * There's no banned colour anymore
     */
    public void resetCard(TurnHandler turnHandler){
        turnHandler.getGameMoves().getIslandController().setBannedColour(6);
    }
}
