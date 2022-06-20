package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * This abstract class contains the information that differentiates each player mode (EG two players mode and three players mode).
 * This class is made up of only getters and setters plus a method (manageSettings) that uses all the setters depending on
 * the player mode class that extends this class.
 */

public abstract class GameSettings implements Serializable {
    private int studentsEntranceRoom;
    private int towerYard;
    private int studentsCloudTile;
    private int diningRoomLength;
    private int numberOfIslands;
    private int numberOfPlayers;
    private int studentsToMove;

    public int getNumberOfIslands() {
        return numberOfIslands;
    }

    public void setNumberOfIslands(int numberOfIslands) {
        this.numberOfIslands = numberOfIslands;
    }

    public int getDiningRoomLength() {
        return diningRoomLength;
    }

    public void setDiningRoomLength(int diningRoomLength) {
        this.diningRoomLength = diningRoomLength;
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

    public int getNumberOfPlayers() {return numberOfPlayers;}

    public void setNumberOfPlayers(int numberOfPlayers) {this.numberOfPlayers = numberOfPlayers;}

    public int getStudentsToMove() {
        return studentsToMove;
    }

    public void setStudentsToMove(int studentsToMove){
        this.studentsToMove = studentsToMove;
    }

    /**
     * This method will be implemented by the classes that extend this abstract class.
     * This way all the attributes specific to a player mode will be set.
     */
    public abstract void manageSettings();

}
