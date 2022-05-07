package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.Exceptions.EmptyDiningRoom;
import it.polimi.ingsw.server.controller.TurnHandler;
import it.polimi.ingsw.server.model.*;

public class BackInTheBagCard extends CharacterCard{

    public BackInTheBagCard(TurnHandler turnHandler){
        super(turnHandler, 3);
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
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
