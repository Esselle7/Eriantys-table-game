package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.Exceptions.EmptyDiningRoom;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.*;

import java.io.IOException;

public class BackInTheBagCard extends CharacterCard{

    public BackInTheBagCard(){
        super(3);
        setDescription("...");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Select the colour of the student to pick"));
        turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
        int colour = turnHandler.getCurrentClient().receiveChooseInt();
        for (Player player: turnHandler.getGameMoves().getCurrentGame().getPlayersList()){
            for(int i = 0; i < 3; i++){
                try {
                    player.getPlayerBoard().decreaseDiningRoom(colour);
                } catch (EmptyDiningRoom e) {
                    break;
                }
            }
        }
    }


}
