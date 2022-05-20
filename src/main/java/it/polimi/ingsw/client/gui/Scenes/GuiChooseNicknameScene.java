package it.polimi.ingsw.client.gui.Scenes;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class GuiChooseNicknameScene implements Runnable{
    @Override
    public void run() {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/NicknamePage.fxml"))); // load del fxml per sever info porta e ip
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        GuiMain.updateScene(root,"Nickname Page");
    }
}
