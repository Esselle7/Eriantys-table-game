package it.polimi.ingsw.client.gui.ScenesController;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.gui.Gui;
import it.polimi.ingsw.client.gui.GuiMain;
import it.polimi.ingsw.client.gui.Scenes.GuiLoadScene;
import it.polimi.ingsw.server.controller.expert.CharacterCard;
import it.polimi.ingsw.server.model.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javax.management.monitor.MonitorSettingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class allows to handle every event during tha game
 */
public class GuiPlaygroundController {

    // Game Model
    private static String myNickname = null;
    private static PlayGround playGround = null;
    private static Board myBoard = null;
    private static Deck myDeck = null;
    private static Card myCurrentCard = null;
    private static int expert;
    private static String notification;


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
    public List<Label> professorsControl;
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

    // current assistant card
    public Label Player1AssistantCard;
    public Label Player2AssistantCard;
    public Label Player3AssistantCard;
    public List<Label> currentAssistantCard;

    //island towers
    public Label towerIsland1;
    public Label towerIsland2;
    public Label towerIsland3;
    public Label towerIsland4;
    public Label towerIsland5;
    public Label towerIsland6;
    public Label towerIsland7;
    public Label towerIsland8;
    public Label towerIsland9;
    public Label towerIsland10;
    public Label towerIsland11;
    public Label towerIsland12;
    public List<Label> towers;

    // islands and tower and student on islands
    public GridPane island1;
    public GridPane island2;
    public GridPane island3;
    public GridPane island4;
    public GridPane island5;
    public GridPane island6;
    public GridPane island7;
    public GridPane island8;
    public GridPane island9;
    public GridPane island10;
    public GridPane island11;
    public GridPane island12;
    public List<GridPane> islands;
    public Button island1Image;
    public Button island2Image;
    public Button island3Image;
    public Button island4Image;
    public Button island5Image;
    public Button island6Image;
    public Button island7Image;
    public Button island8Image;
    public Button island9Image;
    public Button island10Image;
    public Button island11Image;
    public Button island12Image;
    public List<Button> islandsImage;
    public ImageView towerBlackIsland1;
    public ImageView towerBlackIsland2;
    public ImageView towerBlackIsland3;
    public ImageView towerBlackIsland4;
    public ImageView towerBlackIsland5;
    public ImageView towerBlackIsland6;
    public ImageView towerBlackIsland7;
    public ImageView towerBlackIsland8;
    public ImageView towerBlackIsland9;
    public ImageView towerBlackIsland10;
    public ImageView towerBlackIsland11;
    public ImageView towerBlackIsland12;
    List<ImageView> towerBlackIsland;
    public ImageView towerGreyIsland1;
    public ImageView towerGreyIsland2;
    public ImageView towerGreyIsland3;
    public ImageView towerGreyIsland4;
    public ImageView towerGreyIsland5;
    public ImageView towerGreyIsland6;
    public ImageView towerGreyIsland7;
    public ImageView towerGreyIsland8;
    public ImageView towerGreyIsland9;
    public ImageView towerGreyIsland10;
    public ImageView towerGreyIsland11;
    public ImageView towerGreyIsland12;
    List<ImageView> towerGreyIsland;
    public ImageView towerWhiteIsland1;
    public ImageView towerWhiteIsland2;
    public ImageView towerWhiteIsland3;
    public ImageView towerWhiteIsland4;
    public ImageView towerWhiteIsland5;
    public ImageView towerWhiteIsland6;
    public ImageView towerWhiteIsland7;
    public ImageView towerWhiteIsland8;
    public ImageView towerWhiteIsland9;
    public ImageView towerWhiteIsland10;
    public ImageView towerWhiteIsland11;
    public ImageView towerWhiteIsland12;
    List<ImageView> towerWhiteIsland;
    public Label studentRed1;
    public Label studentGreen1;
    public Label studentBlue1;
    public Label studentYellow1;
    public Label studentPink1;
    public List<Label> studentIsland1;
    public Label studentRed2;
    public Label studentGreen2;
    public Label studentBlue2;
    public Label studentYellow2;
    public Label studentPink2;
    public List<Label> studentIsland2;
    public Label studentRed3;
    public Label studentGreen3;
    public Label studentBlue3;
    public Label studentYellow3;
    public Label studentPink3;
    public List<Label> studentIsland3;
    public Label studentRed4;
    public Label studentGreen4;
    public Label studentBlue4;
    public Label studentYellow4;
    public Label studentPink4;
    public List<Label> studentIsland4;
    public Label studentRed5;
    public Label studentGreen5;
    public Label studentBlue5;
    public Label studentYellow5;
    public Label studentPink5;
    public List<Label> studentIsland5;
    public Label studentRed6;
    public Label studentGreen6;
    public Label studentBlue6;
    public Label studentYellow6;
    public Label studentPink6;
    public List<Label> studentIsland6;
    public Label studentRed7;
    public Label studentGreen7;
    public Label studentBlue7;
    public Label studentYellow7;
    public Label studentPink7;
    public List<Label> studentIsland7;
    public Label studentRed8;
    public Label studentGreen8;
    public Label studentBlue8;
    public Label studentYellow8;
    public Label studentPink8;
    public List<Label> studentIsland8;
    public Label studentRed9;
    public Label studentGreen9;
    public Label studentBlue9;
    public Label studentYellow9;
    public Label studentPink9;
    public List<Label> studentIsland9;
    public Label studentRed10;
    public Label studentGreen10;
    public Label studentBlue10;
    public Label studentYellow10;
    public Label studentPink10;
    public List<Label> studentIsland10;
    public Label studentRed11;
    public Label studentGreen11;
    public Label studentBlue11;
    public Label studentYellow11;
    public Label studentPink11;
    public List<Label> studentIsland11;
    public Label studentRed12;
    public Label studentGreen12;
    public Label studentBlue12;
    public Label studentYellow12;
    public Label studentPink12;
    public List<Label> studentIsland12;
    public List<List<Label>> isl;

