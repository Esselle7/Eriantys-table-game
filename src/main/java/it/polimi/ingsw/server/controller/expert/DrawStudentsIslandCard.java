package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.*;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;

import java.io.IOException;

public class DrawStudentsIslandCard extends CharacterCard {

    public DrawStudentsIslandCard(){
        super(1);
        setDescription("...");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        int[] students = turnHandler.getGameMoves().generateStudents(4);
        buyCard(turnHandler);
        int colour=0;
        do {
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Select the colour of the student to pick"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            try
            {
                colour = turnHandler.getCurrentClient().receiveChooseInt();
            }
            catch (chooseCharacterCardException ignored) {}
        } while(students[colour] <= 0);
        students[colour] = students[colour] - 1;
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex).setPlacedStudent(colour);
        turnHandler.getGameMoves().addStudentsToTarget(students, turnHandler.getGameMoves().generateStudents(1));
    }
}