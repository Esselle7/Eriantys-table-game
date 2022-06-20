package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

/**
 * Specific Character Card Class, its effect is listed in its description and its methods implement it
 */
public class TwoExtraInfluenceCard extends CharacterCard{
    public TwoExtraInfluenceCard(){
        super(2,7);
        setDescription("you have two extra points in influence calculation during this round");
    }

    /**
     * The card simply assigns two extra points to the influence of the player
     */
    @Override
    public void useCardImpl(TurnHandler turnHandler) throws NotEnoughCoins, IOException {
        buyCard(turnHandler);
        turnHandler.getCurrentPlayer().setExtraInfluence(2);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Two extra influence points acquired"));
    }

    /**
     * The extra influence points of the player are set back to zero
     */
    @Override
    public void resetCard(TurnHandler turnHandler){
        turnHandler.getCurrentPlayer().setExtraInfluence(0);
    }
}