    //Entrance Room
    public Label entranceStudentPink;
    public Label entranceStudentBlue;
    public Label entranceStudentRed;
    public Label entranceStudentYellow;
    public Label entranceStudentGreen;
    public List<Label> entranceRoom;

    //Dining room green
    public ImageView studentBoardGreen1;
    public ImageView studentBoardGreen2;
    public ImageView studentBoardGreen3;
    public ImageView studentBoardGreen4;
    public ImageView studentBoardGreen5;
    public ImageView studentBoardGreen6;
    public ImageView studentBoardGreen7;
    public ImageView studentBoardGreen8;
    public ImageView studentBoardGreen9;
    public ImageView studentBoardGreen10;
    public List<ImageView> diningGreenStudent;

    //Dining room red
    public ImageView studentBoardRed1;
    public ImageView studentBoardRed2;
    public ImageView studentBoardRed3;
    public ImageView studentBoardRed4;
    public ImageView studentBoardRed5;
    public ImageView studentBoardRed6;
    public ImageView studentBoardRed7;
    public ImageView studentBoardRed8;
    public ImageView studentBoardRed9;
    public ImageView studentBoardRed10;
    public List<ImageView> diningRedStudent;


    //Dining room yellow
    public ImageView studentBoardYellow1;
    public ImageView studentBoardYellow2;
    public ImageView studentBoardYellow3;
    public ImageView studentBoardYellow4;
    public ImageView studentBoardYellow5;
    public ImageView studentBoardYellow6;
    public ImageView studentBoardYellow7;
    public ImageView studentBoardYellow8;
    public ImageView studentBoardYellow9;
    public ImageView studentBoardYellow10;
    public List<ImageView> diningYellowStudent;

    //Dining room pink
    public ImageView studentBoardPink1;
    public ImageView studentBoardPink2;
    public ImageView studentBoardPink3;
    public ImageView studentBoardPink4;
    public ImageView studentBoardPink5;
    public ImageView studentBoardPink6;
    public ImageView studentBoardPink7;
    public ImageView studentBoardPink8;
    public ImageView studentBoardPink9;
    public ImageView studentBoardPink10;
    public List<ImageView> diningPinkStudent;

    //Dining room blue
    public ImageView studentBoardBlue1;
    public ImageView studentBoardBlue2;
    public ImageView studentBoardBlue3;
    public ImageView studentBoardBlue4;
    public ImageView studentBoardBlue5;
    public ImageView studentBoardBlue6;
    public ImageView studentBoardBlue7;
    public ImageView studentBoardBlue8;
    public ImageView studentBoardBlue9;
    public ImageView studentBoardBlue10;
    public List<ImageView> diningBlueStudent;
    List<List<ImageView>> diningRoom;

    // cloud tile
    public GridPane cloudTile1;
    public GridPane cloudTile2;
    public GridPane cloudTile3;
    List<GridPane> paneCloudTiles;
    public Button cloudButton1;
    public Button cloudButton2;
    public Button cloudButton3;
    List<Button> cloudButton;
    public Label studentCloudRed1;
    public Label studentCloudGreen1;
    public Label studentCloudBlue1;
    public Label studentCloudYellow1;
    public Label studentCloudPink1;
    public Label studentCloudRed2;
    public Label studentCloudGreen2;
    public Label studentCloudBlue2;
    public Label studentCloudYellow2;
    public Label studentCloudPink2;
    public Label studentCloudRed3;
    public Label studentCloudGreen3;
    public Label studentCloudBlue3;
    public Label studentCloudYellow3;
    public Label studentCloudPink3;
    List<Label> firstCloudTile;
    List<Label> secondCloudTile;
    List<Label> thirdCloudTile;
    List<List<Label>> cloudTiles;

    //Professors room
    public ImageView professorBoardGreen;
    public ImageView professorBoardRed;
    public ImageView professorBoardYellow;
    public ImageView professorBoardPink;
    public ImageView professorBoardBlue;
    List<ImageView> professorsRoom;

    //Tower Yard
    public Label towerBoardBlack;
    public Label towerBoardWhite;
    public Label towerBoardGrey;
    List<Label> towerYard;

    // mother nature on island
    public ImageView motherNature1;
    public ImageView motherNature2;
    public ImageView motherNature3;
    public ImageView motherNature4;
    public ImageView motherNature5;
    public ImageView motherNature6;
    public ImageView motherNature7;
    public ImageView motherNature8;
    public ImageView motherNature9;
    public ImageView motherNature10;
    public ImageView motherNature11;
    public ImageView motherNature12;
    public List<ImageView> motherNature;

    // character cards
    public ImageView character1;
    public ImageView character2;
    public ImageView character3;
    public ImageView character4;
    public ImageView character5;
    public ImageView character6;
    public ImageView character7;
    public ImageView character8;
    public ImageView character9;
    public ImageView character10;
    public ImageView character11;
    public ImageView character12;
    List<ImageView> characterCards;
    List<ImageView> drawnCards;
    public Label character1Coin;
    public Label character2Coin;
    public Label character3Coin;
    public Label character4Coin;
    public Label character5Coin;
    public Label character6Coin;
    public Label character7Coin;
    public Label character8Coin;
    public Label character9Coin;
    public Label character10Coin;
    public Label character11Coin;
    public Label character12Coin;
    List<Label> characterCoin;
    public Label character1Red;
    public Label character1Green;
    public Label character1Blue;
    public Label character1Yellow;
    public Label character1Pink;
    List<Label> elementCharacterOne;
    public Label character4NoEntry;
    List<Label> elementCharacterFour;
    public Label character6Red;
    public Label character6Green;
    public Label character6Blue;
    public Label character6Yellow;
    public Label character6Pink;
    List<Label> elementCharacterSix;
    public Label character9Red;
    public Label character9Green;
    public Label character9Blue;
    public Label character9Yellow;
    public Label character9Pink;
    List<Label> elementCharacterNine;
    List<List<Label>> elements;
    public Label coins;

    public GuiPlaygroundController() {}

