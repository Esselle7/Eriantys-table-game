package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourCMI;
import it.polimi.ingsw.network.messages.chooseYesOrNoCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.ChooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import java.io.IOException;

/**
 * Specific Character Card Class, its effect is listed in its description and its methods implement it
 */
public class SwitchEntranceDiningCard extends CharacterCard{

    public SwitchEntranceDiningCard(){
        super(1,10);
        setDescription("you can choose to switch a maximum of two students of your choice from your dining room to the entrance room");
    }

    /**
     * This card asks up to 3 times the player: 1) Which colour he wants to move from the Dining room 2) Which colour he wants to
     * move from the Entrance room 3) If he wants to go on
     * Between 2) and 3) the card notifies the player if the switch was successful or if the colours were wrong
     */
    @Override
    public void useCardImpl(TurnHandler turnHandler) throws ChooseCharacterCardException, IOException, NotEnoughCoins {
        buyCard(turnHandler);
        int[] diningRoom = turnHandler.getCurrentPlayer().getPlayerBoard().getDiningRoom();
        int[] entranceRoom = turnHandler.getCurrentPlayer().getPlayerBoard().getEntranceRoom();
        int studentsMoved = 0, chooseAnother, diningToMove, entranceToMove;
        do {
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Dining choose colour"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
            diningToMove = turnHandler.getCurrentClient().receiveChooseInt();
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Entrance choose colour"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
            entranceToMove = turnHandler.getCurrentClient().receiveChooseInt();
            if(entranceRoom[entranceToMove] > 0 && diningRoom[diningToMove] > 0){
                studentsMoved++;
                diningRoom[diningToMove]--;
                diningRoom[entranceToMove]++;
                entranceRoom[diningToMove]++;
                entranceRoom[entranceToMove]--;
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Students successfully switched"));
            } else {
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Invalid dining room or entrance room colours selected"));
            }
            if(studentsMoved == 2){
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No more switches allowed"));
                break;
            }
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Do you want to keep moving: 1 for yes, 0 for no"));
            turnHandler.getCurrentClient().sendMessage(new chooseYesOrNoCMI());
            chooseAnother = turnHandler.getCurrentClient().receiveChooseInt();
        } while (chooseAnother == 1);
    }
}
