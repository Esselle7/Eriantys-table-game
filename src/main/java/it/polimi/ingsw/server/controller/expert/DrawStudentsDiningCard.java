package it.polimi.ingsw.server.controller.expert;


import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourCMI;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;

public class DrawStudentsDiningCard extends CharacterCard {

    public DrawStudentsDiningCard(){
        super(2,9);
        setDescription("you can pick one student from this card and place it in your dining room");
    }

    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, chooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        int colour = 0;
        while(true){
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Select the colour of the student to pick"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
            try
            {
                colour = turnHandler.getCurrentClient().receiveChooseInt();
            }
            catch (chooseCharacterCardException ignored) {

            }
            if(students[colour] > 0){
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Colour successfully submitted"));
                break;
            }
            else
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Choose the right colour, no students for this colour"));
        }
        students[colour]--;
        turnHandler.getCurrentPlayer().getPlayerBoard().getDiningRoom()[colour]++;
        turnHandler.getGameMoves().addStudentsToTarget(this.students,turnHandler.getGameMoves().generateStudents(1));
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Student successfully moved"));
    }

    @Override
    public void initializeCard(TurnHandler turnHandler) {
        this.students = turnHandler.getGameMoves().generateStudents(4);
    }
}
