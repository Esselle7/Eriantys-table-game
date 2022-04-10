package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    PlayGround currentGame;
    List<Island> islands;
    List<Player> players;
    GameSettings gs;
    GameController GC;
    CloudTile[] ct;

    @BeforeEach
    void setup(){
        gs = new TwoGameSettings();
        GC = new GameController(gs);
        GC.setTurnHandler(new TurnHandler());
        ct = new CloudTile[3];
        ct[0] = new CloudTile(new int[]{1,1,1,1,1});
        islands = new ArrayList<>();
        players = new ArrayList<>();
        islands.add(new Island());
        islands.add(new Island());
        players.add(new Player("Simo"));
        players.add(new Player("Andre"));
        currentGame = new PlayGround(players,islands,ct, gs);
        List<Integer> steps = new ArrayList<>();
        steps.add(1);
        steps.add(19);
        steps.add(6);
        steps.add(4);
        steps.add(3);
        Deck AndreDeck = new Deck(Wizard.WIZARD1, steps);
        currentGame.getPlayerByNickname("Andre").setAssistantCards(AndreDeck);
        currentGame.setIslandWithMotherNature(islands.get(0));
        currentGame.getIslandWithMotherNature().setTowerColour(TColour.BLACK);
        GC.getTurnHandler().setCurrentPlayer(currentGame.getPlayersList().get(1));
        currentGame.getPlayerByNickname("Simo").setPlayerBoard(new Board(new int[]{2,1,1,0,0},5,TColour.BLACK));
        currentGame.getPlayerByNickname("Andre").setPlayerBoard(new Board(new int[]{2,1,1,0,0},5,TColour.WHITE));

        GC.setCurrentGame(currentGame);


    }

    @Test
    void moveStudentsEntranceToIslandTest() {
        GC.moveStudentsEntranceToIsland(0,1);
        assertEquals(currentGame.getIslandByIndex(1).numberOfStudentByColour(0), 1);
    }


    @Test
    void moveStudentEntranceToDiningTest() {
        GC.moveStudentEntranceToDining(0);
        assertEquals(currentGame.getPlayerByNickname(GC.getTurnHandler().getCurrentPlayer().getNickname()).getPlayerBoard().getEntranceRoom()[0],1);
        assertEquals(currentGame.getPlayerByNickname(GC.getTurnHandler().getCurrentPlayer().getNickname()).getPlayerBoard().getDiningRoom()[0],1);
    }

    @Test
    void setInfluenceToIslandTest() {
        GC.setInfluenceToIsland();
        assertEquals(GC.getTurnHandler().getCurrentPlayer().getPlayerBoard().getTowerYard(),4);
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
        assertEquals(oldInfluenced.getPlayerBoard().getTowerYard(),6);
        assertEquals(GC.getCurrentGame().getPlayerByNickname("Andre").getPlayerBoard().getTowerYard(),4);
        if(GC.getCurrentGame().getIslandWithMotherNature().getTowerColour().equals(GC.getTurnHandler().getCurrentPlayer().getPlayerBoard().getTowerColour()))
        {
            assertTrue(true);
        }

    }

    @Test
    void takeStudentsFromCloudTileTest() {
        GC.takeStudentsFromCloudTile(0);
        assertArrayEquals(GC.getTurnHandler().getCurrentPlayer().getPlayerBoard().getEntranceRoom(),new int[]{3,2,2,1,1});

    }

    @Test
    void useAssistantCard() {
        GC.useAssistantCard(1);
        assertEquals(GC.getTurnHandler().getCurrentPlayer().getAssistantCards().getResidualCards().get(0).getValue(),2);
        assertEquals(GC.getTurnHandler().getCurrentPlayer().getCurrentCard().getValue(),1);

    }
}