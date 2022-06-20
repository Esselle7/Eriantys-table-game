package it.polimi.ingsw.server.model;
import it.polimi.ingsw.server.controller.expert.CharacterCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores everything that's placed on the table in the physical game as well as some useful
 * information: the list of players, the (sorted) list of islands, the island with mothernature on it,
 * the professor control array, the cloud tiles array, the drawn cards array and the game mode setting
 */
public class PlayGround implements Serializable {
    private List<Player> playersList;
    private List<Island> islands;
    private Island islandWithMotherNature;
    private final String[] professorsControl;
    private CloudTile[] cloudTiles;
    private List <CharacterCard> drawnCards;
    private int gameMode;

    public CloudTile[] getCloudTiles() {
        return cloudTiles;
    }

    public void setCloudTiles(CloudTile[] cloudTiles) {
        this.cloudTiles = cloudTiles;
    }

    public Island getIslandWithMotherNature() {
        return islandWithMotherNature;
    }

    public void setIslandWithMotherNature(Island islandWithMotherNature) {
        this.islandWithMotherNature = islandWithMotherNature;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public List<Island> getIslands() {
        return islands;
    }

    public Island getIslandByIndex(int index)
    {
        return islands.get(index);
    }

    public void setIslands(List<Island> islands) {
        this.islands = islands;
    }

    public String[] getProfessorsControl() {
        return professorsControl;
    }

    public List<CharacterCard> getDrawnCards() {
        return drawnCards;
    }

    public void setDrawnCards(List<CharacterCard> drawnCards) { this.drawnCards = drawnCards;    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    /**
     * Public constructor
     */
    public PlayGround() {
        professorsControl = new String[Colour.colourCount];
        drawnCards = new ArrayList<>();
    }

    /**
     * This method allows to retrieve a player by its nickname
     * @param nickname nickname of the player to find
     * @return the resulting player
     */
    public Player getPlayerByNickname(String nickname){
        return getPlayersList().stream().filter(p -> p.getNickname().equals(nickname)).findFirst().orElse(null);
    }

    /**
     * This method allows to set the control of a professor given in input to a specific player
     * @param professorColour colour of the professor whose control is bound to change
     * @param professorController nickname of the player that has to be set as a new professor controller
     */
    public void setProfessorControlByColour(int professorColour, String professorController)
    {
        professorsControl[professorColour] =  professorController;
    }

    /**
     * This method returns the player whose tower colour is the one passed as argument
     * @param colour colour of the tower
     * @return Player that has towers of this same colour in his tower yard
     */
    public Player getPlayerByTowerColour(TColour colour){
        for(Player player : getPlayersList()){
            if(player.getPlayerBoard().getTowerColour().equals(colour))
                return player;
        }
        return null;
    }
}
