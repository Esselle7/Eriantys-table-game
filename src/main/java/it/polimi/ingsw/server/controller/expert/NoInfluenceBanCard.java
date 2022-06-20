package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.UnableToUseCardException;
import it.polimi.ingsw.server.controller.Exceptions.ChooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.Island;

import java.io.IOException;

/**
 * Specific Character Card Class, its effect is listed in its description and its methods implement it
 */
public class NoInfluenceBanCard extends CharacterCard{

    public NoInfluenceBanCard(){
        super(2,4);
        banCardNumber = 4;
        setDescription("you can choose to ban an island so that the next time mother nature is on it, influence will not be calculated");
    }

    /**
     * If there's at least one banCard available, the player is asked which island he wants to put the banCard on and the
     * island, if valid, is set to banned
     */
    public void useCardImpl(TurnHandler turnHandler) throws IOException, ChooseCharacterCardException, NotEnoughCoins, UnableToUseCardException {
        if(banCardNumber > 0){
            buyCard(turnHandler);
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Please choose which Island"));
            turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
            int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
            Island selectedIsland = turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex - 1);
            selectedIsland.setBanned(true);
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Influence Banner successfully set to the island"));
            banCardNumber--;
        } else {
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No ban cards available"));
            throw new UnableToUseCardException();
        }
    }

    /**
     * The banCardNumber is recalculated by counting how many islands are banned (when calculating influence on
     * an island which is banned, influence won't be calculated and the island's banned status will be set to false)
     */
    @Override
    public void resetCard(TurnHandler turnHandler) {
        int newBanCardNumber = 4;
        for(Island island: turnHandler.getGameMoves().getCurrentGame().getIslands()){
            if(island.isBanned())
                newBanCardNumber--;
        }
        banCardNumber = newBanCardNumber;
    }
}
