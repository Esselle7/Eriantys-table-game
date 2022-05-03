package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.server.controller.TurnHandler;

public class TwoExtraInfluenceCard extends CharacterCard{
    public TwoExtraInfluenceCard(TurnHandler turnHandler){
        super(turnHandler, 2);
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
        currentPlayer.setExtraInfluence(2);
    }

    @Override
    public void resetCard() throws Exception {
        currentPlayer.setExtraInfluence(0);
    }
}
