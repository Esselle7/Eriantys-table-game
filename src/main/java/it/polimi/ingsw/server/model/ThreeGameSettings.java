package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * This class store the settings for the three player mode
 */
public class ThreeGameSettings extends GameSettings implements Serializable {

    /**
     * This method sets the settings for the three player mode
     */
    @Override
    public void manageSettings() {
        setStudentsCloudTile(4);
        setTowerYard(6);
        setStudentsEntranceRoom(9);
        setDiningRoomLength(10);
        setNumberOfIslands(12);
        setNumberOfPlayers(3);
        setStudentsToMove(4);
    }
}
