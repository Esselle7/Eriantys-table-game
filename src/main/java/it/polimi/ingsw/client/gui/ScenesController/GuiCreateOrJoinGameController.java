package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.scene.control.Button;

public class GuiCreateOrJoinGameController {
    public Button leader;
    public Button newGame;
    public void returnLeader()
    {
        GuiMain.getQueue().add(1);
        System.out.println("You will be a lobby leader");
    }

    public void returnNewGame()
    {
        GuiMain.getQueue().add(0);
        System.out.println("You will be add to a game asap");
    }
}
