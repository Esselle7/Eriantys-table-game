package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class ExtraStepsCard extends CharacterCard{

    public ExtraStepsCard(){
        super(1);
        setDescription("...");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws NotEnoughCoins, IOException {
        buyCard(turnHandler);
        turnHandler.getCurrentPlayer().setMotherNatureSteps(turnHandler.getCurrentPlayer().getMotherNatureSteps() + 2);
    }
}
