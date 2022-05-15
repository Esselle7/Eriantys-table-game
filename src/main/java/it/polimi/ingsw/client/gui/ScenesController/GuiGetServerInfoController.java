package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.Gui;
import it.polimi.ingsw.client.gui.GuiMain;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class GuiGetServerInfoController {

    // qui gestisco bottoni da fxml

    public TextField serverIpBox;
    public TextField serverPortBox;
    public Button connectBtn;


    public void function()
    {
        String ip = serverIpBox.getText();
        int port;
        try {
            port = Integer.parseInt(serverPortBox.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The port number is not valid!", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        connectBtn.setDisable(true);
        GuiMain.getQueue().add(ip);
        GuiMain.getQueue().add(port);


    }
}
