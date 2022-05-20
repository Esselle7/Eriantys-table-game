package it.polimi.ingsw.server.controller.expert;


import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class DrawStudentsDiningCard extends CharacterCard {

    public DrawStudentsDiningCard(){
        super(2);
        setDescription("you can pick one student from this card and place it in your dining room");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        int[] students = turnHandler.getGameMoves().generateStudents(4);
        buyCard(turnHandler);
        int colour;
        do {
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Select the colour of the student to pick"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            colour = turnHandler.getCurrentClient().receiveChooseInt();

        } while(students[colour] <= 0);
        students[colour]--;
        turnHandler.getCurrentPlayer().getPlayerBoard().getDiningRoom()[colour]++;
        turnHandler.getGameMoves().addStudentsToTarget(students,turnHandler.getGameMoves().generateStudents(1));
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Student successfully moved"));
    }
}
