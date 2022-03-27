package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A board of the game.
 * Every board belongs to a specific player.
 * It's characterized by the entranceRoom, diningRoom, towerYard and the towerColour.
 * There are others attributes such as maxStudent (identify the maximum numbers of students of each color that can sit down in the diningRoom),
 *
 */

class Board extends ManagerStudent {
    private List<Integer> entranceRoom;
    private List<Integer> diningRoom;
    private int towerYard;
    private TColour towerColour;
    private final int maxStudent;

    /**
     * Create a board.
     * @param entranceRoom represents the place where a player
     *                     can choose students to place in Island or in the diningRoom
     * @param towerYard represents the number of tower that are on the board
     * @param towerColour represents the tower colour choosen by the player
     */
    public Board(List<Integer> entranceRoom, int towerYard, TColour towerColour) {
        this.entranceRoom = entranceRoom;
        this.towerYard = towerYard;
        this.towerColour = towerColour;
        maxStudent = 10;
        diningRoom = new ArrayList<>();

    }

    public List<Integer> getEntranceRoom() { return entranceRoom; }

    public void setEntranceRoom(List<Integer> entranceRoom) { this.entranceRoom = entranceRoom; }

    public List<Integer> getDiningRoom() {
        return diningRoom;
    }

    public void setDiningRoom(List<Integer> diningRoom) {
        this.diningRoom = diningRoom;
    }

    public int getTowerYard() {
        return towerYard;
    }

    public void setTowerYard(int towerYard) {
        this.towerYard = towerYard;
    }

    public TColour getTowerColour() {
        return towerColour;
    }

    public void setTowerColour(TColour towerColour) {
        this.towerColour = towerColour;
    }

    public int getMaxStudent() { return maxStudent; }

    /**
     * update the entranceRoom with new
     * students from CloudTile
     * @param newStudents new students
     */
    public void addStudentEntrance(List<Integer> newStudents) {
        setEntranceRoom(addStudentsToTarget(entranceRoom,newStudents));

    }

    public void removeStudentEntrance(int studentColour)
    {
        setEntranceRoom(removeStudentFromTarget(entranceRoom,studentColour));
    }

    /**
     * get the current number of students
     * by the colour sit down in the diningRoom
     * @param studentColour colour of the students to count
     * @return number of student of colour studentColour
     */
    public int getNumberOfStudent(int studentColour) {

        return diningRoom.get(studentColour);
    }

    /**
     * increase the current number of students
     * by the colour sit down in the diningRoom
     * @param studentColour colour of the students to increase the count
     */
    public void increaseNumberOfStudent(int studentColour) throws ArrayIndexOutOfBoundsException
    {
        if(getDiningRoom().size() == getMaxStudent() )
            throw new ArrayIndexOutOfBoundsException();
        else
            setDiningRoom(addStudentToTarget(getDiningRoom(),studentColour));
    }


    /**
     * increase the number of tower by one, it means
     * that a tower return from
     * an island to the towerYard
     *
     */
    public void increaseTowerYard() { towerYard++; }

    /**
     * decrease the number of tower by one, it means
     * that a tower go from
     * the towerYard to an island
     *
     */
    public void decreaseTowerYard() {
        towerYard--;
    }

}
