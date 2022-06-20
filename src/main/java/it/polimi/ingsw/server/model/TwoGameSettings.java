package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * This class store the settings for the two player mode
 */
public class TwoGameSettings extends GameSettings implements Serializable {

    /**
     * This method sets the settings for the two player mode
     */
    @Override
    public void manageSettings() {
        setStudentsCloudTile(3);
        setTowerYard(8);
        setStudentsEntranceRoom(7);
        setDiningRoomLength(10);
        setNumberOfIslands(12);
        setNumberOfPlayers(2);
        setStudentsToMove(3);
    }
}
