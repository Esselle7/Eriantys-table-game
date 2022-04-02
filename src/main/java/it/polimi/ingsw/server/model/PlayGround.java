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
    private final Player[] professorsControl;
    private CloudTile[] cloudTiles;
    private static final PlayGround instance = null;

    /**
     * Private constructor, applied
     * singleton pattern
     * @param playersList list of the current players
     * @param islands list of the islands instances
     * @param cloudTiles list of cloud tile with students
     *                   already on it
     */
    private PlayGround(List<Player> playersList, List<Island> islands, CloudTile[] cloudTiles) {
        this.playersList = playersList;
        this.islands = islands;
        this.cloudTiles = cloudTiles;
        professorsControl = new Player[Colour.colourCount];
    }

    /**
     * Public static class that create
     * an instance of playground only
     * if there are no others object that
     * has been instanced before
     * @param playersList list of the current players
     * @param islands list of the islands instances
     * @param cloudTiles list of cloud tile with students
     *      *            already on it
     * @return the playGround instance
     */
    public static PlayGround createPlayground(List<Player> playersList, List<Island> islands, CloudTile[] cloudTiles)
    {
        return Objects.requireNonNullElseGet(instance, () -> new PlayGround(playersList, islands, cloudTiles));
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public List<Island> getIslands() {
        return islands;
    }

    public void setIslands(List<Island> islands) {
        this.islands = islands;
    }

    public Player[] getProfessorsControl() {
        return professorsControl;
    }

    public Player getProfessorControlByColour(int professorColour)
    {
        return professorsControl[professorColour];
    }

    public void setProfessorControlByColour(int professorColour, Player professorController)
    {
        professorsControl[professorColour] = professorController;
    }


    public CloudTile[] getCloudTiles() {
        return cloudTiles;
    }

    public void setCloudTiles(CloudTile[] cloudTiles) {
        this.cloudTiles = cloudTiles;
    }
}
