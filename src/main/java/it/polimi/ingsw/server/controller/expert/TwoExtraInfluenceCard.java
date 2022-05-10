package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class TwoExtraInfluenceCard extends CharacterCard{
    public TwoExtraInfluenceCard(){
        super(2);
        setDescription("...");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws NotEnoughCoins, IOException {
        buyCard(turnHandler);
        turnHandler.getCurrentPlayer().setExtraInfluence(2);
    }

    @Override
    public void resetCard(TurnHandler turnHandler){
        turnHandler.getCurrentPlayer().setExtraInfluence(0);
    }
}
