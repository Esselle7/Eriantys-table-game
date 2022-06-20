package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.Exceptions.EmptyTowerYard;
import it.polimi.ingsw.server.controller.Exceptions.GameWonException;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.ChooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.Island;

import java.io.IOException;

/**
 * Specific Character Card Class, its effect is listed in its description and its methods implement it
 */
public class InfluenceCalculateCard extends CharacterCard{

    public InfluenceCalculateCard(){
        super(3,2);
        setDescription("you can choose an island on which influence can be calculated, however this round mothernature will move normally");
    }

    /**
     * The player is asked which island he wants to calculate the influence on and influenceUpdate is called on it
     */
    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, ChooseCharacterCardException, NotEnoughCoins, EmptyTowerYard, GameWonException {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Please choose which Island"));
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        Island island = turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex - 1);
        turnHandler.influenceUpdate(island);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Influence has been calculated on island "+ islandIndex));
    }
}
