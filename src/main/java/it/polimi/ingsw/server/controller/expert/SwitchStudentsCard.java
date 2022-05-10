package it.polimi.ingsw.server.controller.expert;

import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.network.messages.chooseWhereToMove;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class SwitchStudentsCard extends CharacterCard{

    public SwitchStudentsCard(){
        super(1);
        setDescription("...");

    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        int[] students = turnHandler.getGameMoves().generateStudents(6);
        buyCard(turnHandler);
        int studentsMoved = 0, chooseAnother, studentColourToDrop, studentColourToChoose;
        do {
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            studentColourToDrop = turnHandler.getCurrentClient().receiveChooseInt();
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            studentColourToChoose = turnHandler.getCurrentClient().receiveChooseInt();
            if(students[studentColourToChoose] > 0 && turnHandler.getCurrentPlayer().getPlayerBoard().getEntranceRoom()[studentColourToDrop] > 0){
                studentsMoved++;
                students[studentColourToChoose]--;
                students[studentColourToDrop]++;
                turnHandler.getCurrentPlayer().getPlayerBoard().getEntranceRoom()[studentColourToChoose]++;
                turnHandler.getCurrentPlayer().getPlayerBoard().getEntranceRoom()[studentColourToDrop]--;
            } else {
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No students available"));
            }
            turnHandler.getCurrentClient().sendMessage(new chooseWhereToMove());
            chooseAnother = turnHandler.getCurrentClient().receiveChooseInt();
        } while (chooseAnother == 1 && studentsMoved < 3);
    }
}
