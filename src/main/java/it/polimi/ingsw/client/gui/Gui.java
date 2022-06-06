package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.gui.Scenes.*;
import it.polimi.ingsw.client.gui.ScenesController.GuiPlaygroundController;
import it.polimi.ingsw.client.model.ClientColour;
import it.polimi.ingsw.server.model.*;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class Gui implements View {



    private static String gamePhase;

    public Gui()
    {
        setGamePhase("none");
    }

    public static void setGamePhase(String gamePhase) {
        Gui.gamePhase = gamePhase;
    }

    public static String getGamePhase() {
        return gamePhase;
    }

    @Override
    public Card getMyCurrentCard() {
        return null;
    }

    @Override
    public void setMyCurrentCard(Card currentCard) {

    }

    @Override
    public String getMyNickname() {
        return null;
    }

    @Override
    public PlayGround getPlayGround() {
        return null;
    }

    @Override
    public void setPlayGround(PlayGround playGround) {

    }

    @Override
    public Board getMyBoard() {
        return null;
    }

    @Override
    public void setMyBoard(Board myBoard) {

    }

    @Override
    public Deck getMyDeck() {
        return null;
    }

    @Override
    public void setMyDeck(Deck myDeck) {

    }

    @Override
    public void setMyNickname(String myNickname) {

    }

    @Override
    public void showNotification(String text) {
        printNotification(text);
    }

    @Override
    public boolean isDefaultServer() {
        return false;
    }

    @Override
    public List<Object> getServerInfo() {
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("LoginPage").run());
        try {
            List<Object> toReturn = new ArrayList<>();
            toReturn.add(GuiMain.getQueue().take());
            toReturn.add(GuiMain.getQueue().take());
            return toReturn;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public void printNotification(String notification) {
        GuiPlaygroundController.setNotification(notification);
    }

    @Override
    public void loadView() {
        new Thread(() -> GuiMain.launch(GuiMain.class)).start();
        try {
            GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // platform run lather scene che fa la wellcome etc
    }

    @Override
    public void connectionOutcome(boolean isConnected) {
       // if(isConnected)
           // GuiController.showNotificationReceivedByServer("Connected");
    }

    @Override
    public int wantToBeLeader() {
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("ChooseLobby").run());
        try {
            return (Integer) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String choseNickname() {
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("NicknamePage").run());
        try {
            String nickname = (String) GuiMain.getQueue().take();
            GuiPlaygroundController.setMyNickname(nickname);
            return nickname;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void nicknameFormatError() {

    }

    @Override
    public int askGameMode() {
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("ChoosePlayers").run());
        try {
            return (int) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public int chooseExpertMode() {
        try {
            return (int) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void showMyInfo() {

    }

    @Override
    public void showMyDeck() {

    }

    @Override
    public void showCloudTilesInfo() {


    }

    @Override
    public void showCharacterCardsInfo() {

    }

    @Override
    public void showInfoForDecisions() {

    }

    @Override
    public int chooseAssistantCard() {
        Gui.setGamePhase("assistantCard");
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("Playground").run());
        try {
            return (int) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int chooseStudentColourToMove() {
        Gui.setGamePhase("movePhase");
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("Board").run());
        try {
            return (int) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int chooseStudentColour() {
        return 0;
    }

    @Override
    public int chooseIsland() {
        try {
            return (int) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int moveMotherNature()
    {
        Gui.setGamePhase("motherNature");
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("Playground").run());
        try {
            return (int) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int chooseYesOrNo() {
        return 0;
    }

    @Override
    public int chooseCloudTile() {
        Gui.setGamePhase("cloudTiles");
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("CloudTilesPlayers").run());
        try {
            int toReturn = (int) GuiMain.getQueue().take();
            GuiMain.getNewWindow().close();
            return toReturn;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int chooseWhereToMove() {
        Gui.setGamePhase("whereToMove");
        resetGuiQueue();
        Platform.runLater(() -> new GuiLoadScene("Board").run());
        try {
            return (int) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int chooseCharacterCard() {
        Gui.setGamePhase("characterCard");
        return 0;
    }

    @Override
    public void update(Board myBoardNew, Deck myDeckNew, Card myCurrentCardNew, int myCoins) {

    }

    @Override
    public void update(Object playGroundNew) {
        PlayGround playGroundUpdate = (PlayGround) playGroundNew;
        GuiPlaygroundController.update(playGroundUpdate);

    }

    @Override
    public void displayWinner(String winner) {

    }

    private void resetGuiQueue()
    {
        while (!GuiMain.getQueue().isEmpty()) {
            try {
                GuiMain.getQueue().take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
}
