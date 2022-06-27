package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.GuiMain;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * This class control the game settings set by the game leader
 * (e.g. easy ora advance mode, two or three players)
 */
public class GuiAskGameModeController{

    public Button twoPlayers;
    public Button threePlayers;
    public Button easyMode;
    public Button advanceMode;
    public Label notificationLabel;
    public Label notificationLabel2;
    public Label waiting;
    private boolean firstPass = false;
    private boolean secondPass = false;

    /**
     * This method allows to set the lobby size
     * @param lobbySize number of players
     */
    public void chosePlayersMode(int lobbySize)
    {
        GuiMain.getQueue().add(lobbySize);
        System.out.println("Size"+lobbySize);
        notificationLabel.setText(lobbySize+" Players Mode");
        firstPass = true;
        waiting();
    }
    /**
     * This method allows to choose to play with
     * easy or advance mode (without or with character cards)
     * @param mode 1 for advance/expert 0 for easy/normal
     */
    public void choseGameMode(int mode)
    {
        GuiMain.getQueue().add(mode);
        if(mode == 1)
        {
            System.out.println("Expert Mode");
            notificationLabel2.setText("Expert Mode");
        }
        else
        {
            notificationLabel2.setText("Normal Mode");
            System.out.println("Normal Mode");
        }
        secondPass = true;
        waiting();
    }
    /**
     * If the player has chosen the two previous game mode,
     * then print in the label the waiting message
     */
    private void waiting()
    {
        if(firstPass && secondPass) {
            ReFreshConsole();
            waiting.setText("Wait for other players...");
            System.out.println("Wait for other players ...");
            ReFreshConsole();
            System.out.println("Wait for other players ...");
        }
    }
    /**
     * Button event handler method
     */
    public void choseTwoGameMode() {

        chosePlayersMode(2);
        twoPlayers.setDisable(true);
        threePlayers.setDisable(true);
    }
    /**
     * Button event handler method
     */
    public void choseThreeGameMode() {
        chosePlayersMode(3);
        twoPlayers.setDisable(true);
        threePlayers.setDisable(true);
    }
    /**
     * Button event handler method
     */
    public void choseExpertMode(){
        choseGameMode(1);
        easyMode.setDisable(true);
        advanceMode.setDisable(true);
    }
    /**
     * Button event handler method
     */
    public void choseNormalMode(){
        choseGameMode(0);
        easyMode.setDisable(true);
        advanceMode.setDisable(true);
    }

    private void ReFreshConsole()
    {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {}
    }
}
