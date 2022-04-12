package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.model.*;
import java.util.ArrayList;
import java.util.List;

public class GameController extends ManagerStudent {
    private PlayGround currentGame;
    private TurnHandler turnHandler;
    private GameSettings currentSettings;
    private final IslandController islandController;
    private final BoardController boardController;

    public GameController() {
        islandController = new IslandController();
        boardController = new BoardController();
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

    private void setUpGameSettings(int numberOfPlayers)
    {
        if(numberOfPlayers == 2){
            currentSettings = new TwoGameSettings();
            getCurrentSettings().manageSettings();
        }
        else{
            currentSettings = new ThreeGameSettings();
            getCurrentSettings().manageSettings();
        }
    }

    private void setUpPlayers(int numberOfPlayers, String[] playersName)
    {
        List<Player> playersList = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++){
            playersList.add(new Player(playersName[i]));
        }
        getCurrentGame().setPlayersList(playersList);
    }

    private void setUpDecks(int numberOfPlayer)
    {
        Wizard[] wizards = {Wizard.WIZARD1, Wizard.WIZARD2, Wizard.WIZARD3, Wizard.WIZARD4};
        List<Deck> decks = new ArrayList<>();
        for(int i = 0; i < numberOfPlayer; i++){
            decks.add(new Deck(wizards[i]));
            getCurrentGame().getPlayersList().get(i).setDeck(decks.get(i));
        }
    }

    private void setUpIslands()
    {
        List<Island> islands = new ArrayList<>();
        for(int i = 0; i < getCurrentSettings().getNumberOfIslands(); i++){
            islands.add(new Island());
        }
        getCurrentGame().setIslands(islands);
    }
  
    private void setUpBoard(){
        TColour[] colours = {TColour.WHITE, TColour.BLACK, TColour.GRAY}; //check se si può migliorare
        List<Board> boards = new ArrayList<>();
        for(int i = 0; i < getCurrentGame().getPlayersList().size(); i++){
            boards.add(new Board(generateStudents(getCurrentSettings().getStudentsEntranceRoom()), getCurrentSettings().getTowerYard(), colours[i]));
            getCurrentGame().getPlayersList().get(i).setPlayerBoard(boards.get(i));
        }
    }

    private void setUpElementsOnIslands()
    {
        for(int i = 0; i < getCurrentSettings().getNumberOfIslands(); i++){
            if(i == 0){
                getCurrentGame().setIslandWithMotherNature(getCurrentGame().getIslands().get(i));
            }
            else if(i!= 6){
                addStudentsToTarget(getCurrentGame().getIslands().get(i).getPlacedStudent(), generateStudents(1));
            }
        }
    }

    private void setUpCloudTile()
    {
        CloudTile[] cloudTiles = new CloudTile[getCurrentGame().getPlayersList().size()];
        for(int i = 0; i < getCurrentGame().getPlayersList().size(); i++){
            cloudTiles[i] = new CloudTile(generateStudents(getCurrentSettings().getStudentsCloudTile()));
        }
        getCurrentGame().setCloudTiles(cloudTiles);
    }
}
