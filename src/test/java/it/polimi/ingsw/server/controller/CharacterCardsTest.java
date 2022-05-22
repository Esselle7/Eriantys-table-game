package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.VirtualClient.TestingVirtualViewConnection;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.controller.expert.CharacterCard;
import it.polimi.ingsw.server.controller.expert.EqualProfessorCard;
import it.polimi.ingsw.server.controller.expert.ExtraStepsCard;
import it.polimi.ingsw.server.controller.expert.TwoExtraInfluenceCard;
import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterCardsTest {
    CharacterCard card = null;
    TurnHandler turnHandler = null;
    GameMoves GC;
    Player player1, player2, player3;
    PlayGround playGround;
    Island island1, island2;

    @BeforeEach
    void setup(){
        int numberOfPlayers = 3;
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
        try{
            turnHandler = new TurnHandler(playersList, 2);
        } catch(IOException e){
            e.printStackTrace();
        }
        GC = turnHandler.getGameMoves();
        GC.setCurrentGame(new PlayGround());
        GC.setUpGame(numberOfPlayers, playersList);
        GC.setCurrentPlayer(GC.getPlayerByNickname("Player1"));
        player1 = GC.getCurrentGame().getPlayersList().get(0);
        player2 = GC.getCurrentGame().getPlayersList().get(1);
        player3 = GC.getCurrentGame().getPlayersList().get(2);
        playGround = GC.getCurrentGame();
        GC.setCurrentPlayer(player1);
        player1.getPlayerBoard().setCoins(5);
    }

    @Test
    void EqualProfessorCardTest(){
        card = new EqualProfessorCard();
        try{
            card.useCardImpl(turnHandler);
        } catch (Exception e){
            e.printStackTrace();
        } catch (Throwable e){
            e.printStackTrace();
        }
        player2.getPlayerBoard().increaseNumberOfStudent(0);
        player2.getPlayerBoard().increaseNumberOfStudent(0);
        GC.checkProfessorsControl();
        assertEquals(player2, GC.getPlayerByNickname(playGround.getProfessorControlByColour(0)));
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        GC.checkProfessorsControl();
        assertEquals(2, turnHandler.getGameMoves().getCurrentGame().getGameMode());
        assertEquals(player1, GC.getPlayerByNickname(playGround.getProfessorControlByColour(0)));
        assertEquals(player1, GC.getPriorityPlayer());
        assertEquals(3, player1.getPlayerBoard().getCoins());
    }

    @Test
    void ExtraStepsCardTest(){
        card = new ExtraStepsCard();
        try{
            card.useCardImpl(turnHandler);
        } catch (Exception e){
            e.printStackTrace();
        } catch (Throwable e){
            e.printStackTrace();
        }
        assertEquals(2, player1.getMotherNatureSteps());
        player1.useCard(5);
        assertEquals(2 + player1.getCurrentCard().getMotherNatureSteps(), player1.getMotherNatureSteps());
        assertEquals(4, player1.getPlayerBoard().getCoins());
    }

    @Test
    void TwoExtraInfluenceCardTest(){
        island1 = playGround.getIslandByIndex(1);
        card = new TwoExtraInfluenceCard();
        try{
            card.useCardImpl(turnHandler);
        } catch (Exception e){
            e.printStackTrace();
        } catch (Throwable e){
            e.printStackTrace();
        }
        assertEquals(2, player1.getExtraInfluence());
        island1.increasePlacedStudent(1);
        island1.increasePlacedStudent(1);
        island1.increasePlacedStudent(1);
        player1.getPlayerBoard().increaseNumberOfStudent(0);
        player2.getPlayerBoard().increaseNumberOfStudent(1);
        GC.checkProfessorsControl();
        assertEquals(player2, GC.getIslandController().checkInfluence(island1,GC.getCurrentGame()));
        island1.increasePlacedStudent(0);
        island1.increasePlacedStudent(0);
        assertEquals(player1, GC.getIslandController().checkInfluence(island1,GC.getCurrentGame()));
        assertEquals(3, player1.getPlayerBoard().getCoins());
    }
}
