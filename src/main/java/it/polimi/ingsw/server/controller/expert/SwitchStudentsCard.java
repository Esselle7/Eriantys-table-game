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
public class SwitchStudentsCard extends CharacterCard{

    public SwitchStudentsCard(){
        super(1,6);
        setDescription("you can switch a maximum of three students of your choice from your entrance room with three students of your choice from this card");
    }

    /**
     * This card asks up to 3 times the player: 1) Which colour he wants to move from the Entrance room 2) Which colour he wants to
     * move from the Card 3) If he wants to go on
     * Between 2) and 3) the card notifies the player if the switch was successful or if the colours were wrong
     */
    @Override
    public void useCardImpl(TurnHandler turnHandler) throws IOException, ChooseCharacterCardException, NotEnoughCoins {
        buyCard(turnHandler);
        int studentsMoved = 0, chooseAnother, studentColourToDrop, studentColourToChoose;
        do {
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Colour to drop"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
            studentColourToDrop = turnHandler.getCurrentClient().receiveChooseInt();
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Colour to get from the card"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourCMI());
            studentColourToChoose = turnHandler.getCurrentClient().receiveChooseInt();
            if(students[studentColourToChoose] > 0 && turnHandler.getCurrentPlayer().getPlayerBoard().getEntranceRoom()[studentColourToDrop] > 0){
                studentsMoved++;
                students[studentColourToChoose]--;
                students[studentColourToDrop]++;
                turnHandler.getCurrentPlayer().getPlayerBoard().getEntranceRoom()[studentColourToChoose]++;
                turnHandler.getCurrentPlayer().getPlayerBoard().getEntranceRoom()[studentColourToDrop]--;
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Students successfully switched"));
            } else {
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Invalid card or entrance room colours selected"));
            }
            if(studentsMoved == 3){
                turnHandler.getCurrentClient().sendMessage(new NotificationCMI("No more switches allowed"));
                break;
            }
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Do you want to keep moving: 1 for yes, 0 for no"));
            turnHandler.getCurrentClient().sendMessage(new chooseYesOrNoCMI());
            chooseAnother = turnHandler.getCurrentClient().receiveChooseInt();
        } while (chooseAnother == 1);
    }

    @Override
    public void initializeCard(TurnHandler turnHandler) {
        students = turnHandler.getGameMoves().generateStudents(6);
    }
}
