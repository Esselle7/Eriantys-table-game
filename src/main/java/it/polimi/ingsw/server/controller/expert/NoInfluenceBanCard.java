package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.server.controller.Exceptions.UnableToUseCardException;
import it.polimi.ingsw.server.controller.TurnHandler;

public class NoInfluenceBanCard extends CharacterCard{
    int banCardNumber;

    public NoInfluenceBanCard(TurnHandler turnHandler){
        super(turnHandler, 2);
        banCardNumber = 4;
    }

    public void useCard() throws Exception{
        if(banCardNumber > 0){
            buyCard();
            turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
            int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
            turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex).setBanned(true);
            banCardNumber--;
        } else
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No ban cards available"));
            throw new UnableToUseCardException();
    }
}
