package it.polimi.ingsw.server.model;

/**
 * This class implement an
 * Island where a player can place students
 * and where mother nature pow can jump in to.
 * There can be also a tower that represent the
 * influence of a player in a specific island
 *
 */

public class Island extends ManagerStudent {
    private final int[] placedStudent;
    private boolean motherNature;
    private int towerCount;
    private TColour towerColour;

    /**
     * Constructor that create an
     * empty island, only call this method
     * the first time at the beginning of the
     * game.
     */
    public Island()
    {
        placedStudent = new int[Colour.colourCount];
        motherNature = false;
        towerCount = 0;
    }

    /**
     *Private constructor only to call
     * in method that unify islands
     * @param placedStudent students to placed on the island
     * @param towerCount number of tower on the island (also
     *                   the number of islands unified together)
     */

    private Island(int[] placedStudent, int towerCount)
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
     */
    public void setInfluence(){

        towerCount = 1;

    }

    public boolean isMotherNature() {
        return motherNature;
    }

    public void setMotherNature(boolean motherNature) {
        this.motherNature = motherNature;
    }

    public void setPlacedStudent(int studentColour) {
        placedStudent[studentColour]++;
    }

    public int[] getPlacedStudent() {
        return placedStudent;
    }


    /**
     * this method counts the number of
     * student of a colour given in input
     * @param studentColour colour of the students to count
     * @return number of students of colour studentColour
     */
    public int numberOfStudentByColour(int studentColour)
    {
        return placedStudent[studentColour];
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