    public void initialize()
    {
        professorsControl = new ArrayList<>();
        professorsControl.add(redProfessor);
        professorsControl.add(greenProfessor);
        professorsControl.add(blueProfessor);
        professorsControl.add(pinkProfessor);
        professorsControl.add(yellowProfessor);

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

        islands = new ArrayList<>();
        islands.add(island1);
        islands.add(island2);
        islands.add(island3);
        islands.add(island4);
        islands.add(island5);
        islands.add(island6);
        islands.add(island7);
        islands.add(island8);
        islands.add(island9);
        islands.add(island10);
        islands.add(island11);
        islands.add(island12);

        islandsImage = new ArrayList<>();
        islandsImage.add(island1Image);
        islandsImage.add(island2Image);
        islandsImage.add(island3Image);
        islandsImage.add(island4Image);
        islandsImage.add(island5Image);
        islandsImage.add(island6Image);
        islandsImage.add(island7Image);
        islandsImage.add(island8Image);
        islandsImage.add(island9Image);
        islandsImage.add(island10Image);
        islandsImage.add(island11Image);
        islandsImage.add(island12Image);

        towers = new ArrayList<>();
        towers.add(towerIsland1);
        towers.add(towerIsland2);
        towers.add(towerIsland3);
        towers.add(towerIsland4);
        towers.add(towerIsland5);
        towers.add(towerIsland6);
        towers.add(towerIsland7);
        towers.add(towerIsland8);
        towers.add(towerIsland9);
        towers.add(towerIsland10);
        towers.add(towerIsland11);
        towers.add(towerIsland12);


        studentIsland1 = new ArrayList<>();
        studentIsland1.add(studentRed1);
        studentIsland1.add(studentGreen1);
        studentIsland1.add(studentBlue1);
        studentIsland1.add(studentPink1);
        studentIsland1.add(studentYellow1);

        studentIsland2 = new ArrayList<>();
        studentIsland2.add(studentRed2);
        studentIsland2.add(studentGreen2);
        studentIsland2.add(studentBlue2);
        studentIsland2.add(studentPink2);
        studentIsland2.add(studentYellow2);

        studentIsland3 = new ArrayList<>();
        studentIsland3.add(studentRed3);
        studentIsland3.add(studentGreen3);
        studentIsland3.add(studentBlue3);
        studentIsland3.add(studentPink3);
        studentIsland3.add(studentYellow3);

        studentIsland4 = new ArrayList<>();
        studentIsland4.add(studentRed4);
        studentIsland4.add(studentGreen4);
        studentIsland4.add(studentBlue4);
        studentIsland4.add(studentPink4);
        studentIsland4.add(studentYellow4);

        studentIsland5 = new ArrayList<>();
        studentIsland5.add(studentRed5);
        studentIsland5.add(studentGreen5);
        studentIsland5.add(studentBlue5);
        studentIsland5.add(studentPink5);
        studentIsland5.add(studentYellow5);

        studentIsland6 = new ArrayList<>();
        studentIsland6.add(studentRed6);
        studentIsland6.add(studentGreen6);
        studentIsland6.add(studentBlue6);
        studentIsland6.add(studentPink6);
        studentIsland6.add(studentYellow6);

        studentIsland7 = new ArrayList<>();
        studentIsland7.add(studentGreen7);
        studentIsland7.add(studentBlue7);
        studentIsland7.add(studentRed7);
        studentIsland7.add(studentPink7);
        studentIsland7.add(studentYellow7);

        studentIsland8 = new ArrayList<>();
        studentIsland8.add(studentRed8);
        studentIsland8.add(studentGreen8);
        studentIsland8.add(studentBlue8);
        studentIsland8.add(studentPink8);
        studentIsland8.add(studentYellow8);




        studentIsland9 = new ArrayList<>();
        studentIsland9.add(studentRed9);
        studentIsland9.add(studentGreen9);
        studentIsland9.add(studentBlue9);
        studentIsland9.add(studentPink9);
        studentIsland9.add(studentYellow9);

        studentIsland10 = new ArrayList<>();
        studentIsland10.add(studentRed10);
        studentIsland10.add(studentBlue10);
        studentIsland10.add(studentGreen10);
        studentIsland10.add(studentPink10);
        studentIsland10.add(studentYellow10);

        studentIsland11 = new ArrayList<>();
        studentIsland11.add(studentRed11);
        studentIsland11.add(studentGreen11);
        studentIsland11.add(studentBlue11);
        studentIsland11.add(studentPink11);
        studentIsland11.add(studentYellow11);



        studentIsland12 = new ArrayList<>();
        studentIsland12.add(studentRed12);
        studentIsland12.add(studentGreen12);
        studentIsland12.add(studentBlue12);
        studentIsland12.add(studentPink12);
        studentIsland12.add(studentYellow12);




        isl = new ArrayList<>();
        isl.add(studentIsland1);
        isl.add(studentIsland2);
        isl.add(studentIsland3);
        isl.add(studentIsland4);
        isl.add(studentIsland5);
        isl.add(studentIsland6);
        isl.add(studentIsland7);
        isl.add(studentIsland8);
        isl.add(studentIsland9);
        isl.add(studentIsland10);
        isl.add(studentIsland11);
        isl.add(studentIsland12);

        currentAssistantCard = new ArrayList<>();
        currentAssistantCard.add(Player1AssistantCard);
        currentAssistantCard.add(Player2AssistantCard);
        currentAssistantCard.add(Player3AssistantCard);


        towerGreyIsland = new ArrayList<>();
        towerGreyIsland.add(towerGreyIsland1);
        towerGreyIsland.add(towerGreyIsland2);
        towerGreyIsland.add(towerGreyIsland3);
        towerGreyIsland.add(towerGreyIsland4);
        towerGreyIsland.add(towerGreyIsland5);
        towerGreyIsland.add(towerGreyIsland6);
        towerGreyIsland.add(towerGreyIsland7);
        towerGreyIsland.add(towerGreyIsland8);
        towerGreyIsland.add(towerGreyIsland9);
        towerGreyIsland.add(towerGreyIsland10);
        towerGreyIsland.add(towerGreyIsland11);
        towerGreyIsland.add(towerGreyIsland12);

        towerBlackIsland = new ArrayList<>();
        towerBlackIsland.add(towerBlackIsland1);
        towerBlackIsland.add(towerBlackIsland2);
        towerBlackIsland.add(towerBlackIsland3);
        towerBlackIsland.add(towerBlackIsland4);
        towerBlackIsland.add(towerBlackIsland5);
        towerBlackIsland.add(towerBlackIsland6);
        towerBlackIsland.add(towerBlackIsland7);
        towerBlackIsland.add(towerBlackIsland8);
        towerBlackIsland.add(towerBlackIsland9);
        towerBlackIsland.add(towerBlackIsland10);
        towerBlackIsland.add(towerBlackIsland11);
        towerBlackIsland.add(towerBlackIsland12);

        towerWhiteIsland = new ArrayList<>();
        towerWhiteIsland.add(towerWhiteIsland1);
        towerWhiteIsland.add(towerWhiteIsland2);
        towerWhiteIsland.add(towerWhiteIsland3);
        towerWhiteIsland.add(towerWhiteIsland4);
        towerWhiteIsland.add(towerWhiteIsland5);
        towerWhiteIsland.add(towerWhiteIsland6);
        towerWhiteIsland.add(towerWhiteIsland7);
        towerWhiteIsland.add(towerWhiteIsland8);
        towerWhiteIsland.add(towerWhiteIsland9);
        towerWhiteIsland.add(towerWhiteIsland10);
        towerWhiteIsland.add(towerWhiteIsland11);
        towerWhiteIsland.add(towerWhiteIsland12);


        entranceRoom = new ArrayList<>();
        entranceRoom.add(entranceStudentRed);
        entranceRoom.add(entranceStudentGreen);
        entranceRoom.add(entranceStudentBlue);
        entranceRoom.add(entranceStudentPink);
        entranceRoom.add(entranceStudentYellow);

        diningGreenStudent = new ArrayList<>();
        diningPinkStudent = new ArrayList<>();
        diningRedStudent = new ArrayList<>();
        diningBlueStudent = new ArrayList<>();
        diningYellowStudent = new ArrayList<>();
        diningRoom = new ArrayList<>();

        diningBlueStudent.add(studentBoardBlue1);
        diningBlueStudent.add(studentBoardBlue2);
        diningBlueStudent.add(studentBoardBlue3);
        diningBlueStudent.add(studentBoardBlue4);
        diningBlueStudent.add(studentBoardBlue5);
        diningBlueStudent.add(studentBoardBlue6);
        diningBlueStudent.add(studentBoardBlue7);
        diningBlueStudent.add(studentBoardBlue8);
        diningBlueStudent.add(studentBoardBlue9);
        diningBlueStudent.add(studentBoardBlue10);

        diningRedStudent.add(studentBoardRed1);
        diningRedStudent.add(studentBoardRed2);
        diningRedStudent.add(studentBoardRed3);
        diningRedStudent.add(studentBoardRed4);
        diningRedStudent.add(studentBoardRed5);
        diningRedStudent.add(studentBoardRed6);
        diningRedStudent.add(studentBoardRed7);
        diningRedStudent.add(studentBoardRed8);
        diningRedStudent.add(studentBoardRed9);
        diningRedStudent.add(studentBoardRed10);

        diningYellowStudent.add(studentBoardYellow1);
        diningYellowStudent.add(studentBoardYellow2);
        diningYellowStudent.add(studentBoardYellow3);
        diningYellowStudent.add(studentBoardYellow4);
        diningYellowStudent.add(studentBoardYellow5);
        diningYellowStudent.add(studentBoardYellow6);
        diningYellowStudent.add(studentBoardYellow7);
        diningYellowStudent.add(studentBoardYellow8);
        diningYellowStudent.add(studentBoardYellow9);
        diningYellowStudent.add(studentBoardYellow10);

        diningGreenStudent.add(studentBoardGreen1);
        diningGreenStudent.add(studentBoardGreen2);
        diningGreenStudent.add(studentBoardGreen3);
        diningGreenStudent.add(studentBoardGreen4);
        diningGreenStudent.add(studentBoardGreen5);
        diningGreenStudent.add(studentBoardGreen6);
        diningGreenStudent.add(studentBoardGreen7);
        diningGreenStudent.add(studentBoardGreen8);
        diningGreenStudent.add(studentBoardGreen9);
        diningGreenStudent.add(studentBoardGreen10);

        diningPinkStudent.add(studentBoardPink1);
        diningPinkStudent.add(studentBoardPink2);
        diningPinkStudent.add(studentBoardPink3);
        diningPinkStudent.add(studentBoardPink4);
        diningPinkStudent.add(studentBoardPink5);
        diningPinkStudent.add(studentBoardPink6);
        diningPinkStudent.add(studentBoardPink7);
        diningPinkStudent.add(studentBoardPink8);
        diningPinkStudent.add(studentBoardPink9);
        diningPinkStudent.add(studentBoardPink10);

        diningRoom.add(diningRedStudent);
        diningRoom.add(diningGreenStudent);
        diningRoom.add(diningBlueStudent);
        diningRoom.add(diningPinkStudent);
        diningRoom.add(diningYellowStudent);

        professorsRoom = new ArrayList<>();
        professorsRoom.add(professorBoardRed);
        professorsRoom.add(professorBoardGreen);
        professorsRoom.add(professorBoardBlue);
        professorsRoom.add(professorBoardPink);
        professorsRoom.add(professorBoardYellow);

        towerYard = new ArrayList<>();
        towerYard.add(towerBoardWhite);
        towerYard.add(towerBoardGrey);
        towerYard.add(towerBoardBlack);


        firstCloudTile = new ArrayList<>();
        firstCloudTile.add(studentCloudRed1);
        firstCloudTile.add(studentCloudGreen1);
        firstCloudTile.add(studentCloudBlue1);
        firstCloudTile.add(studentCloudPink1);
        firstCloudTile.add(studentCloudYellow1);

        secondCloudTile = new ArrayList<>();
        secondCloudTile.add(studentCloudRed2);
        secondCloudTile.add(studentCloudGreen2);
        secondCloudTile.add(studentCloudBlue2);
        secondCloudTile.add(studentCloudPink2);
        secondCloudTile.add(studentCloudYellow2);

        thirdCloudTile = new ArrayList<>();
        thirdCloudTile.add(studentCloudRed3);
        thirdCloudTile.add(studentCloudGreen3);
        thirdCloudTile.add(studentCloudBlue3);
        thirdCloudTile.add(studentCloudPink3);
        thirdCloudTile.add(studentCloudYellow3);

        cloudTiles = new ArrayList<>();
        cloudTiles.add(firstCloudTile);
        cloudTiles.add(secondCloudTile);
        cloudTiles.add(thirdCloudTile);

        paneCloudTiles = new ArrayList<>();
        paneCloudTiles.add(cloudTile1);
        paneCloudTiles.add(cloudTile2);
        paneCloudTiles.add(cloudTile3);

        cloudButton = new ArrayList<>();
        cloudButton.add(cloudButton1);
        cloudButton.add(cloudButton2);
        cloudButton.add(cloudButton3);

        motherNature = new ArrayList<>();
        motherNature.add(motherNature1);
        motherNature.add(motherNature2);
        motherNature.add(motherNature3);
        motherNature.add(motherNature4);
        motherNature.add(motherNature5);
        motherNature.add(motherNature6);
        motherNature.add(motherNature7);
        motherNature.add(motherNature8);
        motherNature.add(motherNature9);
        motherNature.add(motherNature10);
        motherNature.add(motherNature11);
        motherNature.add(motherNature12);

        characterCards = new ArrayList<>();
        characterCards.add(character1);
        characterCards.add(character2);
        characterCards.add(character3);
        characterCards.add(character4);
        characterCards.add(character5);
        characterCards.add(character6);
        characterCards.add(character7);
        characterCards.add(character8);
        characterCards.add(character9);
        characterCards.add(character10);
        characterCards.add(character11);
        characterCards.add(character12);

        characterCoin = new ArrayList<>();
        characterCoin.add(character1Coin);
        characterCoin.add(character2Coin);
        characterCoin.add(character3Coin);
        characterCoin.add(character4Coin);
        characterCoin.add(character5Coin);
        characterCoin.add(character6Coin);
        characterCoin.add(character7Coin);
        characterCoin.add(character8Coin);
        characterCoin.add(character9Coin);
        characterCoin.add(character10Coin);
        characterCoin.add(character11Coin);
        characterCoin.add(character12Coin);

        elementCharacterOne = new ArrayList<>();
        elementCharacterOne.add(character1Red);
        elementCharacterOne.add(character1Green);
        elementCharacterOne.add(character1Blue);
        elementCharacterOne.add(character1Pink);
        elementCharacterOne.add(character1Yellow);

        elementCharacterFour = new ArrayList<>();
        elementCharacterFour.add(character4NoEntry);

        elementCharacterSix = new ArrayList<>();
        elementCharacterSix.add(character6Red);
        elementCharacterSix.add(character6Green);
        elementCharacterSix.add(character6Blue);
        elementCharacterSix.add(character6Pink);
        elementCharacterSix.add(character6Yellow);

        elementCharacterNine = new ArrayList<>();
        elementCharacterNine.add(character9Red);
        elementCharacterNine.add(character9Green);
        elementCharacterNine.add(character9Blue);
        elementCharacterNine.add(character9Pink);
        elementCharacterNine.add(character9Yellow);

        elements = new ArrayList<>();
        elements.add(elementCharacterOne);
        elements.add(elementCharacterFour);
        elements.add(elementCharacterSix);
        elements.add(elementCharacterNine);
    }

