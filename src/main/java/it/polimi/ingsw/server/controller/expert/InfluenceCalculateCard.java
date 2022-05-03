package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.Island;

public class InfluenceCalculateCard extends CharacterCard{

    public InfluenceCalculateCard(TurnHandler turnHandler){
        super(turnHandler, 3);
    }

    @Override
    public void useCard() throws Exception{
        buyCard();
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        Island island = turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex);
        turnHandler.influenceUpdate(island);
    }
}
