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
        gs = new TwoGameSettings();
        GC = new GameController(gs);
        GC.setTurnHandler(new TurnHandler());
        ct = new CloudTile[3];
        ct[0] = new CloudTile(new int[]{1,1,1,1,1});
        islands = new ArrayList<>();
        players = new ArrayList<>();
        islands.add(new Island());
        islands.add(new Island());
        islands.add(new Island());
        islands.add(new Island());
        islands.add(new Island());

        players.add(new Player("Simo"));
        players.add(new Player("Andre"));
        currentGame = new PlayGround(players,islands,ct, gs);
        Deck AndreDeck = new Deck(Wizard.WIZARD1);
        Deck SimoDeck = new Deck(Wizard.WIZARD2);
        currentGame.getPlayerByNickname("Andre").setAssistantCards(AndreDeck);
        currentGame.getPlayerByNickname("Simo").setAssistantCards(SimoDeck);
        currentGame.setIslandWithMotherNature(islands.get(0));
        currentGame.getIslandWithMotherNature().setTowerColour(TColour.BLACK);
        GC.getTurnHandler().setCurrentPlayer(currentGame.getPlayersList().get(1));
        currentGame.getPlayerByNickname("Simo").setPlayerBoard(new Board(new int[]{2,1,1,0,0},5,TColour.BLACK));
        currentGame.getPlayerByNickname("Andre").setPlayerBoard(new Board(new int[]{2,1,1,0,0},5,TColour.WHITE));

        for(int a = 0; a<Colour.colourCount;a++)
        {
            currentGame.getPlayerByNickname("Simo").getPlayerBoard().increaseNumberOfStudent(a);
            currentGame.getPlayerByNickname("Simo").getPlayerBoard().increaseNumberOfStudent(a);
            currentGame.getPlayerByNickname("Andre").getPlayerBoard().increaseNumberOfStudent(a);
            currentGame.setProfessorControlByColour(a,"Andre");


        }
        currentGame.getPlayersList().get(0).getPlayerBoard().setTowerYard(4);
        currentGame.getPlayersList().get(1).getPlayerBoard().setTowerYard(4);

        currentGame.setProfessorControlByColour(0,"Simo");
       // for(int a = 1; a<11;a++)
          //  currentGame.getPlayersList().get(1).useCard(a);
        GC.setCurrentGame(currentGame);




    }

    @Test
    void moveStudentsEntranceToIslandTest() {
        GC.moveStudentsEntranceToIsland(0,1);
        assertEquals(currentGame.getIslandByIndex(1).numberOfStudentByColour(0), 1);
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
        try{
            GC.takeStudentsFromCloudTile(0);

        }catch(CloudTileAlreadyTakenException e)
        {
            fail();
        }

        assertArrayEquals(GC.getTurnHandler().getCurrentPlayer().getPlayerBoard().getEntranceRoom(),new int[]{3,2,2,1,1});

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


}