    public static String getNotification() {
        return notification;
    }

    public static void setNotification(String notification) {
        GuiPlaygroundController.notification = notification;
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

    /**
     * Update the model stored in the client
     * @param playGroundNew the new playground updated
     */
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

    /**
     * Update personal model info
     * @param myBoardNew new board updated
     * @param myDeckNew new deck updated
     * @param myCurrentCardNew new current card updated
     * @param myCoins new number of coins updated
     */
    private static void update(Board myBoardNew, Deck myDeckNew, Card myCurrentCardNew, int myCoins)
    {
        setMyBoard(myBoardNew);
        setMyCurrentCard(myCurrentCardNew);
        setMyDeck(myDeckNew);
        getMyBoard().setCoins(myCoins);
    }

    /**
     * Refresh the playground gui view after model playground update
     */
    public void updatePlayground()
    {
        //Update Islands
        for(int index = getPlayGround().getIslands().size(); index <islands.size();index++)
        {
            islands.get(index).setOpacity(0.0); // delete islands that have been unified
            islandsImage.get(index).setOpacity(0.0);
            for(int colour = 0; colour < Colour.colourCount; colour ++)
            {
                isl.get(index).get(colour).setOpacity(0.0);
            }
            towerWhiteIsland.get(index).setOpacity(0.0);
            towerBlackIsland.get(index).setOpacity(0.0);
            towerGreyIsland.get(index).setOpacity(0.0);
        }
        towerIsland1.setOpacity(0.0);
        towerIsland2.setOpacity(0.0);
        towerIsland3.setOpacity(0.0);
        towerIsland4.setOpacity(0.0);
        towerIsland5.setOpacity(0.0);
        towerIsland6.setOpacity(0.0);
        towerIsland7.setOpacity(0.0);
        towerIsland8.setOpacity(0.0);
        towerIsland9.setOpacity(0.0);
        towerIsland10.setOpacity(0.0);
        towerIsland11.setOpacity(0.0);
        towerIsland12.setOpacity(0.0);
        int motherNatureIsland = getPlayGround().getIslands().indexOf(getPlayGround().getIslandWithMotherNature());
        for(int index = 0; index < getPlayGround().getIslands().size(); index++)
        {
            Island i = getPlayGround().getIslandByIndex(index);
            if(index != motherNatureIsland)
                motherNature.get(index).setOpacity(0.0);
            else
                motherNature.get(index).setOpacity(1.0);
            for(int student=0; student<i.getPlacedStudent().length;student++)
            {
                isl.get(index).get(student).setText(String.valueOf(i.getPlacedStudent()[student]));
                if(i.getPlacedStudent()[student] > 0)
                    isl.get(index).get(student).setOpacity(1.0);
                else
                    isl.get(index).get(student).setOpacity(0.0);
            }
            if(i.getTowerCount()>0)
            {
                towerWhiteIsland.get(index).setOpacity(0.0);
                towerBlackIsland.get(index).setOpacity(0.0);
                towerGreyIsland.get(index).setOpacity(0.0);
                switch (i.getTowerColour())
                {
                    case GRAY:
                        towerGreyIsland.get(index).setOpacity(1.0);
                        //towerGreyIsland.get(index). QUI FARE SET TEXT
                        break;
                    case BLACK:
                        towerBlackIsland.get(index).setOpacity(1.0);
                        //towerGreyIsland.get(index). QUI FARE SET TEXT
                        break;
                    case WHITE:
                        towerWhiteIsland.get(index).setOpacity(1.0);
                        //towerGreyIsland.get(index). QUI FARE SET TEXT
                        break;
                }
                towers.get(index).setOpacity(1.0);
                towers.get(index).setText(String.valueOf(i.getTowerCount()));
            }
        }
    }

    /**
     * Update of stats which include assistant card,
     * professors control, number of tower of each player
     */
    public void updateStats() {
        // Update general info in Stats scene
        for(int index = 0; index < getPlayGround().getPlayersList().size(); index++)
        {
            String nickname = getPlayGround().getPlayersList().get(index).getNickname();
            Card card = getPlayGround().getPlayersList().get(index).getCurrentCard();
            if(card != null)
            {
                currentAssistantCard.get(index).setText(nickname.toUpperCase(Locale.ROOT)+"' assistant card: "+card.getValue());
            }
            else
                currentAssistantCard.get(index).setText("None");
        }

        for(Player p : getPlayGround().getPlayersList())
        {
            if(!p.getNickname().equals(getMyNickname()))
            {
                if(nickname1.getText().equals("None")) {
                    nickname1.setText(p.getNickname());
                }
                else {
                    nickname2.setText(p.getNickname());
                }
            }
            if(p.getPlayerBoard().getTowerColour().equals(TColour.WHITE))
                towerWhite.setText(String.valueOf((p.getPlayerBoard().getTowerYard())));
            if(p.getPlayerBoard().getTowerColour().equals(TColour.BLACK))
                towerBlack.setText(String.valueOf((p.getPlayerBoard().getTowerYard())));
            if(p.getPlayerBoard().getTowerColour().equals(TColour.GRAY))
                towerGray.setText(String.valueOf((p.getPlayerBoard().getTowerYard())));
        }

        //Update assistant cards
        boolean found = false;
        for(ImageView card : assistantCards)
        {
            for(Card c : getMyDeck().getResidualCards())
            {
                if(assistantCards.indexOf(card)+1 == c.getValue())
                    found = true;
            }
            if(!found)
            {
                card.setOpacity(0.5);
                card.setDisable(true);
            }
            found = false;
        }
        //Update professors control
        for(int colour = 0; colour < Colour.colourCount; colour++)
        {
            if(getPlayGround().getProfessorsControl()[colour] != null)
            {
                professorsControl.get(colour).setText(getPlayGround().getProfessorsControl()[colour]);
            }
        }
    }

    /**
     * Update cloud tiles view with all the available students to pick
     */
    public void updateCloudTiles()
    {
        for(int cloud = 0; cloud<getPlayGround().getCloudTiles().length; cloud++)
        {
            paneCloudTiles.get(cloud).opacityProperty().set(1.0);
            cloudButton.get(cloud).opacityProperty().set(1.0);
            if(!getPlayGround().getCloudTiles()[cloud].isUsed())
                for(int student=0; student<Colour.colourCount;student++)
                {
                    cloudTiles.get(cloud).get(student).setText(String.valueOf(getPlayGround().getCloudTiles()[cloud].getStudents()[student]));
                    if(getPlayGround().getCloudTiles()[cloud].getStudents()[student] > 0)
                        cloudTiles.get(cloud).get(student).setOpacity(1.0);
                    else
                        cloudTiles.get(cloud).get(student).setOpacity(0.5);
                }
            else
            {
                paneCloudTiles.get(cloud).opacityProperty().set(0.0);
            }
        }
        if(getPlayGround().getPlayersList().size() == 2)
        {
            paneCloudTiles.get(2).opacityProperty().set(0.0);
            for(int student=0; student<Colour.colourCount;student++)
            {
                cloudTiles.get(2).get(student).setOpacity(0.0);
            }
        }
    }

    /**
     * Call update board general with current client personal board
     */
    public void updateBoard()
    {
        updateBoardGeneral(getMyBoard(),getMyNickname());
    }

    /**
     *  Call find player with first other player
     */
    public void updateBoardPlayerOne()
    {
        findPlayer(1);
    }

    /**
     *  Call find player with second other player
     */
    public void updateBoardPlayerTwo()
    {
       findPlayer(2);
    }

    /**
     *  Call update board general with the player found
     */
    private void findPlayer(int count)
    {
        int index = 0;
        for(Player p : getPlayGround().getPlayersList())
        {
            if(!p.getNickname().equals(getMyNickname()))
            {
                index++;
                if(index == count)
                    updateBoardGeneral(p.getPlayerBoard(),p.getNickname());
            }
        }
    }

    /**
     * Update a generic board: entrance, dining, professor room and tower yard
     */
    public void updateBoardGeneral(Board board,String nickname)
    {
        // entrance room
        for(int student = 0; student < Colour.colourCount; student++)
        {
            entranceRoom.get(student).setText(String.valueOf(board.getEntranceRoom()[student]));
            if(board.getEntranceRoom()[student] == 0)
                entranceRoom.get(student).setOpacity(0.5);
            else
                entranceRoom.get(student).setOpacity(1.0);
        }
        //dining room
        for(int student = 0; student < Colour.colourCount; student++)
        {
            for(int index = 0; index < board.getDiningRoom()[student]; index++)
            {
                diningRoom.get(student).get(index).setOpacity(1.0);
            }
        }
        //professors room
        for(int colour = 0; colour < Colour.colourCount; colour++)
        {
            if(getPlayGround().getProfessorsControl()[colour] != null)
            {
                if(getPlayGround().getProfessorsControl()[colour].equals(nickname))
                    professorsRoom.get(colour).setOpacity(1.0);
                else
                    professorsRoom.get(colour).setOpacity(0.0);
            }
            else
                professorsRoom.get(colour).setOpacity(0.0);
        }
        //tower yard
        int index=-1;
        switch (board.getTowerColour())
        {
            case WHITE:
                index = 0;
                break;
            case GRAY:
                index = 1;
                break;
            case BLACK:
                index = 2;
                break;
            default:
                break;
        }
        towerYard.get(index).setOpacity(1.0);
        towerYard.get(index).setText(String.valueOf(board.getTowerYard()));
    }

    /**
     * Update character card view with current player coins
     */
    public void updateCharacter()
    {
        coins.setText("Remains coins: "+getMyBoard().getCoins());
        drawnCards = new ArrayList<>();
        for (ImageView characterCard : characterCards) {
            characterCard.setOpacity(0.3);
            characterCoin.get(characterCards.indexOf(characterCard)).setText("0");
            characterCoin.get(characterCards.indexOf(characterCard)).setOpacity(0.5);
        }
        for(CharacterCard card : getPlayGround().getDrawnCards())
        {
            characterCards.get(card.getId()-1).setOpacity(1.0);
            characterCoin.get(card.getId()-1).setOpacity(1.0);
            characterCoin.get(card.getId()-1).setText(String.valueOf(card.getPrice()));
            drawnCards.add(characterCards.get(card.getId()-1));
            int index =-1;
            switch (card.getId()-1){
                case 0:
                    index = 0;
                    break;
                case 5:
                    index = 2;
                    break;
                case 8:
                    index = 3;
                    break;
            }
            if(index != -1)
                for(int colour = 0; colour<Colour.colourCount;colour++)
                {
                    elements.get(index).get(colour).setOpacity(1.0);
                    elements.get(index).get(colour).setText(String.valueOf(card.getStudents()[colour]));
                }
            else
            {
                if(card.getId()-1 == 3)
                {
                    elements.get(1).get(0).setText(String.valueOf(card.getBanCardNumber()));
                }
            }
        }
    }

    /**
     * Switch to board view
     */
    public void switchToBoard()
    {
        Platform.runLater(() ->
                new GuiLoadScene("Board").run());
    }
    /**
     * Switch to character card view
     */
    public void switchToCharacter()
    {
        if(getPlayGround().getGameMode() == 1 && !Gui.getGamePhase().equals("Other"))
        {
            Platform.runLater(() ->
                    new GuiLoadScene("CharacterCard").run());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Character Cards not allowed now");
            alert.showAndWait();
        }
    }
    /**
     * Switch to playground view
     */
    public void switchToPlayground()
    {
        Platform.runLater(() -> new GuiLoadScene("Playground").run());
    }
    /**
     * Switch to settings view
     */
    public void switchToSettings()
    {
        Platform.runLater(() -> new GuiLoadScene("Settings").run());

    }
    /**
     * Switch to stats view
     */
    public void switchToStats()
    {
        Platform.runLater(() -> new GuiLoadScene("Stats").run());
    }
    /**
     * Switch to board first other player view
     */
    public void switchToBoardPlayerOne()
    {
        Platform.runLater(() -> new GuiLoadScene("BoardOtherPlayerOne").run());
    }
    /**
     * Switch to board second other player view
     */
    public void switchToBoardPlayerTwo()
    {
        if(!nickname2.getText().equals("None"))
            Platform.runLater(() -> new GuiLoadScene("BoardOtherPlayerTwo").run());
    }
    /**
     * Exit from character card menù
     */
    public void backFromCharacterMenu()
    {
        //GuiMain.getQueue().add(-1);
        Platform.runLater(() -> new GuiLoadScene("Playground").run());
    }
    /**
     * Use the selected assistant card
     */
    private void assistantCardToUse(int card){
        if(Gui.getGamePhase().equals("assistantCard"))
            GuiMain.getQueue().add(card);
        switchToPlayground();
    }

    // list of methods to handle assistant card choose event
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

    /**
     * Move a student from entrance to dining
     */
    public void moveToDining()
    {
        if(Gui.getGamePhase().equals("whereToMove"))
            GuiMain.getQueue().add(0);
        //switchToPlayground();
    }

    /**
     * Select colour of the student to move
     * @param colour colour of the student
     */
    public void moveStudent(int colour)
    {
        if(Gui.getGamePhase().equals("movePhase"))
            GuiMain.getQueue().add(colour);
       // switchToPlayground();
    }
    // list of methods to handle move student event
    public void moveGreen(MouseEvent mouseEvent)
    {
        moveStudent(Colour.GREEN);
    }

    public void moveRed(MouseEvent mouseEvent)
    {
        moveStudent(Colour.RED);
    }

    public void movePink(MouseEvent mouseEvent)
    {
        moveStudent(Colour.PINK);
    }

    public void moveBlue(MouseEvent mouseEvent)
    {
        moveStudent(Colour.BLUE);
    }

    public void moveYellow(MouseEvent mouseEvent)
    {
        moveStudent(Colour.YELLOW);
    }

    /**
     * Allows to selecet an island for example to move mother nature on it,
     * or to move a student on it
     * @param island index of the island chosen
     */
    public void moveToIsland(int island)
    {
        if(Gui.getGamePhase().equals("motherNature"))
        {
            GuiMain.getQueue().add(island);
        }
        if(Gui.getGamePhase().equals("whereToMove"))
        {
            GuiMain.getQueue().add(1);
            GuiMain.getQueue().add(island);
        }
        switchToPlayground();
    }
    // list of methods to handle select island event
    public void moveToIsland1(MouseEvent mouseEvent)
    {
        moveToIsland(1);
    }
    public void moveToIsland2(MouseEvent mouseEvent)
    {
        moveToIsland(2);
    }
    public void moveToIsland3(MouseEvent mouseEvent)
    {
        moveToIsland(3);
    }
    public void moveToIsland4(MouseEvent mouseEvent)
    {
        moveToIsland(4);
    }
    public void moveToIsland5(MouseEvent mouseEvent)
    {
        moveToIsland(5);
    }
    public void moveToIsland6(MouseEvent mouseEvent)
    {
        moveToIsland(6);
    }
    public void moveToIsland7(MouseEvent mouseEvent)
    {
        moveToIsland(7);
    }
    public void moveToIsland8(MouseEvent mouseEvent)
    {
        moveToIsland(8);
    }
    public void moveToIsland9(MouseEvent mouseEvent)
    {
        moveToIsland(9);
    }
    public void moveToIsland10(MouseEvent mouseEvent)
    {
        moveToIsland(10);
    }
    public void moveToIsland11(MouseEvent mouseEvent)
    {
        moveToIsland(11);
    }
    public void moveToIsland12(MouseEvent mouseEvent)
    {
        moveToIsland(12);
    }

    /**
     * Allows to choose a cloud tile to pick
     * @param cloudTile index of the cloud tile to pick
     */
    public void chooseCloudTile(int cloudTile)
    {
        if(Gui.getGamePhase().equals("cloudTiles"))
        {
            GuiMain.getQueue().add(cloudTile);
            Gui.setGamePhase("Other");
            switchToPlayground();
        }
    }
    // list of methods to handle select cloud tile event
    public void cloudTileOne()
    {
        if(paneCloudTiles.get(1).opacityProperty().get() != 0.0)
            chooseCloudTile(1);
    }
    public void cloudTileTwo()
    {
        if(paneCloudTiles.get(2).opacityProperty().get() != 0.0)
            chooseCloudTile(2);
    }
    public void cloudTileThree()
    {
        if(getPlayGround().getPlayersList().size() == 3)
            if(paneCloudTiles.get(3).opacityProperty().get() != 0.0)
                chooseCloudTile(3);
    }

    /**
     * Allows to choose a character card
     * @param character index of the character card chosen
     */
    public void chooseCharacter(int character)
    {
        Gui.setGamePhase("Character");
        GuiMain.getQueue().add(Client.getNotAllowedInt());
        if(getPlayGround().getDrawnCards().get(character).getPrice() <= getMyBoard().getCoins())
            GuiMain.getQueue().add(character+1);
        else
        {
            GuiMain.getQueue().add(-1);
            setNotification("Not enough coins");
            System.out.println("Not enough coins");
            switchToCharacter();
        }
    }
    // list of methods to handle select character card event
    public void characterOne(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character1))
            chooseCharacter(drawnCards.indexOf(character1));
    }
    public void characterTwo(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character2))
            chooseCharacter(drawnCards.indexOf(character2));
    }
    public void characterThree(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character3))
            chooseCharacter(drawnCards.indexOf(character3));
    }
    public void characterFour(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character4))
            chooseCharacter(drawnCards.indexOf(character4));
    }
    public void characterFive(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character5))
            chooseCharacter(drawnCards.indexOf(character5));
    }
    public void characterSix(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character6))
            chooseCharacter(drawnCards.indexOf(character6));
    }
    public void characterSeven(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character7))
            chooseCharacter(drawnCards.indexOf(character7));
    }
    public void characterEight(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character8))
            chooseCharacter(drawnCards.indexOf(character8));
    }
    public void characterNine(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character9))
            chooseCharacter(drawnCards.indexOf(character9));
    }
    public void characterTen(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character10))
            chooseCharacter(drawnCards.indexOf(character10));
    }
    public void characterEleven(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character11))
            chooseCharacter(drawnCards.indexOf(character11));
    }
    public void characterTwelve(MouseEvent mouseEvent)
    {
        if(drawnCards.contains(character12))
            chooseCharacter(drawnCards.indexOf(character12));
    }

    // list of methods to handle select student placed on character card
    public void chooseBlue(MouseEvent mouseEvent)
    {
        if(Gui.getGamePhase().equals("Character"))
            GuiMain.getQueue().add(2);
    }
    public void chooseRed(MouseEvent mouseEvent)
    {
        if(Gui.getGamePhase().equals("Character"))
        {
            GuiMain.getQueue().add(0);
        }

    }
    public void chooseGreen(MouseEvent mouseEvent)
    {
        if(Gui.getGamePhase().equals("Character"))
            GuiMain.getQueue().add(1);
    }
    public void choosePink(MouseEvent mouseEvent)
    {
        if(Gui.getGamePhase().equals("Character"))
            GuiMain.getQueue().add(3);
    }
    public void chooseYellow(MouseEvent mouseEvent)
    {
        if(Gui.getGamePhase().equals("Character"))
            GuiMain.getQueue().add(4);
    }
    // list of methods to handle select yes or no choose by player
    public void chooseYes(MouseEvent mouseEvent)
    {
        if(Gui.getGamePhase().equals("Character"))
            GuiMain.getQueue().add(1);
    }

    public void chooseNo(MouseEvent mouseEvent)
    {
        if(Gui.getGamePhase().equals("Character"))
            GuiMain.getQueue().add(0);
    }

    /**
     * Display the winner or lose view
     * @param winner winner player nickname
     */
    public static void Winner(String winner)
    {
        if(getMyNickname().equalsIgnoreCase(winner))
            Platform.runLater(() -> new GuiLoadScene("Winner").run());
        else
            Platform.runLater(() -> new GuiLoadScene("Loser").run());
    }

    /**
     * Kill the game
     */
    public void close()
    {
        System.exit(0);
    }

}
