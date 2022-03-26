package main.java.it.polimi.ingsw.model;

import java.util.*;

/**
 * A board of the game.
 * Every board belongs to a specific player.
 * It's characterized by the entranceRoom, diningRoom, towerYard and the towerColour.
 * There are others attributes such as maxStudent (identify the maximum numbers of students of each color that can sit down in the diningRoom),
 *
 */

public class Board {
    private final Map<Colour,Integer> entranceRoom;
    private Map<Colour,Integer> diningRoom;
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
    public Board(Map<Colour, Integer> entranceRoom, int towerYard, TColour towerColour) {
        this.entranceRoom = entranceRoom;
        this.towerYard = towerYard;
        this.towerColour = towerColour;
        maxStudent = 10;

    }

    public Map<Colour, Integer> getEntranceRoom() {
        return entranceRoom;
    }
    /**
     * update the entranceRoom with new
     * students from CloudTile
     * @param newStudents new students
     */
    public void addStudentEntrance(Map<Colour, Integer> newStudents) {
        for(Colour c : Colour.values()) // da aggiungere controllo numero studenti
        {
            entranceRoom.put(c,entranceRoom.get(c)+ newStudents.get(c));
        }
    }

    public Map<Colour, Integer> getDiningRoom() {
        return diningRoom;
    }

    public void setDiningRoom(Map<Colour, Integer> diningRoom) {
        this.diningRoom = diningRoom;
    }

    /**
     * get the current number of students
     * by the colour sit down in the diningRoom
     * @param studentColour colour of the students to count
     * @return number of student of colour studentColour
     */
    public int getNumberOfStudent(Colour studentColour)
    {
        return diningRoom.get(studentColour);
    }
    /**
     * increase the current number of students
     * by the colour sit down in the diningRoom
     * @param studentColour colour of the students to increase the count
     */
    public void increaseNumberOfStudent(Colour studentColour) throws ArrayIndexOutOfBoundsException
    {
        if(diningRoom.size() == maxStudent )
            throw new ArrayIndexOutOfBoundsException();
        else
            diningRoom.put(studentColour, diningRoom.get(studentColour) + 1);
    }

    public int getTowerYard() {
        return towerYard;
    }

    public void setTowerYard(int towerYard) {
        this.towerYard = towerYard;
    }
    /**
     * increase the number of tower by one, it means
     * that a tower return from
     * an island to the towerYard
     *
     */
    public void increaseTowerYard()
    {
        towerYard++;
    }
    /**
     * decrease the number of tower by one, it means
     * that a tower go from
     * the towerYard to an island
     *
     */
    public void decreaseTowerYard()
    {
        towerYard--;
    }

    public TColour getTowerColour() {
        return towerColour;
    }

    public void setTowerColour(TColour towerColour) {
        this.towerColour = towerColour;
    }

}
