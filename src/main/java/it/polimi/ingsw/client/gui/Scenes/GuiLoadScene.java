package it.polimi.ingsw.client.gui.Scenes;

import it.polimi.ingsw.client.gui.Gui;
import it.polimi.ingsw.client.gui.GuiMain;
import it.polimi.ingsw.client.gui.ScenesController.GuiPlaygroundController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.Objects;

/**
 * This class allows to load a new xml scene. By using the switch
 * constructor the run method understand how to fit the new window.
 */

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
        switch (xml) {
            case "Playground": {
                GuiMain.updateScene(root,xml);
                GuiPlaygroundController controller = loader.getController();
                controller.notificationLabel.setText(GuiPlaygroundController.getNotification());
                controller.updatePlayground();
                break;
            }
            case "Stats": {
                GuiMain.updateScene(root,xml);
                GuiPlaygroundController controller = loader.getController();
                controller.notificationLabel.setText(GuiPlaygroundController.getNotification());
                controller.updateStats();
                break;
            }
            case "Board": {
                GuiMain.updateSceneBoard(root,xml,440,850);
                GuiPlaygroundController controller = loader.getController();
                controller.notificationLabel.setText(GuiPlaygroundController.getNotification());
                controller.updateBoard();
                break;
            }
            case "BoardOtherPlayerOne":{
                GuiMain.updateSceneBoard(root,xml,400,850);
                GuiPlaygroundController controller = loader.getController();
                controller.updateBoardPlayerOne();
                break;
            }
            case "BoardOtherPlayerTwo":{
                GuiMain.updateSceneBoard(root,xml,400,850);
                GuiPlaygroundController controller = loader.getController();
                controller.updateBoardPlayerTwo();
                break;
            }
            case "CloudTilesPlayers":{
                GuiMain.updateNewWindow(root,xml,850,600);
                GuiPlaygroundController controller = loader.getController();
                controller.notificationLabel.setText(GuiPlaygroundController.getNotification());
                controller.updateCloudTiles();
                break;
            }
            case "ChooseYesNo":{
                GuiMain.updateNewWindow(root,xml,850,600);
                GuiPlaygroundController controller = loader.getController();
                controller.notificationLabel.setText(GuiPlaygroundController.getNotification());
                break;
            }
            case "CharacterCard":{
                GuiMain.updateScene(root,xml);
                GuiPlaygroundController controller = loader.getController();
                controller.notificationLabel.setText(GuiPlaygroundController.getNotification());
                controller.updateCharacter();
                break;
            }
            case "ChooseStudent":{
                GuiMain.updateNewWindow(root,xml,550,400);
                GuiPlaygroundController controller = loader.getController();
                controller.notificationLabel.setText(GuiPlaygroundController.getNotification());
                break;
            }
            default:
                GuiMain.updateScene(root,xml);
                break;
        }
    }
}

