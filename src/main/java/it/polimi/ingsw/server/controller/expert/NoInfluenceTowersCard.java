package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.*;

import java.io.IOException;

public class NoInfluenceTowersCard extends CharacterCard{
    private Island noInfluenceIsland;

    public NoInfluenceTowersCard(){
        super(3);
        noInfluenceIsland = null;
        setDescription("you can choose an island on which, during this round, towers won't be included in influence calculation");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        noInfluenceIsland = turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex - 1);
        noInfluenceIsland.setTowersBanned(true);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Towers won't be computed in the influence calculation for this island"));
    }

    @Override
    public void resetCard(TurnHandler turnHandler){
        if(noInfluenceIsland != null && noInfluenceIsland.isTowersBanned())
            noInfluenceIsland.setTowersBanned(false);
    }
}
