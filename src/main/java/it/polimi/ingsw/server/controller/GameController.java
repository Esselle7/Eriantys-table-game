package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.model.*;


public class GameController extends ManagerStudent{
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

    private Board getCurrentPlayerBoard() //shortcut
    {
        return getTurnHandler().getCurrentPlayerBoard();
    }

    public void moveStudentsEntranceToIsland(int studentColour, int selectedIsland)
    {
        getCurrentPlayerBoard().removeStudentEntrance(studentColour);
        getCurrentGame().getIslandByIndex(selectedIsland).setPlacedStudent(studentColour);

    }

    public void moveStudentEntranceToDining(int studentColour)
    {
        getCurrentPlayerBoard().removeStudentEntrance(studentColour);
        getCurrentPlayerBoard().increaseNumberOfStudent(studentColour);
    }

    public void setInfluenceToIsland()
    {
        getCurrentPlayerBoard().decreaseTowerYard();
        getCurrentGame().getIslandWithMotherNature().setInfluence();
        getCurrentGame().getIslandWithMotherNature().setTowerColour(getCurrentPlayerBoard().getTowerColour());
    }

    public void changeInfluenceToIsland()
    {
        getCurrentPlayerBoard().decreaseTowerYard();
        getCurrentGame().getPlayersList().stream().filter(p -> p.getPlayerBoard().getTowerColour().equals(getCurrentGame().getIslandWithMotherNature().getTowerColour())).forEachOrdered(p -> p.getPlayerBoard().increaseTowerYard()); //add tower to previous island owner
        getCurrentGame().getIslandWithMotherNature().setTowerColour(getCurrentPlayerBoard().getTowerColour());

    }

    public void takeStudentsFromCloudTile(int chosenCloudTile) // you have to control isUsed attribute in CloudTile before calling this method
    {
        getCurrentPlayerBoard().setEntranceRoom(addStudentsToTarget(getCurrentPlayerBoard().getEntranceRoom(),getCurrentGame().getCloudTiles()[chosenCloudTile].getStudents()));
    }

    public void useAssistantCard(int cardNumber)
    {
        if(checkCardValidity(cardNumber))
            getTurnHandler().getCurrentPlayer().useCard(cardNumber);
        // else throw an exception

    }

    private boolean checkCardValidity(int cardNumber)
    {
        for (Player player: getCurrentGame().getPlayersList()) {
            if(player.getNickname().equals(getTurnHandler().getCurrentPlayer().getNickname()))
                return true;
            if(player.getCurrentCard().getValue() == cardNumber)
                return false;
        }
        return false;
    }




} // ottimizza codice con scrittura di metodi in turn handler
