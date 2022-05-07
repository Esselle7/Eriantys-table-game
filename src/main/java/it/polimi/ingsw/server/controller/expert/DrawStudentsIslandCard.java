package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.*;

public class DrawStudentsIslandCard extends CharacterCard {
    private int[] students;

    public DrawStudentsIslandCard(TurnHandler turnHandler){
        super(turnHandler, 1);
        this.students = gameMoves.generateStudents(4);
    }

    @Override
    public void useCard() throws Exception {
        buyCard();
        int colour;
        do {
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Select the colour of the student to pick"));
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            colour = turnHandler.getCurrentClient().receiveChooseInt();
        } while(students[colour] <= 0);
        students[colour] = students[colour] - 1;
        turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
        int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
        gameMoves.getCurrentGame().getIslandByIndex(islandIndex).setPlacedStudent(colour);
        gameMoves.addStudentsToTarget(this.students, gameMoves.generateStudents(1));
    }
}
