package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.server.controller.TurnHandler;

public class ExtraStepsCard extends CharacterCard{

    public ExtraStepsCard(TurnHandler turnHandler){
        super(turnHandler, 1);
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
        super.useCard();
        currentPlayer.setMotherNatureSteps(currentPlayer.getMotherNatureSteps() + 2);
    }
}
