package it.polimi.ingsw.server.model;

/**
 * This abstract class will contain
 * the main information for two players
 * mode and three players mode
 */

public abstract class GameSettings {
    private int studentsEntranceRoom;
    private int towerYard;
    private int studentsCloudTile;
    private int diningRoomLenght;
    private int numberOfIslands;

    /**
     * This method will be implemented
     * in the two classes that extends
     * this abstract class. It allows
     * to modify the game settings
     * (the attributes)
     */
    public abstract void manageSettings();

    public int getNumberOfIslands() {
        return numberOfIslands;
    }

    public void setNumberOfIslands(int numberOfIslands) {
        this.numberOfIslands = numberOfIslands;
    }

    public int getDiningRoomLenght() {
        return diningRoomLenght;
    }

    public void setDiningRoomLenght(int diningRoomLenght) {
        this.diningRoomLenght = diningRoomLenght;
    }

    public int getStudentsEntranceRoom() {
        return studentsEntranceRoom;
    }

    public void setStudentsEntranceRoom(int studentsEntranceRoom) {
        this.studentsEntranceRoom = studentsEntranceRoom;
    }

    public int getTowerYard() {
        return towerYard;
    }

    public void setTowerYard(int towerYard) {
        this.towerYard = towerYard;
    }

    public int getStudentsCloudTile() {
        return studentsCloudTile;
    }

    public void setStudentsCloudTile(int studentsCloudTile) {
        this.studentsCloudTile = studentsCloudTile;
    }
}
