package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.*;

public class NoInfluenceTowersCard extends CharacterCard{
    private Island noInfluenceIsland;

    public NoInfluenceTowersCard(TurnHandler turnHandler){
        super(turnHandler, 3);
        noInfluenceIsland = null;
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        noInfluenceIsland = turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex);
        noInfluenceIsland.setTowersBanned(true);
    }

    @Override
    public void resetCard(){
        noInfluenceIsland.setTowersBanned(false);
    }
}
