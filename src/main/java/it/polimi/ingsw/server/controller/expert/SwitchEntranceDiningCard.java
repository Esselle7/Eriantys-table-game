package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.network.messages.chooseWhereToMove;
import it.polimi.ingsw.server.controller.TurnHandler;

public class SwitchEntranceDiningCard extends CharacterCard{

    public SwitchEntranceDiningCard(TurnHandler turnHandler){
        super(turnHandler, 1);
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
        int[] diningRoom = currentPlayer.getPlayerBoard().getDiningRoom();
        int[] entranceRoom = currentPlayer.getPlayerBoard().getEntranceRoom();
        int studentsMoved = 0, chooseAnother, diningToMove, entranceToMove;
        do {
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            diningToMove = turnHandler.getCurrentClient().receiveChooseInt();
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
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
            turnHandler.getCurrentClient().sendMessage(new chooseWhereToMove());
            chooseAnother = turnHandler.getCurrentClient().receiveChooseInt();
        } while (chooseAnother == 1 && studentsMoved < 2);
    }
}
