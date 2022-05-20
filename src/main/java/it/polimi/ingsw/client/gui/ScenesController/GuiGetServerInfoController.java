package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.Gui;
import it.polimi.ingsw.client.gui.GuiMain;
import javafx.application.Platform;
import javafx.scene.control.*;

public class GuiGetServerInfoController {


    public TextField ipLabel;
    public TextField portLabel;
    public Button submitButton;

    public void initialize()
    {
        ipLabel.setText("127.0.0.1");
        portLabel.setText("5000");
    }

    public void openSocket()
    {
        String ip = ipLabel.getText();
        int port;
        try {
            port = Integer.parseInt(portLabel.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The port number is not valid!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        submitButton.setDisable(true);
        GuiMain.getQueue().add(ip);
        GuiMain.getQueue().add(port);


    }
}
