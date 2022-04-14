package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class GameControllerTest {

    PlayGround currentGame;
    List<Island> islands;
    List<Player> players;
    GameSettings gs;
    GameController GC;
    CloudTile[] ct;

    @BeforeEach
    void setup(){
        int numberOfPlayers = 2;
        GC = new GameController();
        GC.setCurrentGame(PlayGround.createPlayground());
        String[] players = new String[numberOfPlayers];
        players[0] = "Francesco";
        players[1] = "Marco";
        GC.setUpGameSettings(numberOfPlayers);
        GC.setUpPlayers(numberOfPlayers,players);
        GC.setUpIslands();
        GC.setUpBoard();
        GC.setUpCloudTile();
        GC.setUpDecks(numberOfPlayers);
        GC.setTurnHandler(new TurnHandler());
        GC.getTurnHandler().setCurrentPlayer(GC.getCurrentGame().getPlayerByNickname("Francesco"));

    }

    @Test
    void moveStudentsEntranceToIslandTest() {
        int previousValue = GC.getCurrentGame().getIslandByIndex(1).numberOfStudentByColour(0);
        GC.moveStudentsEntranceToIsland(0,1);
        assertEquals(GC.getCurrentGame().getIslandByIndex(1).numberOfStudentByColour(0), 1+previousValue);
    }


    @Test
    void moveStudentEntranceToDiningTest() {
        try{
            GC.moveStudentEntranceToDining(0);
        }
        catch (FullDiningRoomTable e)
        {
            fail();
        }

        assertEquals(GC.getCurrentGame().getPlayerByNickname(GC.getTurnHandler().getCurrentPlayer().getNickname()).getPlayerBoard().getDiningRoom()[0],1);
        assertEquals(GC.getCurrentGame().getPlayerByNickname(GC.getTurnHandler().getCurrentPlayer().getNickname()).getPlayerBoard().getDiningRoom()[0],1);
    }
/*
    @Test
    void setInfluenceToIslandTest() {
        GC.setInfluenceToIsland();
        assertEquals(GC.getTurnHandler().getCurrentPlayer().getPlayerBoard().getTowerYard(),GC.getCurrentSettings().getTowerYard()-1);
        if(GC.getCurrentGame().getIslandWithMotherNature().getTowerColour().equals(GC.getTurnHandler().getCurrentPlayer().getPlayerBoard().getTowerColour()))
        {
            assertTrue(true);
        }
        assertEquals(GC.getCurrentGame().getIslandWithMotherNature().getTowerCount(),1);

    }

    @Test
    void changeInfluenceToIslandTest() {
        Player oldInfluenced = null;
        for (Player p: GC.getCurrentGame().getPlayersList()) {
            if(p.getPlayerBoard().getTowerColour() == (GC.getCurrentGame().getIslandWithMotherNature().getTowerColour()))
            {
                oldInfluenced = p;
            }
        }
        GC.changeInfluenceToIsland();
        assert oldInfluenced != null;
        assertEquals(oldInfluenced.getPlayerBoard().getTowerYard(),GC.getCurrentSettings().getTowerYard());
        assertEquals(GC.getCurrentGame().getPlayerByNickname("Andre").getPlayerBoard().getTowerYard(),4);
        if(GC.getCurrentGame().getIslandWithMotherNature().getTowerColour().equals(GC.getTurnHandler().getCurrentPlayer().getPlayerBoard().getTowerColour()))
        {
            assertTrue(true);
        }

    }*/

    @Test
    void takeStudentsFromCloudTileTest() {
        int[] result = new int[Colour.colourCount];
        int[] studentsEntranceRoom = GC.getTurnHandler().getCurrentPlayerBoard().getEntranceRoom();
        int chosenCloudTile = 0;
        for(int i = 0; i < Colour.colourCount; i++)
        {
            result[i] = GC.getCurrentGame().getCloudTiles()[chosenCloudTile].getStudents()[i] + studentsEntranceRoom[i];
        }
        GC.getCurrentGame().getCloudTiles()[chosenCloudTile].setUsed();
        try{
            GC.takeStudentsFromCloudTile(chosenCloudTile);

        }catch(CloudTileAlreadyTakenException e)
        {
            fail();
        }

        assertArrayEquals(GC.getTurnHandler().getCurrentPlayer().getPlayerBoard().getEntranceRoom(),result);

    }
/*
    @Test
    void useAssistantCardTest() {
        try{
            GC.useAssistantCard(1);
        }
        catch (UnableToUseCardException e)
        {
            fail();
        }
        assertEquals(GC.getTurnHandler().getCurrentPlayer().getAssistantCards().getResidualCards().get(0).getValue(),2);
        assertEquals(GC.getTurnHandler().getCurrentPlayer().getCurrentCard().getValue(),1);

    }

    @Test
    void checkProfessorsControlTest() {
        GC.checkProfessorsControl();
        for (String p : GC.getCurrentGame().getProfessorsControl()) {
            assertEquals(p, "Simo");
        }
    }

    @Test
    void checkWinTest() {
        GC.checkProfessorsControl();
        try
        {
            assertEquals(GC.checkWin(),"Simo");
        }
        catch(noWinnerException e)
        {
            assertTrue(true);
        }

    }

*/
}