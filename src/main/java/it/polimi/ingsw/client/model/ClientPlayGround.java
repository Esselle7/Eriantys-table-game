package it.polimi.ingsw.client.model;


import java.util.List;

public class ClientPlayGround {
    private List<ClientPlayer> playersList;
    private List<ClientIsland> islands;
    private ClientIsland islandWithMotherNature;
    private String[] professorsControl;
    private ClientCloudTiles[] cloudTiles;

    public List<ClientPlayer> getPlayersList() {
        return playersList;
    }

    public List<ClientIsland> getIslands() {
        return islands;
    }

    public ClientIsland getIslandWithMotherNature() {
        return islandWithMotherNature;
    }

    public String[] getProfessorsControl() {
        return professorsControl;
    }

    public ClientCloudTiles[] getCloudTiles() {
        return cloudTiles;
    }

    public void setPlayersList(List<ClientPlayer> playersList) {
        this.playersList = playersList;
    }

    public void setIslands(List<ClientIsland> islands) {
        this.islands = islands;
    }

    public void setIslandWithMotherNature(ClientIsland islandWithMotherNature) {
        this.islandWithMotherNature = islandWithMotherNature;
    }

    public void setProfessorsControl(String[] professorsControl) {
        this.professorsControl = professorsControl;
    }

    public void setCloudTiles(ClientCloudTiles[] cloudTiles) {
        this.cloudTiles = cloudTiles;
    }
}
