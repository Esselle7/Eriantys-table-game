package it.polimi.ingsw.client.gui.Scenes;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class GuiLoadScene implements Runnable{
    private final String xml;
    public GuiLoadScene(String xml)
    {
        this.xml = xml;
    }
    @Override
    public void run() {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/"+xml+".fxml"))); // load del fxml per sever info porta e ip
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        GuiMain.updateScene(root,xml);
    }
}

