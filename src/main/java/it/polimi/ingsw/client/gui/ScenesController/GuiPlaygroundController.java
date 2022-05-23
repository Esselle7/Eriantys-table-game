package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.gui.Gui;
import it.polimi.ingsw.client.gui.GuiMain;
import it.polimi.ingsw.client.gui.Scenes.GuiLoadScene;
import it.polimi.ingsw.client.model.ClientColour;
import it.polimi.ingsw.server.model.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GuiPlaygroundController {

    // Game Model
    private static String myNickname = null;
    private static PlayGround playGround = null;
    private static Board myBoard = null;
    private static Deck myDeck = null;
    private static Card myCurrentCard = null;
    private static int expert;

    // Game fx id references
    public Label notificationLabel;
    // Buttons to display others players board
    public Button nickname1;
    public Button nickname2;
    // professors owner
    public Label pinkProfessor;
    public Label redProfessor;
    public Label greenProfessor;
    public Label yellowProfessor;
    public Label blueProfessor;
    // number of tower per colour
    public Label towerGray;
    public Label towerBlack;
    public Label towerWhite;
    // assistant Cards
    public ImageView assistantOne;
    public ImageView assistantTwo;
    public ImageView assistantThree;
    public ImageView assistantFour;
    public ImageView assistantFive;
    public ImageView assistantSix;
    public ImageView assistantSeven;
    public ImageView assistantEight;
    public ImageView assistantNine;
    public ImageView assistantTen;
    public List<ImageView> assistantCards;

    public GuiPlaygroundController()
    {

    }

    public void initialize()
    {
        assistantCards = new ArrayList<>();
        assistantCards.add(assistantOne);
        assistantCards.add(assistantTwo);
        assistantCards.add(assistantThree);
        assistantCards.add(assistantFour);
        assistantCards.add(assistantFive);
        assistantCards.add(assistantSix);
        assistantCards.add(assistantSeven);
        assistantCards.add(assistantEight);
        assistantCards.add(assistantNine);
        assistantCards.add(assistantTen);
        for(Player p : getPlayGround().getPlayersList())
        {
            if(!p.getNickname().equals(getMyNickname()))
            {
                if(nickname1.getText().equals("None"))
                    nickname1.setText(p.getNickname());
                else
                    nickname2.setText(p.getNickname());
            }
            if(p.getPlayerBoard().getTowerColour().equals(TColour.WHITE))
                towerWhite.setText(String.valueOf((p.getPlayerBoard().getTowerYard())));
            if(p.getPlayerBoard().getTowerColour().equals(TColour.BLACK))
                towerBlack.setText(String.valueOf((p.getPlayerBoard().getTowerYard())));
            if(p.getPlayerBoard().getTowerColour().equals(TColour.GRAY))
                towerGray.setText(String.valueOf((p.getPlayerBoard().getTowerYard())));
        }
    }

    public static String getMyNickname() {
        return myNickname;
    }

    public static void setMyNickname(String myNickname) {
        GuiPlaygroundController.myNickname = myNickname;
    }

    public static PlayGround getPlayGround() {
        return playGround;
    }

    public static void setPlayGround(PlayGround playGround) {
        GuiPlaygroundController.playGround = playGround;
    }

    public static Board getMyBoard() {
        return myBoard;
    }

    public static void setMyBoard(Board myBoard) {
        GuiPlaygroundController.myBoard = myBoard;
    }

    public static Deck getMyDeck() {
        return myDeck;
    }

    public static void setMyDeck(Deck myDeck) {
        GuiPlaygroundController.myDeck = myDeck;
    }

    public static Card getMyCurrentCard() {
        return myCurrentCard;
    }

    public static void setMyCurrentCard(Card myCurrentCard) {
        GuiPlaygroundController.myCurrentCard = myCurrentCard;
    }

    public static int getExpert() {
        return expert;
    }

    public static void setExpert(int expert) {
        GuiPlaygroundController.expert = expert;
    }

    public static void update(PlayGround playGroundNew)
    {
        setPlayGround(playGroundNew);
        for (Player p: playGroundNew.getPlayersList()) {
            if(p.getNickname().equals(getMyNickname()))
            {
                update(p.getPlayerBoard(),p.getAssistantCards(),p.getCurrentCard(),p.getPlayerBoard().getCoins());
                break;
            }
        }
    }

    private static void update(Board myBoardNew, Deck myDeckNew, Card myCurrentCardNew, int myCoins)
    {
        setMyBoard(myBoardNew);
        setMyCurrentCard(myCurrentCardNew);
        setMyDeck(myDeckNew);
        getMyBoard().setCoins(myCoins);
    }

    private void updateGui() {
        //Update assistant cards
        boolean found = false;
        for(ImageView card : assistantCards)
        {
            for(Card c : getMyDeck().getResidualCards())
            {
                if(assistantCards.indexOf(card) == c.getValue())
                    found = true;
            }
            if(!found)
            {
                card.setOpacity(0.5);
                card.setDisable(true);
            }

        }

    }

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

    private void assistantCardToUse(int card){
        if(Gui.getGamePhase().equals("assistantCard"))
            GuiMain.getQueue().add(card);
        updateGui();
    }

    public void cardOne(MouseEvent mouseEvent) {
        assistantCardToUse(1);
    }
    public void cardTwo(MouseEvent mouseEvent) {
        assistantCardToUse(2);
    }
    public void cardThree(MouseEvent mouseEvent) {
        assistantCardToUse(3);
    }
    public void cardFour(MouseEvent mouseEvent) {
        assistantCardToUse(4);
    }
    public void cardFive(MouseEvent mouseEvent) {
        assistantCardToUse(5);
    }
    public void cardSix(MouseEvent mouseEvent) {
        assistantCardToUse(6);
    }
    public void cardSeven(MouseEvent mouseEvent) {
        assistantCardToUse(7);
    }
    public void cardEight(MouseEvent mouseEvent) {
        assistantCardToUse(8);
    }
    public void cardNine(MouseEvent mouseEvent) {
        assistantCardToUse(9);
    }
    public void cardTen(MouseEvent mouseEvent) {
        assistantCardToUse(10);
    }




}
