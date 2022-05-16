package it.polimi.ingsw.client.gui.Scenes;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class GuiGetServerInfoScene implements Runnable{

    @Override
    public void run() { // qui creo solamente la scena e la attacco al root (sostituisco scena)

        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/LoginScene.fxml"))); // load del fxml per sever info porta e ip
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene newScene = new Scene(root);
        GuiMain.updateScene(newScene,"Server IP/Port");
    }
}
