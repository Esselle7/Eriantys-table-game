package it.polimi.ingsw.client.gui.Scenes;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class prova implements Runnable {


        @Override
        public void run() { // qui creo solamente la scena e la attacco al root (sostituisco scena)

            Group root = new Group();
            Scene newScene = new Scene(root, 300, 300, Color.BLACK);
            GuiMain.updateScene(newScene,"Prova");
        }

}
