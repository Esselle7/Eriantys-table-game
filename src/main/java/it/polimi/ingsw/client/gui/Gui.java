package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.gui.Scenes.GuiGetServerInfoScene;
import it.polimi.ingsw.client.gui.Scenes.prova;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.model.Board;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.Deck;
import it.polimi.ingsw.server.model.PlayGround;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class Gui implements View {
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

    }

    @Override
    public boolean isDefaultServer() {
        return false;
    }

    @Override
    public List<Object> getServerInfo() {
        while (!GuiMain.getQueue().isEmpty()) {
            try {
                GuiMain.getQueue().take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        Platform.runLater(() -> new GuiGetServerInfoScene().run());
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

    }

    @Override
    public String choseNickname() {
        return null;
    }

    @Override
    public void nicknameFormatError() {

    }

    @Override
    public int askGameMode() {
        while (!GuiMain.getQueue().isEmpty()) {
            try {
                GuiMain.getQueue().take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return -1;
            }
        }
        Platform.runLater(() -> new prova().run());
        try {
            return (int) GuiMain.getQueue().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public int chooseExpertMode() {
        return 0;
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
        return 0;
    }

    @Override
    public int chooseStudentColourToMove() {
        return 0;
    }

    @Override
    public int chooseIsland() {
        return 0;
    }

    @Override
    public int chooseCloudTile() {
        return 0;
    }

    @Override
    public int chooseWhereToMove() {
        return 0;
    }

    @Override
    public int chooseCharacterCard() {
        return 0;
    }

    @Override
    public void update(Board myBoardNew, Deck myDeckNew, Card myCurrentCardNew, int myCoins) {

    }

    @Override
    public void update(Object playGroundNew) {

    }

    @Override
    public void displayWinner(String winner) {

    }
}
