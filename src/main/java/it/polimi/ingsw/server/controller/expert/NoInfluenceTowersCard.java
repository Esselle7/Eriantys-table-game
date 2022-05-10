package it.polimi.ingsw.server.controller.expert;

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
        setDescription("...");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        noInfluenceIsland = turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex);
        noInfluenceIsland.setTowersBanned(true);
    }

    @Override
    public void resetCard(TurnHandler turnHandler){
        noInfluenceIsland.setTowersBanned(false);
    }
}
