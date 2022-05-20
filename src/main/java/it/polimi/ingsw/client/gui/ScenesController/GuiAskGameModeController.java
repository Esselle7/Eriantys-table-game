package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GuiAskGameModeController{

    public Button twoPlayers;
    public Button threePlayers;
    public Button easyMode;
    public Button advanceMode;
    public Label notificationLabel;
    public Label notificationLabel2;

    public void chosePlayersMode(int lobbySize)
    {
        GuiMain.getQueue().add(lobbySize);
        System.out.println("Size"+lobbySize);
        notificationLabel.setText("2 Players Mode");
    }

    public void choseGameMode(int mode)
    {
        GuiMain.getQueue().add(mode);
        if(mode == 1)
        {
            System.out.println("Expert Mode");
            notificationLabel2.setText("Expert Mode");
        }
        else
        {
            notificationLabel2.setText("Normal Mode");
            System.out.println("Normal Mode");
        }


    }

    public void choseTwoGameMode() {

        chosePlayersMode(2);
    }

    public void choseThreeGameMode() {
        chosePlayersMode(3);
    }

    public void choseExpertMode(){
        choseGameMode(1);
    }

    public void choseNormalMode(){
        choseGameMode(0);
    }
}
