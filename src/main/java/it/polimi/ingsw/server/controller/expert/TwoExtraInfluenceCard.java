package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class TwoExtraInfluenceCard extends CharacterCard{
    public TwoExtraInfluenceCard(){
        super(2,7);
        setDescription("you have two extra points in influence calculation during this round");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws NotEnoughCoins, IOException {
        buyCard(turnHandler);
        turnHandler.getCurrentPlayer().setExtraInfluence(2);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Two extra influence points acquired"));
    }

    @Override
    public void resetCard(TurnHandler turnHandler){
        turnHandler.getCurrentPlayer().setExtraInfluence(0);
    }
}
