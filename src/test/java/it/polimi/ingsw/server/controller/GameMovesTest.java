package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GameMovesTest {
    GameMoves GC;

    @BeforeEach
    void setup(){
        int numberOfPlayers = 2;
        GC = new GameMoves();
        GC.setCurrentGame(PlayGround.createPlayground());
        String[] players = new String[numberOfPlayers];
        players[0] = "Francesco";
        players[1] = "Marco";
        GC.setUpGame(numberOfPlayers,players);
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
    @Test
    void moveStudentEntranceToDiningExceptionTest() {
        for(int i = 0; i < GC.getCurrentSettings().getDiningRoomLenght(); i++)
        {
            GC.getTurnHandler().getCurrentPlayerBoard().increaseNumberOfStudent(0);
        }

        try{
            GC.moveStudentEntranceToDining(0);
        }
        catch (FullDiningRoomTable e)
        {
            assertTrue(true);
        }
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

    @Test
    void takeStudentsFromCloudTileExceptionTest() {
        int chosenCloudTile = 0;
        GC.getCurrentGame().getCloudTiles()[chosenCloudTile].getStudents();
        try{
            GC.takeStudentsFromCloudTile(chosenCloudTile);

        }catch(CloudTileAlreadyTakenException e)
        {
            assertTrue(true);
        }
    }

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
    void useAssistantCardExceptionTest() {
        GC.getTurnHandler().setCurrentPlayer(GC.getCurrentGame().getPlayerByNickname("Marco"));
        GC.getCurrentGame().getPlayersList().get(0).setCurrentCard(new Card(1,1));
        try{
            GC.useAssistantCard(1);
        }
        catch (UnableToUseCardException e)
        {
            assertTrue(true);
        }
    }

    @Test
    void checkProfessorsControlTest() {
        GC.getCurrentGame().getPlayersList().get(0).getPlayerBoard().increaseNumberOfStudent(0);
        GC.getCurrentGame().getPlayersList().get(1).getPlayerBoard().increaseNumberOfStudent(1);
        GC.getCurrentGame().getPlayersList().get(1).getPlayerBoard().increaseNumberOfStudent(0);
        GC.getCurrentGame().getPlayersList().get(1).getPlayerBoard().increaseNumberOfStudent(0);
        GC.checkProfessorsControl();
        GC.getCurrentGame().getPlayersList().get(0).getPlayerBoard().increaseNumberOfStudent(0);
        GC.checkProfessorsControl();
        assertEquals(GC.getCurrentGame().getProfessorsControl()[0],"Marco");
        assertEquals(GC.getCurrentGame().getProfessorsControl()[1],"Marco");
        assertNull(GC.getCurrentGame().getProfessorsControl()[2]);
        assertNull(GC.getCurrentGame().getProfessorsControl()[3]);
        assertNull(GC.getCurrentGame().getProfessorsControl()[4]);


    }

    @Test
    void checkWinEmptyTowerYardTest() {
        GC.getCurrentGame().getPlayersList().get(0).getPlayerBoard().setTowerYard(0);
        try
        {
            assertEquals(GC.checkWin(), "Francesco");
        }
        catch(noWinnerException e)
        {
            fail();
        }
    }

    @Test
    void checkWinByTowerYardTest() {
        GC.getCurrentGame().getPlayersList().get(0).getPlayerBoard().increaseNumberOfStudent(0);
        GC.getCurrentGame().getPlayersList().get(1).getPlayerBoard().increaseNumberOfStudent(1);
        GC.getCurrentGame().getPlayersList().get(1).getPlayerBoard().increaseNumberOfStudent(0);
        GC.getCurrentGame().getPlayersList().get(1).getPlayerBoard().increaseNumberOfStudent(0);
        GC.checkProfessorsControl();
        GC.setTotalStudentPaws(0);
        try
        {
            assertEquals(GC.checkWin(), "Marco");
        }
        catch(noWinnerException e)
        {
            fail();
        }
    }

    @Test
    void checkWinByProfessorsControlTest() {
        GC.getCurrentGame().getPlayersList().get(0).getPlayerBoard().setTowerYard(5);
        GC.setTotalStudentPaws(0);
        try
        {
            assertEquals(GC.checkWin(), "Francesco");
        }
        catch(noWinnerException e)
        {
            fail();
        }
    }

    @Test
    void checkWinExceptionTest() {
        GC.checkProfessorsControl();
        try
        {
            GC.checkWin();
        }
        catch(noWinnerException e)
        {
            assertTrue(true);
        }
    }


    @Test
    void moveMotherNatureTest() {
        GC.getCurrentGame().setIslandWithMotherNature(GC.getCurrentGame().getIslandByIndex(11));
        GC.getTurnHandler().getCurrentPlayer().useCard(4);
        try{
            GC.moveMotherNature(2);
            assertEquals(GC.getCurrentGame().getIslandWithMotherNature(), GC.getCurrentGame().getIslandByIndex(1));
        }catch (ExceededMotherNatureStepsException e)
        {
            fail();
        }
    }

    @Test
    void moveMotherNatureExceptionTest() {
        GC.getTurnHandler().getCurrentPlayer().useCard(4);
        try{
            GC.moveMotherNature(5);
        }
        catch (ExceededMotherNatureStepsException e)
        {
            assertTrue(true);
        }
    }

/*
    @Test
    void setInfluenceToIsland() {
        GC.moveStudentsEntranceToIsland(0,0);
        GC.moveStudentsEntranceToIsland(0,0);
        GC.checkProfessorsControl();
        GC.setInfluenceToIsland();
        assertEquals(GC.getCurrentGame().getIslands().get(0).getTowerColour(),GC.getTurnHandler().getCurrentPlayerBoard().getTowerColour());
        assertEquals(GC.getTurnHandler().getCurrentPlayerBoard().getTowerYard(),GC.getCurrentSettings().getTowerYard()-1);


    }*/
}