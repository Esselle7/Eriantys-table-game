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
    private final GameSettings gameSettings;

    /**
     * Private constructor, applied
     * singleton pattern
     * @param playersList list of the current players
     * @param islands list of the islands instances
     * @param cloudTiles list of cloud tile with students
     *                   already on it
     */
    public PlayGround(List<Player> playersList, List<Island> islands, CloudTile[] cloudTiles, GameSettings gameSettings) {
        this.playersList = playersList;
        this.islands = islands;
        this.cloudTiles = cloudTiles;
        this.gameSettings = gameSettings;
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
    public static PlayGround createPlayground(List<Player> playersList, List<Island> islands, CloudTile[] cloudTiles, GameSettings gameSettings)
    {
        return Objects.requireNonNullElseGet(instance, () -> new PlayGround(playersList, islands, cloudTiles, gameSettings));
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public Player getPlayerByNickname(String nickname){
        return getPlayersList().stream().filter(p -> p.getNickname().equals(nickname)).findFirst().orElse(null);
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
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
