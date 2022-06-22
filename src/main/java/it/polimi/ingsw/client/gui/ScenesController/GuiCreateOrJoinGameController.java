package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This class allow the player to choose if they want to
 * create a new game (and to be the lobby leader) or to be added
 * to an existing lobby
 */
public class GuiCreateOrJoinGameController {
    public Button leader;
    public Button newGame;
    public Label notificationLabel;

    /**
     * Button event handler for leader
     */
    public void returnLeader()
    {
        GuiMain.getQueue().add(1);
        System.out.println("You will be a lobby leader");
    }
    /**
     * Button event handler for added to a game
     */
    public void returnAddToGame()
    {
        GuiMain.getQueue().add(0);
        System.out.println("You will be add to a game asap");
        notificationLabel.setText("You will be added to a game...");
    }
}
