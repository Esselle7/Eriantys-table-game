package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.scene.input.MouseEvent;

public class GuiAskGameModeController{

    public void choseGameMode(int lobbySize)
    {
        GuiMain.getQueue().add(lobbySize);
        System.out.println("Size"+lobbySize);
    }

    public void choseTwoGameMode(MouseEvent mouseEvent) {
        choseGameMode(2);
    }

    public void choseThreeGameMode(MouseEvent mouseEvent) {
        choseGameMode(3);
    }
}
