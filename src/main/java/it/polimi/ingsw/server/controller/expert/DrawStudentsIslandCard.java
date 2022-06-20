package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourCMI;
import it.polimi.ingsw.server.controller.*;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.ChooseCharacterCardException;

import java.io.IOException;

/**
 * Specific Character Card Class, its effect is listed in its description and its methods implement it
 */
public class DrawStudentsIslandCard extends CharacterCard {

    public DrawStudentsIslandCard(){
        super(1,1);
        setDescription("you can choose to move 1 student from this card to an island of your choice");
    }

    /**
     * The card is initialized by placing 4 students on it
     */
    @Override
    public void initializeCard(TurnHandler turnHandler) {
        this.students = turnHandler.getGameMoves().generateStudents(4);
    }

    /**
     * The card first asks the player which colour he wants and then, if the colour is correct, asks him which island
     * he wants to place it on
     */
    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, ChooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        int colour=0;
        while(true){
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Select the colour of the student to pick"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
            try
            {
                colour = turnHandler.getCurrentClient().receiveChooseInt();
            }
            catch (ChooseCharacterCardException ignored) {

            }
            if(students[colour] > 0){
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Colour successfully submitted"));
                break;
            }
            else
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Choose the right colour, no students for this colour"));
        }
        students[colour] = students[colour] - 1;
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt() - 1;
        turnHandler.getGameMoves().getCurrentGame().getIslandByIndex(islandIndex).increasePlacedStudent(colour);
        turnHandler.getGameMoves().addStudentsToTarget(this.students, turnHandler.getGameMoves().generateStudents(1));
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Student successfully moved to the island"));
    }
}
