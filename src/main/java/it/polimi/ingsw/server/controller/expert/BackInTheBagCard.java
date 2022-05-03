package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.TurnHandler;

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

    }
}
