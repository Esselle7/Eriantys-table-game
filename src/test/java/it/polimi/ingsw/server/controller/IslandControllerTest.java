package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IslandControllerTest {
    Island island1 = new Island();
    Island island2 = new Island();
    Island island3 = new Island();
    Island island4 = new Island();
    Island island5 = new Island();
    List< Island > islands = new ArrayList<>();

    CloudTile[] cloudTiles = null;
    GameSettings gameSettings = null;

    Player player1 = new Player("Player1");
    Player player2 = new Player("Player2");

    List< Player > playersList = new ArrayList<>();


    int[] entranceRoom1 = {0, 0, 0, 0, 0};
    int[] entranceRoom2 = {0, 0, 0, 0, 0};
    Board player1board = new Board(entranceRoom1, 6, TColour.GRAY);
    Board player2board = new Board(entranceRoom2, 6, TColour.BLACK);

    PlayGround playGround = PlayGround.createPlayground();
    IslandController islandController = new IslandController();

    @BeforeEach
    void setUp() {
        islands.add(island1);
        islands.add(island2);
        islands.add(island3);
        islands.add(island4);
        islands.add(island5);
        playersList.add(player1);
        playersList.add(player2);
        playGround.setPlayersList(playersList);
        playGround.setIslands(islands);
        player1.setPlayerBoard(player1board);
        player2.setPlayerBoard(player2board);
        islandController.setPlayGround(playGround);
    }

    @Test
    void testcheckInfluence(){
        playGround.setProfessorControlByColour(0, player1.getNickname());
        playGround.setProfessorControlByColour(1, player2.getNickname());
        playGround.setIslandWithMotherNature(island1);
        island1.setPlacedStudent(0);
        island1.setPlacedStudent(0);
        island1.setPlacedStudent(1);
        assertEquals(player1, islandController.checkInfluence());
    }

    @Test
    void testislandUnification_1(){
        island1.setTowerColour(TColour.GRAY);
        island2.setTowerColour(TColour.WHITE);
        island3.setTowerColour(TColour.WHITE);
        island4.setTowerColour(TColour.WHITE);
        island5.setTowerColour(TColour.GRAY);
        List <Island> nearby_islands = new ArrayList<>();
        nearby_islands.add(island2);
        nearby_islands.add(island4);
        island3.setNearbyIslands(nearby_islands);
        islandController.islandUnification(island3);
        assertEquals(3, playGround.getIslands().size());
        //Island2, island3 and island4 unify under the same island, the third one in playGround.getIslands()
        assertEquals(TColour.WHITE, playGround.getIslands().get(1).getTowerColour());
        assertEquals(island1, playGround.getIslands().get(1).getNearbyIslands().get(0));
        assertEquals(island5, playGround.getIslands().get(1).getNearbyIslands().get(1));
    }

    @Test
    void testislandUnification_2(){
        island1.setTowerColour(TColour.GRAY);
        island2.setTowerColour(TColour.WHITE);
        island3.setTowerColour(TColour.WHITE);
        island4.setTowerColour(TColour.WHITE);
        island5.setTowerColour(TColour.GRAY);
        List <Island> nearby_islands = new ArrayList<>();
        nearby_islands.add(island5);
        nearby_islands.add(island2);
        island1.setNearbyIslands(nearby_islands);
        islandController.islandUnification(island1);
        assertEquals(4, playGround.getIslands().size());
        //Island1 and Island5 unify under the same island which is located in Island1's former position: 0
        assertEquals(TColour.GRAY, playGround.getIslands().get(0).getTowerColour());
        assertEquals(island4, playGround.getIslands().get(0).getNearbyIslands().get(0));
        assertEquals(island2, playGround.getIslands().get(0).getNearbyIslands().get(1));
    }

    @Test
    void testislandUnification_3(){
        island1.setTowerColour(TColour.GRAY);
        island2.setTowerColour(TColour.WHITE);
        island3.setTowerColour(TColour.WHITE);
        island4.setTowerColour(TColour.GRAY);
        island5.setTowerColour(TColour.GRAY);
        List <Island> nearby_islands = new ArrayList<>();
        nearby_islands.add(island1);
        nearby_islands.add(island4);
        island5.setNearbyIslands(nearby_islands);
        islandController.islandUnification(island5);
        assertEquals(3, playGround.getIslands().size());
        //Island4, Island1 and Island5 unify and the unified island is placed in Island5's former position
        assertEquals(TColour.GRAY, playGround.getIslands().get(2).getTowerColour());
        assertEquals(island3, playGround.getIslands().get(2).getNearbyIslands().get(0));
        assertEquals(island2, playGround.getIslands().get(2).getNearbyIslands().get(1));
    }
}