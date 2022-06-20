package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.ChooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.*;

import java.io.IOException;

/**
 * Specific Character Card Class, its effect is listed in its description and its methods implement it
 */
public class NoInfluenceTowersCard extends CharacterCard{
    private Island noInfluenceIsland;

    public NoInfluenceTowersCard(){
        super(3,5);
        noInfluenceIsland = null;
        setDescription("you can choose an island on which, during this round, towers won't be included in influence calculation");
    }

    /**
     * The player is asked which island he wants to apply the effect to and , if the island index is valid, the island's towers
     * are set to be banned from the influence count
     */
    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, ChooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Please choose which Island"));
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        noInfluenceIsland = turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex - 1);
        noInfluenceIsland.setTowersBanned(true);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Towers won't be computed in the influence calculation for this island"));
    }

    /**
     * The island whose towers are banned is set back to a normal state and its towers will be calculated
     */
    @Override
    public void resetCard(TurnHandler turnHandler){
        if(noInfluenceIsland != null && noInfluenceIsland.isTowersBanned())
            noInfluenceIsland.setTowersBanned(false);
    }
}
