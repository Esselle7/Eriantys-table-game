package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.model.*;

import java.util.List;


public class GameController {
    private PlayGround currentGame;
    private GameSettings currentSettings;
    private IslandController islandController;
    private BoardController boardController;
    private TurnHandler turnHandler;

    public GameController(GameSettings settings)
    {
        currentSettings = settings;
        islandController = new IslandController();
        boardController = new BoardController();

    }

    public void setUpGame()
    {
        // qui richiamiamo tutti i controller per fare i set up

    }

    private void setUpPlayers(int numberOfPlayers)
    {

    }

    public PlayGround getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(PlayGround currentGame) {
        this.currentGame = currentGame;
    }

    public GameSettings getCurrentSettings() {
        return currentSettings;
    }

    public void setCurrentSettings(GameSettings currentSettings) {
        this.currentSettings = currentSettings;
    }

    public IslandController getIslandController() {
        return islandController;
    }

    public void setIslandController(IslandController islandController) {
        this.islandController = islandController;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    public void setTurnHandler(TurnHandler turnHandler) {
        this.turnHandler = turnHandler;
    }
    
    public void moveStudentsEntranceToIsland(int studentColour, int selectedIsland)
    {
       turnHandler.getCurrentPlayer().getPlayerBoard().removeStudentEntrance(studentColour);
       currentGame.getIslandByIndex(selectedIsland).setPlacedStudent(studentColour);

    }

}
