package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class ExtraStepsCard extends CharacterCard{

    public ExtraStepsCard(){
        super(1);
        setDescription("you can move mothernature two extra steps");
    }

    /**
     * This card adds 2 to the extra steps the current player can make mothernature do, keep in mind that when
     * choosing the assistant card turnHandler will add the card's steps to the extra (if there are) steps set by these
     * card. After mothernature has been moved, TurnHandler.moveMotherNature will set the current player's steps to zero
     * @param turnHandler
     */
    @Override
    public void useCardImpl(TurnHandler turnHandler) throws NotEnoughCoins, IOException {
        buyCard(turnHandler);
        turnHandler.getCurrentPlayer().setMotherNatureSteps(turnHandler.getCurrentPlayer().getMotherNatureSteps() + 2);
    }
}
