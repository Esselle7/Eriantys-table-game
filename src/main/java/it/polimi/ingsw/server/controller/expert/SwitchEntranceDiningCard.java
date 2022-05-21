package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourCMI;
import it.polimi.ingsw.network.messages.chooseWhereToMove;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;
import java.io.IOException;

public class SwitchEntranceDiningCard extends CharacterCard{

    public SwitchEntranceDiningCard(){
        super(1);
        setDescription("you can choose to switch a maximum of two students of your choice from your dining room to the entrance room");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws chooseCharacterCardException, IOException, NotEnoughCoins {
        buyCard(turnHandler);
        int[] diningRoom = turnHandler.getCurrentPlayer().getPlayerBoard().getDiningRoom();
        int[] entranceRoom = turnHandler.getCurrentPlayer().getPlayerBoard().getEntranceRoom();
        int studentsMoved = 0, chooseAnother, diningToMove, entranceToMove;
        do {
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Dining"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
            diningToMove = turnHandler.getCurrentClient().receiveChooseInt();
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Entrance"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
            entranceToMove = turnHandler.getCurrentClient().receiveChooseInt();
            if(entranceRoom[entranceToMove] > 0 && diningRoom[diningToMove] > 0){
                studentsMoved++;
                diningRoom[diningToMove]--;
                diningRoom[entranceToMove]++;
                entranceRoom[diningToMove]++;
                entranceRoom[entranceToMove]--;
            } else {
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No students available"));
            }
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Students successfully switched"));
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Do you want to keep moving: 1 for yes, 0 for no"));
            turnHandler.getCurrentClient().sendMessage(new chooseWhereToMove());
            chooseAnother = turnHandler.getCurrentClient().receiveChooseInt();
        } while (chooseAnother == 1 && studentsMoved < 2);
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No more switches allowed"));
    }
}
