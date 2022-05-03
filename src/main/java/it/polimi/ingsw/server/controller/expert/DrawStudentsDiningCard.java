package it.polimi.ingsw.server.controller.expert;


import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseIslandCMI;
import it.polimi.ingsw.network.messages.chooseStudentColourToMoveCMI;
import it.polimi.ingsw.server.controller.TurnHandler;

public class DrawStudentsDiningCard extends CharacterCard {
    private int[] students;

    public DrawStudentsDiningCard(TurnHandler turnHandler){
        super(turnHandler, 2);
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
        students[colour]--;
        currentPlayer.getPlayerBoard().getDiningRoom()[colour]++;
        gameMoves.addStudentsToTarget(this.students, gameMoves.generateStudents(1));
    }
}
