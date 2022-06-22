package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.VirtualClient.TestingVirtualViewConnection;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMovesTest {
    GameMoves GC;
    Player player1, player2, player3;
    PlayGround playGround;
    Island island1, island2, island6;

    @BeforeEach
    void setup(){
        int numberOfPlayers = 3;
        GC = new GameMoves();
        GC.setCurrentGame(new PlayGround());
        List<VirtualViewConnection> playersList = new ArrayList<>();
        try {
            playersList.add(new TestingVirtualViewConnection());
            playersList.add(new TestingVirtualViewConnection());
            playersList.add(new TestingVirtualViewConnection());
        } catch(IOException e){
            e.printStackTrace();
        }
        playersList.get(0).setNickname("Player1");
        playersList.get(1).setNickname("Player2");
        playersList.get(2).setNickname("Player3");
        GC.setUpGame(numberOfPlayers, playersList);
        GC.setCurrentPlayer(GC.getPlayerByNickname("Player1"));
        player1 = GC.getCurrentGame().getPlayersList().get(0);
        player2 = GC.getCurrentGame().getPlayersList().get(1);
        player3 = GC.getCurrentGame().getPlayersList().get(2);
        playGround = GC.getCurrentGame();
        island1 = playGround.getIslandByIndex(1);
        island2 = playGround.getIslandByIndex(2);
    }

    @Test
    void moveStudentsEntranceToIslandTest() {
        int[] entranceRoom = {1,0,0,0,0};
        GC.setCurrentPlayer(player1);
        GC.getCurrentPlayer().getPlayerBoard().setEntranceRoom(entranceRoom);
        int previousValue = GC.getCurrentGame().getIslandByIndex(1).numberOfStudentByColour(0);
        try {
            GC.moveStudentsEntranceToIsland(0, 1);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(GC.getCurrentGame().getIslandByIndex(1).numberOfStudentByColour(0), 1+previousValue);
    }

    @Test
    void moveStudentsEntranceToIslandTestNoStudentForColourExceptionTest() {
        int[] entranceRoom = {0,0,0,0,0};
        GC.getCurrentPlayer().getPlayerBoard().setEntranceRoom(entranceRoom);
        try {
            GC.moveStudentsEntranceToIsland(0, 1);
            fail();
        } catch (Exception e){
            assertTrue(true);
        }
    }


    @Test
    void moveStudentEntranceToDiningTest() {
        int[] entranceRoom = {1,0,0,0,0};
        GC.getCurrentPlayer().getPlayerBoard().setEntranceRoom(entranceRoom);
        try{
            GC.moveStudentEntranceToDining(0);
        }
        catch (Exception e)
        {
            fail();
        }

        assertEquals(GC.getCurrentGame().getPlayerByNickname(GC.getCurrentPlayer().getNickname()).getPlayerBoard().getDiningRoom()[0],1);
        assertEquals(GC.getCurrentGame().getPlayerByNickname(GC.getCurrentPlayer().getNickname()).getPlayerBoard().getDiningRoom()[0],1);
    }
    @Test
    void moveStudentEntranceToDiningFullDiningRoomTableExceptionTest() {
        int[] entranceRoom = {3,1,1,2,2};
        GC.getCurrentPlayerBoard().setEntranceRoom(entranceRoom);
        for(int i = 0; i < GC.getCurrentSettings().getDiningRoomLength(); i++)
        {
            GC.getCurrentPlayerBoard().increaseNumberOfStudent(0);
        }

        try{
            GC.moveStudentEntranceToDining(0);
        }
        catch (FullDiningRoomTable e)
        {
            assertTrue(true);
        }
        catch (NoStudentForColour e)
        {
            fail();
        }
    }

    @Test
    void moveStudentEntranceToDiningNoStudentForColourExceptionTest() {
        int[] entranceRoom = {5,0,1,2,2};
        GC.getCurrentPlayerBoard().setEntranceRoom(entranceRoom);
        try{
            GC.moveStudentEntranceToDining(1);
        }
        catch (FullDiningRoomTable e)
        {
            fail();
        }
        catch (NoStudentForColour e)
        {
            assertTrue(true);
        }
    }
    @Test
    void setInfluenceToIslandTest() {
        island1.increasePlacedStudent(0);
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        player1.getPlayerBoard().setTowerYard(6);
        GC.checkProfessorsControl();
        try{
            GC.setInfluenceToIsland(island1);
        } catch (EmptyTowerYard e){
            e.printStackTrace();
        }
        assertEquals(player1.getPlayerBoard().getTowerColour(), island1.getTowerColour());
        assertEquals(5, player1.getPlayerBoard().getTowerYard());
    }

    @Test
    void changeInfluenceToIslandTest() {
        island6 = playGround.getIslandByIndex(6);
        island6.setTowerCount(2);
        island6.setTowerColour(player1.getPlayerBoard().getTowerColour());
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        player1.getPlayerBoard().setTowerYard(4);
        island6.increasePlacedStudent(0);
        island6.increasePlacedStudent(0);
        player2.getPlayerBoard().setTowerYard(6);
        player2.getPlayerBoard().increaseNumberOfStudent(1);
        island6.increasePlacedStudent(1);
        island6.increasePlacedStudent(1);
        island6.increasePlacedStudent(1);
        island6.increasePlacedStudent(1);
        island6.increasePlacedStudent(1);
        GC.checkProfessorsControl();
        try{
            GC.changeInfluenceToIsland(island6);
        } catch(EmptyTowerYard e){
            fail();
        }
        assertEquals(GC.getIslandController().checkInfluence(island6,GC.getCurrentGame()), player2);
        assertEquals(player2.getPlayerBoard().getTowerColour(), island6.getTowerColour());
        assertEquals(4, player2.getPlayerBoard().getTowerYard());
        assertEquals(6, player1.getPlayerBoard().getTowerYard());
    }

    @Test
    void takeStudentsFromCloudTileTest() {
        int[] entranceRoom = {5,0,0,0,0};
        GC.getCurrentPlayer().getPlayerBoard().setEntranceRoom(entranceRoom);
        int chosenCloudTile = 1;
        CloudTile selectedCloudTile = GC.getCurrentGame().getCloudTiles()[chosenCloudTile - 1];
        int[] cloudTileStudentsToSet = {2,0,1,0,1};
        selectedCloudTile.setStudents(cloudTileStudentsToSet);
        try{
            GC.takeStudentsFromCloudTile(chosenCloudTile);
        }catch(CloudTileAlreadyTakenException e)
        {
            fail();
        }
        int[] expectedResult = {7,0,1,0,1};
        assertArrayEquals(expectedResult, GC.getCurrentPlayer().getPlayerBoard().getEntranceRoom());
    }

    @Test
    void takeStudentsFromCloudTileExceptionTest() {
        int chosenCloudTile = 1;
        CloudTile selectedCloudTile = GC.getCurrentGame().getCloudTiles()[chosenCloudTile - 1];
        selectedCloudTile.getStudents();
        try{
            GC.takeStudentsFromCloudTile(chosenCloudTile);
            fail();
        } catch(CloudTileAlreadyTakenException e)
        {
            assertTrue(true);
        }
    }

    @Test
    void useAssistantCardTest() {
        GC.setCurrentPlayer(player1);
        try{
            GC.useAssistantCard(1);
        }
        catch (Exception e)
        {
            fail();
        }
        assertEquals(GC.getCurrentPlayer().getAssistantCards().getResidualCards().get(0).getValue(),2);
        assertEquals(GC.getCurrentPlayer().getCurrentCard().getValue(),1);

    }

    @Test
    void useAssistantCardUnableToUseCardExceptionTest() {
        GC.setCurrentPlayer(player2);
        player1.setCurrentCard(new Card(1,1));
        try{
            GC.useAssistantCard(1);
            fail();
        }
        catch (UnableToUseCardException e)
        {
            assertTrue(true);
        }
        catch (CardNotFoundException e){
            fail();
        }
    }

    @Test
    void useAssistantCardCardNotFoundExceptionTest() {
        useAssistantCardTest();
        GC.setCurrentPlayer(player1);
        try{
            GC.useAssistantCard(1);
            fail();
        }
        catch (UnableToUseCardException e)
        {
            fail();
        }
        catch (CardNotFoundException e){
            assertTrue(true);
        }
    }

    @Test
    void checkProfessorsControlTest() {
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        player1.getPlayerBoard().increaseNumberOfStudent(1);
        player2.getPlayerBoard().increaseNumberOfStudent(0);
        player2.getPlayerBoard().increaseNumberOfStudent(0);
        GC.checkProfessorsControl();
        assertEquals(playGround.getProfessorsControl()[1],player1.getNickname());
        assertEquals(playGround.getProfessorsControl()[0],player2.getNickname());
        player2.getPlayerBoard().increaseNumberOfStudent(1);
        player2.getPlayerBoard().increaseNumberOfStudent(1);
        GC.checkProfessorsControl();
        assertEquals(playGround.getProfessorsControl()[0],player2.getNickname());
        assertEquals(playGround.getProfessorsControl()[1],player2.getNickname());
        assertNull(playGround.getProfessorsControl()[2]);
        assertNull(playGround.getProfessorsControl()[3]);
        assertNull(playGround.getProfessorsControl()[4]);
    }

    @Test
    void checkProfessorsControlPriorityPlayerTest() {
        player2.getPlayerBoard().increaseNumberOfStudent(0);
        GC.checkProfessorsControl();
        assertEquals(player2.getNickname(), playGround.getProfessorsControl()[0]);
        GC.setCurrentPlayer(player1);
        GC.setPriorityPlayer(player1);
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        GC.checkProfessorsControl();
        assertEquals(player1.getNickname(), playGround.getProfessorsControl()[0]);
    }

    @Test
    void findWinnerTower1Test() {
        player1.getPlayerBoard().setTowerYard(4);
        player2.getPlayerBoard().setTowerYard(2);
        player3.getPlayerBoard().setTowerYard(3);
        try{
            assertEquals(GC.findWinnerTower(), player2);
        }
        catch(NoWinnerException e){
            fail();
        }
    }

    @Test
    void findWinnerTower2Test() {
        player1.getPlayerBoard().setTowerYard(2);
        player2.getPlayerBoard().setTowerYard(2);
        player3.getPlayerBoard().setTowerYard(2);
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        player1.getPlayerBoard().increaseNumberOfStudent(1);
        player1.getPlayerBoard().increaseNumberOfStudent(2);
        player2.getPlayerBoard().increaseNumberOfStudent(3);
        player3.getPlayerBoard().increaseNumberOfStudent(4);
        try{
            assertEquals(GC.findWinnerTower(), player1);
        }
        catch(NoWinnerException e){
            fail();
        }
    }

    @Test
    void findWinnerTowerExceptionTest() {
        player1.getPlayerBoard().setTowerYard(2);
        player2.getPlayerBoard().setTowerYard(2);
        player3.getPlayerBoard().setTowerYard(2);
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        player1.getPlayerBoard().increaseNumberOfStudent(1);
        player2.getPlayerBoard().increaseNumberOfStudent(2);
        player2.getPlayerBoard().increaseNumberOfStudent(3);
        player3.getPlayerBoard().increaseNumberOfStudent(4);
        try{
            assertEquals(GC.findWinnerTower(), player1);
            fail();
        }
        catch(NoWinnerException e){
            assertTrue(true);
        }
    }

    @Test
    void checkForEmptyTowerYardTest() {
        player1.getPlayerBoard().setTowerYard(1);
        player2.getPlayerBoard().setTowerYard(2);
        player3.getPlayerBoard().setTowerYard(3);
        try{
            player1.getPlayerBoard().decreaseTowerYard();
            fail();
        } catch (EmptyTowerYard e){
            assertEquals(player1.getPlayerBoard().getTowerYard(), 0);
            assertEquals(player1.getNickname(), GC.checkForEmptyTowerYard().getNickname());
        }
    }

    @Test
    void moveMotherNatureTest() {
        GC.getCurrentGame().setIslandWithMotherNature(GC.getCurrentGame().getIslandByIndex(11));
        GC.getCurrentPlayer().useCard(4);
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
        GC.getCurrentPlayer().useCard(4);
        try{
            GC.moveMotherNature(5);
        }
        catch (ExceededMotherNatureStepsException e)
        {
            assertTrue(true);
        }
    }
}