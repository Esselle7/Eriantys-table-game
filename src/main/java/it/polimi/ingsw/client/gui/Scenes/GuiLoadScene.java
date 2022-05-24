package it.polimi.ingsw.client.gui.Scenes;

import it.polimi.ingsw.client.gui.Gui;
import it.polimi.ingsw.client.gui.GuiMain;
import it.polimi.ingsw.client.gui.ScenesController.GuiPlaygroundController;
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
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/"+xml+".fxml")));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GuiMain.updateScene(root,xml);
        if(Gui.getGamePhase().equals("assistantCard")) {
            GuiPlaygroundController controller = loader.getController();
            controller.notificationLabel.setText(GuiPlaygroundController.getNotification());
            controller.updateStats();
        }
    }
}

