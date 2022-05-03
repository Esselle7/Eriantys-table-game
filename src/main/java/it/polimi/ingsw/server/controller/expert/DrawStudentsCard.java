package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.*;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.noStudentForColour;

public class DrawStudentsCard extends CharacterCard {
    private int[] students;

    public DrawStudentsCard(TurnHandler turnHandler){
        super(turnHandler);
        this.students = gameMoves.generateStudents(4);
        this.price = 1;
    }

    public void useCard() throws Exception {
        buyCard();
        while true {
            turnHandler.getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
            int colour = turnHandler.getCurrentClient().receiveChooseInt();
            if (students[colour] > 0) {
                students[colour] = students[colour] - 1;
                //No need to use addStudentsToTarget since we have to move only one student
                turnHandler.getCurrentClient().sendMessage(new chooseIslandCMI());
                int islandIndex = turnHandler.getCurrentClient().receiveChooseInt();
                gameMoves.getCurrentGame().getIslandByIndex(islandIndex).setPlacedStudent(colour);
                gameMoves.addStudentsToTarget(this.students, gameMoves.generateStudents(1));
            } else
                throw new noStudentForColour();
        }
    }
}
