package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.Exceptions.EmptyTowerYard;
import it.polimi.ingsw.server.controller.Exceptions.GameWonException;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.Island;

import java.io.IOException;

public class InfluenceCalculateCard extends CharacterCard{

    public InfluenceCalculateCard(){
        super(3);
        setDescription("you can choose an island on which influence can be calculated, however this round mothernature will move normally");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins, EmptyTowerYard, GameWonException {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        Island island = turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex);
        turnHandler.influenceUpdate(island);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Influence will be calculated on this island"));
    }
}
