package it.polimi.ingsw.server.model;

import java.util.List;
import java.util.Objects;

public class PlayGround {
    private List<Player> playersList;
    private List<Island> islands;
    private final Player[] professorsControl;
    private CloudTile[] cloudTiles;
    private static final PlayGround instance = null;

    private PlayGround(List<Player> playersList, List<Island> islands, CloudTile[] cloudTiles) {
        this.playersList = playersList;
        this.islands = islands;
        this.cloudTiles = cloudTiles;
        professorsControl = new Player[Colour.colourCount];
    }

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
