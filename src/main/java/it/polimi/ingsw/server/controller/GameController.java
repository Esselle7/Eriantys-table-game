package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.model.Colour;
import it.polimi.ingsw.server.model.GameSettings;
import it.polimi.ingsw.server.model.Island;
import it.polimi.ingsw.server.model.PlayGround;

import java.util.List;


public class GameController {
    private PlayGround currentGame;
    private TurnHandler turnHandler;
    private GameSettings currentSettings;
    private IslandController islandController;
    private BoardController boardController;

    public GameController(GameSettings settings) {
        currentSettings = settings;
        islandController = new IslandController();
        boardController = new BoardController();
    }

    public void setUpGame() {
        // qui richiamiamo tutti i controller per fare i set up

    }

    private void setUpPlayers(int numberOfPlayers) {

    }

    private List setUpIslands() {
        return null; // da modificare, l'ho messo solo per poter committare
    }

    public void moveMotherNature(Island island_to_move){
        island_to_move.setMotherNature();
    }


}
