package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class allow the player to choose a proper nickname
 */
public class GuiChooseNicknameController {
    public Label notificationLabel;
    public Button submitButton;
    public TextField textFieldNickname;

    /**
     * Button event handler to submit the nickname
     */
    public void submitNickname()
    {
        String nickname = textFieldNickname.getText();
        if(nickname.equals(""))
            notificationLabel.setText("Please insert a valid nickname");
        else
        {
            GuiMain.getQueue().add(nickname);
            notificationLabel.setText("Waiting for other players to choose nickname ...");
        }
    }
}
