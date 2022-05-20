package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.UnableToUseCardException;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class NoInfluenceBanCard extends CharacterCard{
    int banCardNumber;

    public NoInfluenceBanCard(){
        super(2);
        banCardNumber = 4;
        setDescription("you can choose to ban an island so that the (only the) next time mothernature is on it, influence will not be calculated");
    }

    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins, UnableToUseCardException {
        if(banCardNumber > 0){
            buyCard(turnHandler);
            turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
            int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
            turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex).setBanned(true);
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Influence Banner successfully set to the island"));
            banCardNumber--;
        } else
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No ban cards available"));
            throw new UnableToUseCardException();
    }
}
