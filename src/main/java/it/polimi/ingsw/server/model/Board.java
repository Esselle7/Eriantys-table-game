package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.controller.Exceptions.EmptyTowerYard;
import it.polimi.ingsw.server.controller.Exceptions.NotEnoughCoins;
import it.polimi.ingsw.server.controller.Exceptions.NoStudentForColour;
import it.polimi.ingsw.server.controller.Exceptions.EmptyDiningRoom;

import java.io.Serializable;

/**
 * Board/School belonging to each player of the game,
 * It stores the info about the entranceRoom, the diningRoom, the towerYard and the towerColour as well as
 * the number of coins the player has.
 * There's also the static attribute coinReserve which keeps up with the limited number of coins in circulation
 */

public class Board extends ManagerStudent implements Serializable {
    private int[] entranceRoom;
    private final int[] diningRoom;
    private int towerYard;
    private final TColour towerColour;
    private int coins;
    static int coinReserve = 20;

    /**
     * Board Constructor
     * @param entranceRoom is the array that sets the configuration of the entrance room
     * @param towerYard specifies the number of towers to place on the board
     * @param towerColour sets the towerColour corresponding to the board
     */
    public Board(int[] entranceRoom, int towerYard, TColour towerColour) {
        this.entranceRoom = entranceRoom;
        this.towerYard = towerYard;
        this.towerColour = towerColour;
        this.addCoin();
        diningRoom = new int[Colour.colourCount];
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void decreaseCoinReserve(){
        coinReserve--;
    }

    /**
     * This method adds a coin to the board while decreasing the coinReserve attribute to simulate removing a coin
     * from the money supply and adding it to the player's board
     */
    public void addCoin(){
        coins++;
        coinReserve--;
    }

    /**
     * This method decreases the board's coins in case the player chooses to use a character card. The subtracted coins
     * are added back to the coinReserve
     */
    public void decreaseCoins(int coinsToSubtract) throws NotEnoughCoins{
        if(this.coins >= coinsToSubtract) {
            this.coins = this.coins - coinsToSubtract;
            coinReserve = coinReserve + coinsToSubtract;
        }
        else
            throw new NotEnoughCoins();
    }

    public int[] getEntranceRoom() { return entranceRoom; }

    public void setEntranceRoom(int[] entranceRoom) { this.entranceRoom = entranceRoom; }

    public int[] getDiningRoom() {
        return diningRoom;
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



    /**
     * Adds an array of students to the entrance room. Often called when adding cloud tile students.
     * @param newStudents the students to add to the entrance room
     */
    public void addStudentEntrance(int[] newStudents) {
        setEntranceRoom(addStudentsToTarget(entranceRoom,newStudents));

    }

    /**
     * Removes a SINGLE student of the selected colour from the entrance room
     * @param studentColour the colour of the student to remove
     */
    public void removeStudentEntrance(int studentColour) throws NoStudentForColour
    {
        if(entranceRoom[studentColour] > 0)
            entranceRoom[studentColour] --;
        else
            throw new NoStudentForColour();
    }

    /**
     * Gets the number of students of a specific colour sitting down in the dining room
     * @param studentColour colour of the students to count
     * @return number of students counted
     */
    public int getNumberOfStudent(int studentColour) {
        return diningRoom[studentColour];
    }

    /**
     * Increases the number of students of the selected colour in the dining room.
     * In case the number of students is a multiple of 3, a coin will be added to the board.
     * @param studentColour selected colour
     */
    public void increaseNumberOfStudent(int studentColour)
    {
        diningRoom[studentColour]++;
        if(diningRoom[studentColour] == 3 || diningRoom[studentColour] == 6 || diningRoom[studentColour] == 9)
            this.addCoin();
    }

    /**
     * Removes a student of the selected colour from the dining room
     * @param colour selected colour
     */
    public void decreaseDiningRoom(int colour) throws EmptyDiningRoom{
        if(diningRoom[colour] > 0)
            diningRoom[colour]--;
        else
            throw new EmptyDiningRoom();
    }

    /**
     * Increases the number of towers in the tower yard.
     * Often called when placing a tower back to the board after influence changes.
     */
    public void increaseTowerYard() { towerYard++; }

    /**
     * Decreases the number of towers in the tower yard.
     * Often called when placing a tower on an island after influence changes.
     * @throws EmptyTowerYard if the tower yard is empty AFTER subtracting one tower from it
     */
    public void decreaseTowerYard() throws EmptyTowerYard {
        this.towerYard = this.towerYard - 1;
        if(towerYard == 0) {
            throw new EmptyTowerYard();
        }
    }

}
