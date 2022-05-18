package it.polimi.ingsw.client.gui.Scenes;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class GuiAskGameModeScene implements Runnable{
    @Override
    public void run() {

        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ChoosePlayers.fxml"))); // load del fxml per sever info porta e ip
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        GuiMain.updateScene(root,"Game Mode");
    }
}
