package it.polimi.ingsw.server.model;

import java.util.List;
import java.util.ArrayList;

/**
 * This class implement an
 * Island where a player can place students
 * and where mother nature pow can jump in to.
 * There can be also a tower that represent the
 * influence of a player in a specific island
 *
 */

public class Island extends ManagerStudent {
    private List<Integer> placedStudent;
    private boolean motherNature;
    private int towerCount;
    private TColour towerColour;
    private static boolean islandSeatedUp = false; // magari mettilo in controller,non qui

    /**
     * First constructor that create an
     * empty island, only call this method
     * the first time at the beginning of the
     * game.
     */
    private Island()
    {
        placedStudent = new ArrayList<>();
        motherNature = false;
        towerCount = 0;
    }

    /**
     * Call the constructor only if
     * the islands aren't set up yet
     * @return instace of island
     * @throws InstantiationError if the 12 islands are already instanced
     */

    public static Island setUpIslands() throws InstantiationError
    {
        if (!islandSeatedUp)
            return new Island();
        else
            throw new InstantiationError();
    }


    /**
     *Private constructor only to call
     * in method that unify islands
     * @param placedStudent students to placed on the island
     * @param towerCount number of tower on the island (also
     *                   the number of islands unified together)
     */

    private Island(List<Integer> placedStudent, int towerCount)
    {
        this.placedStudent = placedStudent;
        this.towerCount = towerCount;

    }


    public TColour getTowerColour() {
        return towerColour;
    }

    public void setTowerColour(TColour towerColour) {
        this.towerColour = towerColour;
    }

    public int getTowerCount() {
        return towerCount;
    }

    /**
     * This method set a player
     * influence to an island by adding a
     * tower to that island.
     *
     * @throws IllegalCallerException If there are more than
     *                                a tower on a island it means that
     *                                it is a unified island and so you
     *                                cannot call this method
     */
    public void setTowerCount() throws IllegalCallerException{
        if(towerCount == 0)
            towerCount = 1;
        else
            throw new IllegalCallerException();
    }

    public static void setIslandSeatedUp() {
        islandSeatedUp = true;
    }

    public boolean isMotherNature() {
        return motherNature;
    }

    public void setMotherNature(boolean motherNature) {
        this.motherNature = motherNature;
    }

    public void setPlacedStudent(List<Integer> placedStudent) {
        this.placedStudent = placedStudent;
    }

    public List<Integer> getPlacedStudent() {
        return placedStudent;
    }

    public void addStudent(int studentColour)
    {
        setPlacedStudent(addStudentToTarget(getPlacedStudent(), studentColour));
    }

    /**
     * this method counts the number of
     * student of a colour given in input
     * @param studentColour colour of the students to count
     * @return number of students of colour studentColour
     */
    public int numberOfStudentByColour(int studentColour)
    {
        return placedStudent.get(studentColour);
    }

    /**
     * this method create a new island
     * just by adding the students and
     * the tower count of this
     * and the students of the island given in input
     * @param islandToUnify island to unify to this
     * @return a new instance that substitute
     *         this and islandToUnify
     */
    public Island unifyIslands(Island islandToUnify) // ricodati di fare metodo per unificazione ocn tipo statio "island i =...." e richiama la funzione
    {
        return new Island(addStudentsToTarget(getPlacedStudent(),islandToUnify.getPlacedStudent()), getTowerCount()+ islandToUnify.getTowerCount());
    }


}
