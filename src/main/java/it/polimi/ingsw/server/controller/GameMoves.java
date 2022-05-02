package it.polimi.ingsw.server.controller;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.*;

import java.io.Serializable;
import java.util.*;

/**
 * This class allows setting up the game
 * and performing all the possible moves during
 * the game. It stores the "PlayGround" of
 * the game, the game settings and the turn
 * handler.
 */
public class GameMoves extends ManagerStudent implements Serializable {
    private PlayGround currentGame;
    private GameSettings currentSettings;
    private IslandController islandController;
    private Player currentPlayer;

    public GameMoves() {
        islandController = new IslandController();
        currentGame = new PlayGround();
    }





    public PlayGround getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(PlayGround currentGame) {
        this.currentGame = currentGame;
        getIslandController().setPlayGround(currentGame);
    }

    public GameSettings getCurrentSettings() {
        return currentSettings;
    }

    public void setCurrentSettings(GameSettings currentSettings) {
        this.currentSettings = currentSettings;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

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
     * This method allows to set up
     * all the correct game settings based
     * on the number of players
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
     * This method allows to create
     * the correct number of players
     * with their nicknames
     * @param gamePlayers list of associate client
     */
    private void setUpPlayers(List<VirtualViewConnection> gamePlayers)
    {
        ArrayList<Player> playersList = new ArrayList<>();
        for(VirtualViewConnection c: gamePlayers){
            playersList.add(new Player(c.getNickname()));
        }
        getCurrentGame().setPlayersList(playersList);
    }

    public IslandController getIslandController() {
        return islandController;
    }

    public void setIslandController(IslandController islandController) {
        this.islandController = islandController;
    }


    public Board getCurrentPlayerBoard() //shortcut
    {
        return getCurrentPlayer().getPlayerBoard();
    }

    /**
     * This method allows to move
     * a student from the board (entrance room)
     * to an island
     * @param studentColour the student colour to move
     * @param selectedIsland the island where will be
     *                       placed the student
     */
    public void moveStudentsEntranceToIsland(int studentColour, int selectedIsland) throws noStudentForColour
    {
        getCurrentPlayerBoard().removeStudentEntrance(studentColour);
        getCurrentGame().getIslandByIndex(selectedIsland).setPlacedStudent(studentColour);

    }

    /**
     * This method allows to move
     * a student from the entrance
     * room to the dining room
     * @param studentColour the student colour to move
     * @throws FullDiningRoomTable if there are no free
     *                              chairs in the dining
     *                              room
     */
    public void moveStudentEntranceToDining(int studentColour) throws FullDiningRoomTable, noStudentForColour
    {
        if(getCurrentPlayerBoard().getDiningRoom()[studentColour] < getCurrentSettings().getDiningRoomLenght() &&
                getCurrentPlayerBoard().getEntranceRoom()[studentColour] > 0)
        {
            getCurrentPlayerBoard().removeStudentEntrance(studentColour);
            getCurrentPlayerBoard().increaseNumberOfStudent(studentColour);

        }
        else if(getCurrentPlayerBoard().getDiningRoom()[studentColour] >= getCurrentSettings().getDiningRoomLenght())
            throw new FullDiningRoomTable();
        else if(getCurrentPlayerBoard().getEntranceRoom()[studentColour] == 0)
            throw new noStudentForColour();
    }

    public void setInfluenceToIsland() throws EmptyTowerYard
    {
        Player playerInfluence = getIslandController().checkInfluence();
        playerInfluence.getPlayerBoard().decreaseTowerYard();
        getCurrentGame().getIslandWithMotherNature().setInfluence();
        getCurrentGame().getIslandWithMotherNature().setTowerColour(playerInfluence.getPlayerBoard().getTowerColour());

    }

    public void changeInfluenceToIsland() throws EmptyTowerYard
    {
        Player playerInfluence = getIslandController().checkInfluence();
        for(int i = 0; i < getCurrentGame().getIslandWithMotherNature().getTowerCount(); i++)
            playerInfluence.getPlayerBoard().decreaseTowerYard();
        for(Player p : getCurrentGame().getPlayersList())
        {
            if(p.getPlayerBoard().getTowerColour().equals(getCurrentGame().getIslandWithMotherNature().getTowerColour()))
            {
                p.getPlayerBoard().setTowerYard(p.getPlayerBoard().getTowerYard()+getCurrentGame().getIslandWithMotherNature().getTowerCount());
            }
        }
        getCurrentGame().getIslandWithMotherNature().setTowerColour(playerInfluence.getPlayerBoard().getTowerColour());


    }

    /**
     * This method allows current player
     * to take students from a cloud tile
     * and to place them in the entrance
     * room
     * @param chosenCloudTile the number of the cloud tile
     *                        chosen
     * @throws CloudTileAlreadyTakenException if the cloud tile
     *                                        chosen is empty (/already taken)
     */
    public void takeStudentsFromCloudTile(int chosenCloudTile) throws CloudTileAlreadyTakenException
    {
        if(!getCurrentGame().getCloudTiles()[chosenCloudTile].isUsed()) {
            getCurrentPlayerBoard().setEntranceRoom(addStudentsToTarget(getCurrentPlayerBoard().getEntranceRoom(), getCurrentGame().getCloudTiles()[chosenCloudTile].getStudents()));

        }
        else
            throw new CloudTileAlreadyTakenException();
    }

    /**
     * This method allows to use an assistant card
     * by previously check the validity
     * @param cardNumber card number to remove from the deck
     *                   and to set as current card
     * @throws UnableToUseCardException if the check validity
     *                                  return false
     */
    public void useAssistantCard(int cardNumber) throws UnableToUseCardException
    {
        if(checkCardValidity(cardNumber)) {
           getCurrentPlayer().useCard(cardNumber);

        }
        else
            throw new UnableToUseCardException();

    }

    /**
     * This method check an assistant card validity
     * by searching if previous players have used
     * the same card
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
                return false;
        }
        return false;
    }

    /**
     * This method allow to check and set
     * the new professor controller
     * @param professorColour colour of the professor
     *                        of which will be checked the
     *                        control
     * @return the nickname of the player that control the
     *         professor given in input
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
            else
                if(playerNumberOfStudentByColour == maximum && maximum != 0)
                {

                    return getCurrentGame().getProfessorsControl()[professorColour];
                }
        }

        return nickname;
    }

    /**
     * This method check the control for all professors
     * by calling the method checkProfessorControlByColour()
     */
    public void checkProfessorsControl()
    {
        for (int professorColour = 0; professorColour < Colour.colourCount; professorColour++)
        {
            getCurrentGame().setProfessorControlByColour(professorColour,checkProfessorsControlByColour(professorColour));
        }

    }

    /**
     * This method check if there is a winner
     * or if the game can resume
     * @return nickname of the winner
     * @throws noWinnerException if there is no winner
     */
    public String checkWin() throws noWinnerException
    {
        if(checkForEmptyTowerYard() != null)
            return checkForEmptyTowerYard().getNickname();
        if(existDeckEmpty() || isBagEmpty())
                return findWinnerTower();
        else
            throw new noWinnerException();

    }

    /**
     * This method search a winner
     * by comparing the number of professor controlled
     * by each player
     * @return the nickname of the winner
     */
    private String findWinnerProfessors()
    {
        String nicknameWinner = null;
        int occurrencesWinner = 0;
        for (String nicknamePlayer: getCurrentGame().getProfessorsControl()) {
            if(nicknamePlayer != null)
            {
                int occurrencesPlayer = Collections.frequency(Arrays.asList(getCurrentGame().getProfessorsControl()),nicknamePlayer);
                if(occurrencesPlayer > occurrencesWinner)
                {
                    occurrencesWinner = occurrencesPlayer;
                    nicknameWinner = nicknamePlayer;
                }
            }
        }
        return nicknameWinner;
    }

    /**
     * This method search a winner
     * by comparing the number of tower
     * in each player board
     * @return the nickname of the winner
     */
    public String findWinnerTower()
    {
        int minimum = 10;
        String nicknameWinner = null;
        for (Player player: getCurrentGame().getPlayersList()) {
            int playerTowerYard = player.getPlayerBoard().getTowerYard();
            if(playerTowerYard < minimum)
            {
                minimum = playerTowerYard;
                nicknameWinner = player.getNickname();
            }
            else
                if(playerTowerYard == minimum)
                    return findWinnerProfessors();
        }
        return nicknameWinner;
    }

    /**
     * This method check if there can be a winner
     * by searching if there is at least one board
     * with no towers
     * @return the nickname of the winner
     */
    public Player checkForEmptyTowerYard()
    {
        return getCurrentGame().getPlayersList().stream().filter(player -> player.getPlayerBoard().getTowerYard() == 0).findFirst().orElse(null);
    }

    /**
     * This method check if a player
     * have finished the assistant card and
     * allows proceeding with the calculus of
     * the winner
     * @return true if there is at least an empty deck
     */
    public boolean existDeckEmpty()
    {
        return getCurrentGame().getPlayersList().stream().anyMatch(player -> player.getAssistantCards().getResidualCards().size() == 0);
    }

    /**
     * This method check if there are
     * no more students paw in the bag
     * and allows proceeding with the calculus of
     * the winner
     * @return true if the bag is empty
     */
    private boolean isBagEmpty()
    {
        return getTotalStudentPaws() == 0;
    }

    /**
     * This method allows to move mother
     * nature from an island to another
     * @param islandId the island where mother nature
     *                 will be positioned
     * @throws ExceededMotherNatureStepsException if the islandId refers to an island
     *                                             far from the current mother nature island
     *                                             more than the number of mother nature steps
     *                                             stored in the current assistant card
     */
    public void moveMotherNature(int islandId) throws ExceededMotherNatureStepsException {
        int indexIslandMotherNature = getCurrentGame().getIslands().indexOf(getCurrentGame().getIslandWithMotherNature());
        int steps = (islandId + indexIslandMotherNature) % getCurrentSettings().getNumberOfIslands();
        int numberOfIslands = getCurrentSettings().getNumberOfIslands();
        int motherNatureSteps = getCurrentPlayer().getCurrentCard().getMotherNatureSteps();

        if (steps > motherNatureSteps)
            throw new ExceededMotherNatureStepsException();
        else {
            if (indexIslandMotherNature + steps > numberOfIslands)
                steps -= numberOfIslands;
            getCurrentGame().setIslandWithMotherNature(getCurrentGame().getIslandByIndex(steps));

        }
    }

    /**
     * This method allows to set up
     * the correct number of decks based on the number
     * of players in the game
     */
    private void setUpDecks()
    {
        Wizard[] wizards = {Wizard.WIZARD1, Wizard.WIZARD2, Wizard.WIZARD3, Wizard.WIZARD4};
        List<Deck> decks = new ArrayList<>();
        for(int i = 0; i < getCurrentSettings().getNumberOfPlayers(); i++){
            decks.add(new Deck(wizards[i]));
            getCurrentGame().getPlayersList().get(i).setDeck(decks.get(i));
        }
    }

    /**
     * This method allows to set up 12
     * islands in the playground
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
     * This method allows to set up
     * the boards a nd associate them to
     * each players in the game
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
     * This method allows to set up
     * all the elements of the islands
     * at the start of the game so
     * students and mother nature in the
     * correct positions

     */
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

    /**
     * This method allows to set up
     * the cloud tiles
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
