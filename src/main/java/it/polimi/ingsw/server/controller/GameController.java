package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.*;
import java.util.*;

/**
 * This class allows setting up the game
 * and performing all the possible moves during
 * the game. It stores the "PlayGround" of
 * the game, the game settings and the turn
 * handler.
 */
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

    /**
     * This method allows to move
     * a student from the board (entrance room)
     * to an island
     * @param studentColour the student colour to move
     * @param selectedIsland the island where will be
     *                       placed the student
     */
    public void moveStudentsEntranceToIsland(int studentColour, int selectedIsland)
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
    public void moveStudentEntranceToDining(int studentColour) throws FullDiningRoomTable
    {
        if(getCurrentPlayerBoard().getDiningRoom()[studentColour] < getCurrentSettings().getDiningRoomLenght())
        {
            getCurrentPlayerBoard().removeStudentEntrance(studentColour);
            getCurrentPlayerBoard().increaseNumberOfStudent(studentColour);
        }
        else
            throw new FullDiningRoomTable();

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
        if(!getCurrentGame().getCloudTiles()[chosenCloudTile].isUsed())
            getCurrentPlayerBoard().setEntranceRoom(addStudentsToTarget(getCurrentPlayerBoard().getEntranceRoom(),getCurrentGame().getCloudTiles()[chosenCloudTile].getStudents()));
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
        if(checkCardValidity(cardNumber))
            getTurnHandler().getCurrentPlayer().useCard(cardNumber);
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
            if(player.getNickname().equals(getTurnHandler().getCurrentPlayer().getNickname()))
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
     *                        controll
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
                if(playerNumberOfStudentByColour == maximum)
                    return nickname;
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
        if(existDeckEmpty() || isBagEmpty() || threeIslandRemain())
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
            int occurrencesPlayer = Collections.frequency(Arrays.asList(getCurrentGame().getProfessorsControl()),nicknamePlayer);
            if(occurrencesPlayer > occurrencesWinner)
            {
                occurrencesWinner = occurrencesPlayer;
                nicknameWinner = nicknamePlayer;
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
    private Player checkForEmptyTowerYard()
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
    private boolean existDeckEmpty()
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
     * This method check if there are
     * 3 or less island on the playground
     * and allows proceeding with the calculus of
     * the winner
     * @return true if there are less than 3 islands
     */
    private boolean threeIslandRemain()
    {
        return getCurrentGame().getIslands().size() == 3;
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
    public void moveMotherNature(int islandId) throws ExceededMotherNatureStepsException
    {
        int indexIslandMotherNature = getCurrentGame().getIslands().indexOf(getCurrentGame().getIslandWithMotherNature());
        int steps = (islandId+indexIslandMotherNature)%getCurrentGame().getGameSettings().getNumberOfIslands();
        int numberOfIslands = getCurrentGame().getGameSettings().getNumberOfIslands();
        int motherNatureSteps = getTurnHandler().getCurrentPlayer().getCurrentCard().getMotherNatureSteps();

        if( steps > getTurnHandler().getCurrentPlayer().getCurrentCard().getMotherNatureSteps() )
            throw new ExceededMotherNatureStepsException();
        else
        {
            if(indexIslandMotherNature + steps > numberOfIslands )
                steps -= numberOfIslands;
            getCurrentGame().setIslandWithMotherNature(getCurrentGame().getIslandByIndex(steps));
        }
    }

}
