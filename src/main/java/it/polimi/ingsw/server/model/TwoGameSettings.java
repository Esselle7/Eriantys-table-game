package it.polimi.ingsw.server.model;

/**
 * This class store the settings
 * for three players
 */

public class TwoGameSettings extends GameSettings{

    /**
     * This method allow to set
     * the settings for two players
     */
    @Override
    public void manageSettings() {
        setStudentsCloudTile(3);
        setTowerYard(8);
        setStudentsEntranceRoom(7);
        setDiningRoomLenght(10);
        setNumberOfIslands(12);
        setNumberOfPlayers(2);
    }
}
