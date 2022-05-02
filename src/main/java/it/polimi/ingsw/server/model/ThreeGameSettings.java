package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * This class store the settings
 * for three players
 */

public class ThreeGameSettings extends GameSettings implements Serializable {
    /**
     * This method allow to set
     * the settings for two players
     */
    @Override
    public void manageSettings() {
        setStudentsCloudTile(4);
        setTowerYard(6);
        setStudentsEntranceRoom(9);
        setDiningRoomLenght(10);
        setNumberOfIslands(12);
        setNumberOfPlayers(3);
        setStudentsToMove(4);
    }
}
