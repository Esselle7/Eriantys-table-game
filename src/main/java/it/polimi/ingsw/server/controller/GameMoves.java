package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.*;

import java.io.Serializable;
import java.util.*;

/**
 * This class features the aggregated functions of the game, such as all the setup functions, the influence changing
 * functions and so on. These functions are useful for the TurnHandler class.
 * It also stores the Game's playground as currentGame, the current settings, the islandController, the currentPlayer and
 * a priorityPlayer in case EqualProfessorCard is used.
 */
public class GameMoves extends ManagerStudent implements Serializable {
    private PlayGround currentGame;
    private GameSettings currentSettings;
    private final IslandController islandController;
    private Player currentPlayer;
    private Player priorityPlayer = null;

    /**
     * Public Constructor
     */
    public GameMoves() {
        islandController = new IslandController();
        currentGame = new PlayGround();
    }

    public void setPriorityPlayer(Player priorityPlayer) {
        this.priorityPlayer = priorityPlayer;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public IslandController getIslandController() {
        return islandController;
    }

    public Board getCurrentPlayerBoard() //shortcut
    {
        return getCurrentPlayer().getPlayerBoard();
    }

    /**
     * This method recalls the Player that has the nickname passed as argument
     * @param nickname selected nickname
     * @return returned player
     */
    public Player getPlayerByNickname(String nickname){
        for(Player player : currentGame.getPlayersList()){
            if(Objects.equals(player.getNickname(), nickname))
                return player;
        }
        return null;
    }

    /**
     * This method allows sets up the game by calling all the setup functions of this class
     * @param numberOfPlayers the number of players in the game
     * @param gamePlayers List<VirtualViewConnection> useful for setting up the players
     */
    public void setUpGame(int numberOfPlayers, List<VirtualViewConnection> gamePlayers)
    {
        setUpPlayers(gamePlayers);
        setUpGameSettings(numberOfPlayers);
        setUpDecks();
        setUpIslands();
        setUpCloudTile();
        setUpBoard();
    }
    /**
     * This method sets up the correct game settings depending on the number of players
     * @param numberOfPlayers the number of players in the game
     */
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

    /**
     * This method sets up the players
     * @param gamePlayers clients' connections
     */
    private void setUpPlayers(List<VirtualViewConnection> gamePlayers)
    {
        ArrayList<Player> playersList = new ArrayList<>();
        for(VirtualViewConnection c: gamePlayers){
            playersList.add(new Player(c.getNickname()));
        }
        getCurrentGame().setPlayersList(playersList);
    }

    /**
     * This method allows to move a student from the board (entrance room) to an island
     * @param studentColour the student colour to move
     * @param selectedIsland the island where the student will be place
     */
    public void moveStudentsEntranceToIsland(int studentColour, int selectedIsland) throws NoStudentForColour
    {
        getCurrentPlayerBoard().removeStudentEntrance(studentColour);
        getCurrentGame().getIslandByIndex(selectedIsland).increasePlacedStudent(studentColour);
    }

    /**
     * This method allows to move a student from the entrance room to the dining room
     * @param studentColour the student colour to move
     * @throws FullDiningRoomTable if there are no free
     *                              chairs in the dining
     *                              room
     */
    public void moveStudentEntranceToDining(int studentColour) throws FullDiningRoomTable, NoStudentForColour {
        if(getCurrentPlayerBoard().getDiningRoom()[studentColour] < getCurrentSettings().getDiningRoomLength() &&
                getCurrentPlayerBoard().getEntranceRoom()[studentColour] > 0)
        {
            getCurrentPlayerBoard().removeStudentEntrance(studentColour);
            getCurrentPlayerBoard().increaseNumberOfStudent(studentColour);
        }
        else if(getCurrentPlayerBoard().getDiningRoom()[studentColour] >= getCurrentSettings().getDiningRoomLength())
            throw new FullDiningRoomTable();

    }

    /**
     * This method allows to set the influence of an island which has had no influence control up until now
     * @param island is the island whose influence has to be set
     */
    public void setInfluenceToIsland(Island island) throws EmptyTowerYard
    {
        Player playerInfluence = getIslandController().checkInfluence(island, getCurrentGame());
        playerInfluence.getPlayerBoard().decreaseTowerYard();
        island.setInfluence();
        island.setTowerColour(playerInfluence.getPlayerBoard().getTowerColour());
    }

    /**
     * This method allows to change the influence of an island
     * @param island is the island whose influence has to be changed, it can also be an island on which
     *               multiple towers reside
     */
    public void changeInfluenceToIsland(Island island) throws EmptyTowerYard
    {
        Player playerInfluence = getIslandController().checkInfluence(island,getCurrentGame());
        Player previousInfluence = getCurrentGame().getPlayerByTowerColour(island.getTowerColour());
        for(int i = 0; i < island.getTowerCount(); i++)
            playerInfluence.getPlayerBoard().decreaseTowerYard();
        previousInfluence.getPlayerBoard().setTowerYard(previousInfluence.getPlayerBoard().getTowerYard()+island.getTowerCount());
        island.setTowerColour(playerInfluence.getPlayerBoard().getTowerColour());
    }

    /**
     * This method allows current player to take students from a cloud tile and place them in the entrance room
     * @param chosenCloudTile the cloud tile index
     * @throws CloudTileAlreadyTakenException if the cloud tile
     *                                        chosen is empty (/already taken)
     */
    public void takeStudentsFromCloudTile(int chosenCloudTile) throws CloudTileAlreadyTakenException
    {
        if(!getCurrentGame().getCloudTiles()[chosenCloudTile-1].isUsed()) {
            getCurrentPlayerBoard().setEntranceRoom(addStudentsToTarget(getCurrentPlayerBoard().getEntranceRoom(), getCurrentGame().getCloudTiles()[chosenCloudTile-1].getStudents()));
        }
        else
            throw new CloudTileAlreadyTakenException();
    }

    /**
     * This method allows to use an assistant card by previously checking its validity
     * @param cardNumber card number to remove from the deck
     *                   and to set as current card
     * @throws UnableToUseCardException if the validity check
     *                                  returns false
     */
    public void useAssistantCard(int cardNumber) throws UnableToUseCardException, CardNotFoundException {
        if(checkCardValidity(cardNumber)) {
           if(!getCurrentPlayer().useCard(cardNumber))
               throw new CardNotFoundException();
        }
        else
            throw new UnableToUseCardException();
    }

    /**
     * This method checks an assistant card validity by searching if previous players have used the same card
     * @param cardNumber card number to check
     * @return true if the card number is valid,
     *          false if not
     */
    private boolean checkCardValidity(int cardNumber)
    {
        for (Player player: getCurrentGame().getPlayersList()) {
            if(player.getNickname().equals(getCurrentPlayer().getNickname()))
                return true;
            if(player.getCurrentCard().getValue() == cardNumber)
                break;
        }
        return false;
    }

    /**
     * This method allows to check a selected colour's professor control
     * @param professorColour selected colour
     * @return the nickname of the player that controls the professor
     */
    private String checkProfessorsControlByColour(int professorColour)
    {
        String nickname = null;
        int maximum = 0;
        for (Player player: getCurrentGame().getPlayersList()) {
            int playerNumberOfStudentByColour = player.getPlayerBoard().getDiningRoom()[professorColour];
            if(playerNumberOfStudentByColour > maximum)
            {
                maximum = playerNumberOfStudentByColour;
                nickname = player.getNickname();
            }
        }
        Player currentControlPlayer = getCurrentGame().getPlayerByNickname(getCurrentGame().getProfessorsControl()[professorColour]);
        if(priorityPlayer != null && priorityPlayer.getPlayerBoard().getDiningRoom()[professorColour] == maximum){
            return priorityPlayer.getNickname();
        }
        else if(currentControlPlayer!= null && currentControlPlayer.getPlayerBoard().getDiningRoom()[professorColour] == maximum)
        {
            return getCurrentGame().getProfessorsControl()[professorColour];
        }
        else
            return nickname;
    }

    /**
     * This method updates the control of all professor colours stored in GameMoves.currentGame
     * by calling the method checkProfessorControlByColour() on each colour
     */
    public void checkProfessorsControl()
    {
        for (int professorColour = 0; professorColour < Colour.colourCount; professorColour++)
        {
            getCurrentGame().setProfessorControlByColour(professorColour,checkProfessorsControlByColour(professorColour));
        }

    }

    /**
     * This method looks for a winner by comparing the number of towers in each player's board and, in case
     * there's an equal amount of towers, it checks the amount of professors controlled as per the game rules
     * @return the Player with the least amount of towers
     */
    public Player findWinnerTower() throws NoWinnerException
    {
        boolean draw = false;
        int minimum = 10, playerTowerYard;
        Player minPlayer = null;
        List <Player> equalTowerPlayers = new ArrayList<>();
        for (Player player: getCurrentGame().getPlayersList()) {
            playerTowerYard = player.getPlayerBoard().getTowerYard();
            if(playerTowerYard < minimum)
            {
                minimum = playerTowerYard;
                minPlayer = player;
                equalTowerPlayers.add(minPlayer);
                draw = false;
            }
            else if(playerTowerYard == minimum) {
                    draw = true;
                    equalTowerPlayers.add(player);
            }
        }
        if(draw)
            return findWinnerProfessors(equalTowerPlayers);
        else
            return minPlayer;
    }

    /**
     * This method looks for the player with the most amount of colours controlled in between a specific list of players
     * @param equalTowerPlayers the list of players
     * @return the player with the most amount of colours controlled
     */
    private Player findWinnerProfessors(List<Player> equalTowerPlayers) throws NoWinnerException
    {
        Player maxPlayer = null;
        boolean draw = false;
        int maxOccurrences = 0, playerOccurrences;
        checkProfessorsControl();
        for(Player player: equalTowerPlayers){
            playerOccurrences = Collections.frequency(Arrays.asList(getCurrentGame().getProfessorsControl()), player.getNickname());
            if(playerOccurrences > maxOccurrences)
            {
                maxOccurrences = playerOccurrences;
                maxPlayer = getPlayerByNickname(player.getNickname());
                draw = false;
            } else if(playerOccurrences == maxOccurrences)
            {
                draw = true;
            }
        }
        if(!draw) {
            return maxPlayer;
        } else {
            throw new NoWinnerException();
        }
    }

    /**
     * This method checks which player has a board with no towers
     * @return the winning player
     */
    public Player checkForEmptyTowerYard()
    {
        return getCurrentGame().getPlayersList().stream().filter(player -> player.getPlayerBoard().getTowerYard() == 0).findFirst().orElse(null);
    }

    /**
     * This method allows moving mother nature from an island to another island
     * @param islandId the island where mother nature
     *                 will be positioned
     * @throws ExceededMotherNatureStepsException if the islandId refers to an island
     *                                             too far from the current mother nature island
     *                                             more than the number of mother nature steps
     *                                             stored in the player's steps attribute
     */
    public void moveMotherNature(int islandId) throws ExceededMotherNatureStepsException {
        int indexIslandMotherNature = getCurrentGame().getIslands().indexOf(getCurrentGame().getIslandWithMotherNature());
        int steps;
        islandId = islandId-1;
        if(islandId<indexIslandMotherNature)
            steps = (getCurrentSettings().getNumberOfIslands()-1)-indexIslandMotherNature+islandId;
        else
            steps = islandId-indexIslandMotherNature;
        int motherNatureSteps = getCurrentPlayer().getMotherNatureSteps();
        if (steps > motherNatureSteps || steps < 1)
            throw new ExceededMotherNatureStepsException();
        else
            getCurrentGame().setIslandWithMotherNature(getCurrentGame().getIslandByIndex(islandId));
    }

    /**
     * This method allows setting up the correct number of decks based on the number of players in the game
     */
    private void setUpDecks()
    {
        List<Deck> decks = new ArrayList<>();
        for(int i = 0; i < getCurrentSettings().getNumberOfPlayers(); i++){
            decks.add(new Deck());
            getCurrentGame().getPlayersList().get(i).setDeck(decks.get(i));
        }
    }

    /**
     * This method allows setting up 12 islands in the playground
     */
    private void setUpIslands()
    {
        ArrayList<Island> islands = new ArrayList<>();
        for(int i = 0; i < getCurrentSettings().getNumberOfIslands(); i++){
            islands.add(new Island());
        }
        getCurrentGame().setIslands(islands);
        setUpElementsOnIslands();
    }

    /**
     * This method allows setting up the boards and link them to the players
     */
    private void setUpBoard(){
        TColour[] colours = {TColour.WHITE, TColour.BLACK, TColour.GRAY};
        List<Board> boards = new ArrayList<>();
        for(int i = 0; i < getCurrentGame().getPlayersList().size(); i++){
            boards.add(new Board(generateStudents(getCurrentSettings().getStudentsEntranceRoom()), getCurrentSettings().getTowerYard(), colours[i]));
            getCurrentGame().getPlayersList().get(i).setPlayerBoard(boards.get(i));
        }
    }

    /**
     * This method allows setting up all the elements of the islands at the start of the game so
     * students and mother nature are in the correct positions
     */
    private void setUpElementsOnIslands()
    {
        for(int i = 0; i < getCurrentSettings().getNumberOfIslands(); i++){
            if(i == 0){
                getCurrentGame().setIslandWithMotherNature(getCurrentGame().getIslands().get(i));
            }
            if(i!= 6){
                addStudentsToTarget(getCurrentGame().getIslands().get(i).getPlacedStudent(), generateStudents(1));
            }
        }
    }

    /**
     * This method allows setting up the cloud tiles
     */
    private void setUpCloudTile()
    {
        CloudTile[] cloudTiles = new CloudTile[getCurrentGame().getPlayersList().size()];
        for(int i = 0; i < getCurrentGame().getPlayersList().size(); i++){
            cloudTiles[i] = new CloudTile(generateStudents(getCurrentSettings().getStudentsCloudTile()));
        }
        getCurrentGame().setCloudTiles(cloudTiles);
    }

}
