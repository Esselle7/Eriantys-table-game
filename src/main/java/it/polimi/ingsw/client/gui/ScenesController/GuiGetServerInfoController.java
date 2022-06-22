package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.Gui;
import it.polimi.ingsw.client.gui.GuiMain;
import it.polimi.ingsw.client.gui.Scenes.GuiLoadScene;
import javafx.application.Platform;
import javafx.scene.control.*;

/**
 * This class allows retrieving ip and port to open a socket
 */
public class GuiGetServerInfoController {
    public TextField ipLabel;
    public TextField portLabel;
    public Button submitButton;

    /**
     * Initialize with default value
     */
    public void initialize()
    {
        ipLabel.setText("127.0.0.1");
        portLabel.setText("4500");
    }

    /**
     * Button event handler to open a socket with ip and port
     * given in input
     */
    public void openSocket()
    {
        String ip = ipLabel.getText();
        int port = 4500;
        try {
            port = Integer.parseInt(portLabel.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The port number is not valid!", ButtonType.OK);
            alert.showAndWait();
            Platform.runLater(() ->
                    new GuiLoadScene("LoginPage").run());
        }
        submitButton.setDisable(true);
        GuiMain.getQueue().add(ip);
        GuiMain.getQueue().add(port);
    }
}
