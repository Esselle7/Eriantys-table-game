package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class GuiAskGameModeController{

    public Button twoPlayers;
    public Button threePlayers;

    public void choseGameMode(int lobbySize)
    {
        GuiMain.getQueue().add(lobbySize);
        System.out.println("Size"+lobbySize);
    }

    public void choseTwoGameMode(ActionEvent mouseEvent) {
        choseGameMode(2);
    }

    public void choseThreeGameMode(ActionEvent mouseEvent) {
        choseGameMode(3);
    }
}
