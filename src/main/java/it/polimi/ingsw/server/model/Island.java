package it.polimi.ingsw.server.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Island Class keeps track of which students, towers (and their colours) are placed on it.
 * It also keeps track of the Nearby Islands around it as well as the bans on computing towers in influence calculations
 * (isTowerBanned) or computing influence at all (isBanned)
 */
public class Island extends ManagerStudent implements Serializable {
    private final int[] placedStudent;
    private int towerCount;
    private TColour towerColour;
    private List<Island> NearbyIslands;
    private boolean isBanned;
    private boolean isTowerBanned;

    /**
     * Constructor that create an empty island, only call this method at the beginning of the game.
     */
    public Island()
    {
        placedStudent = new int[Colour.colourCount];
        towerCount = 0;
        isBanned = false;
        isTowerBanned = false;
    }

    /**
     *Private constructor used when unifying islands (hence a new island will be created)
     * @param placedStudent students to place on the new island
     * @param towerCount number of towers on the new island
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

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isTowersBanned() {
        return isTowerBanned;
    }

    public void setTowersBanned(boolean banned) {
        isTowerBanned = banned;
    }

    public int getTowerCount() {
        return towerCount;
    }

    public void setTowerCount(int towerCount) { this.towerCount = towerCount; }

    public List<Island> getNearbyIslands() {
        return NearbyIslands;
    }

    public void setNearbyIslands(List<Island> IslandsToSetAsNearby) {
        this.NearbyIslands = IslandsToSetAsNearby;
    }

    public void setInfluence(){
        towerCount = 1;
    }

    public void increasePlacedStudent(int studentColour) {
        placedStudent[studentColour]++;
    }

    public int[] getPlacedStudent() {
        return placedStudent;
    }

    /**
     * This method counts the number of students of a selected colour present on an island
     * @param studentColour selected colour
     * @return counter number
     */
    public int numberOfStudentByColour(int studentColour)
    {
        return placedStudent[studentColour];
    }

    /**
     * This method creates a new island and adds to it the students and the tower count of this island to the
     * ones of the island to unify
     * @param islandToUnify island to unify to this
     * @return the island resulting from the fusion
     */
    public Island unifyIslands(Island islandToUnify)
    {
        return new Island(addStudentsToTarget(getPlacedStudent(),islandToUnify.getPlacedStudent()), getTowerCount()+ islandToUnify.getTowerCount());
    }
}
