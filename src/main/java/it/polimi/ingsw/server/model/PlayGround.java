package it.polimi.ingsw.server.model;

import java.util.List;
import java.util.Objects;

/**
 * Class that store every
 * instance of the class in order to
 * play a game.
 */

public class PlayGround {
    private List<Player> playersList;
    private List<Island> islands;
    private Island islandWithMotherNature;
    private final String[] professorsControl;
    private CloudTile[] cloudTiles;
    private static PlayGround instance = null;


    /**
     * Private constructor, applied
     * singleton pattern
     */
    private PlayGround() {
        professorsControl = new String[Colour.colourCount];
    }

    /**
     * Public static class that create
     * an instance of playground only
     * if there are no others object that
     * has been instanced before
     * @return the playGround instance
     */
    public static PlayGround createPlayground()
    {
        if(instance == null)
           instance = new PlayGround();
        return instance;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    /**
     * This method allows to retrieve
     * a player by it's nickname
     * @param nickname nickname of player to find
     * @return the player with nickname in input
     */
    public Player getPlayerByNickname(String nickname){
        return getPlayersList().stream().filter(p -> p.getNickname().equals(nickname)).findFirst().orElse(null);
    }

    public Island getIslandWithMotherNature() {
        return islandWithMotherNature;
    }

    public void setIslandWithMotherNature(Island islandWithMotherNature) {
        this.islandWithMotherNature = islandWithMotherNature;
    }

    public void setPlayersList(List<Player> playersList) {
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

    /**
     * This method allows to get
     * the player nickname that have
     * the control of the professor
     * given in input
     * @param professorColour colour of the professor
     * @return player that own the control of the professor
     *         given in input
     */
    public String getProfessorControlByColour(int professorColour)
    {
        return professorsControl[professorColour];
    }

    /**
     * This method allows to change
     * the control of a professor
     * from one player to another
     * given in input
     * @param professorColour colour of the professor to control
     * @param professorController future professor controller
     */
    public void setProfessorControlByColour(int professorColour, String professorController)
    {
        professorsControl[professorColour] =  professorController;
    }


    public CloudTile[] getCloudTiles() {
        return cloudTiles;
    }

    public void setCloudTiles(CloudTile[] cloudTiles) {
        this.cloudTiles = cloudTiles;
    }
}
