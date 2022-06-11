package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GuiCreateOrJoinGameController {
    public Button leader;
    public Button newGame;
    public Label notificationLabel;

    public void returnLeader()
    {
        GuiMain.getQueue().add(1);
        System.out.println("You will be a lobby leader");
    }

    public void returnNewGame()
    {
        GuiMain.getQueue().add(0);
        System.out.println("You will be add to a game asap");
        notificationLabel.setText("You will be added to a game...");
    }
}
