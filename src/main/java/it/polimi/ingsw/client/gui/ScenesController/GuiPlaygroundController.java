package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.Scenes.GuiLoadScene;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class GuiPlaygroundController {

    public Label notificationLabel;

    public void switchToBoard()
    {
        Platform.runLater(() -> new GuiLoadScene("Board").run());
        notificationLabel.setText("Cambio a Board");
    }

    public void switchToPlayground()
    {
        Platform.runLater(() -> new GuiLoadScene("Playground").run());
        notificationLabel.setText("Cambio a Playground");
    }

    public void switchToSettings()
    {
        Platform.runLater(() -> new GuiLoadScene("Settings").run());
    }

    public void switchToStats()
    {
        Platform.runLater(() -> new GuiLoadScene("Stats").run());
    }
}
