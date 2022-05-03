package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.network.messages.chooseWhereToMove;
import it.polimi.ingsw.server.controller.TurnHandler;

public class SwitchStudentsCard extends CharacterCard{
    private int[] students;

    public SwitchStudentsCard(TurnHandler turnHandler){
        super(turnHandler, 1);
        this.students = gameMoves.generateStudents(6);
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
        int studentsMoved = 0, chooseAnother, studentColourToDrop, studentColourToChoose;
        do {
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            studentColourToDrop = turnHandler.getCurrentClient().receiveChooseInt();
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            studentColourToChoose = turnHandler.getCurrentClient().receiveChooseInt();
            if(this.students[studentColourToChoose] > 0 && currentPlayer.getPlayerBoard().getEntranceRoom()[studentColourToDrop] > 0){
                studentsMoved++;
                this.students[studentColourToChoose]--;
                currentPlayer.getPlayerBoard().getEntranceRoom()[studentColourToChoose]++;
                currentPlayer.getPlayerBoard().getEntranceRoom()[studentColourToDrop]--;
            } else {
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No students available"));
            }
            turnHandler.getCurrentClient().sendMessage(new chooseWhereToMove());
            chooseAnother = turnHandler.getCurrentClient().receiveChooseInt();
        } while (chooseAnother == 1 && studentsMoved < 3);
    }
}
