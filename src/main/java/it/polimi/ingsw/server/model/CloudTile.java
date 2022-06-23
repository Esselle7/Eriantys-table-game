package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * A cloud tile, its attributes are the students onto it as well as a parameter (isUsed) to track whether the cloud tile
 * is empty (true) or it has already been refilled (false)
 */

public class CloudTile  implements Serializable {
    private int[] students;
    private boolean isUsed=false;

    /**
     * CloudTile Constructor, it sets the students on the cloud tile and sets the isUsed parameter to false
     * @param students students to set on the cloud tile
     */
    public CloudTile(int[] students) {
        this.students = students;
        isUsed = false;
    }

    public void setStudents(int[] students) {
        this.students = students;
        isUsed = false;
    }

    public int[] getStudents() {
        isUsed = true;
        return students;
    }

    /**
     * Shows the students on the cloud tile, useful for displaying them on the GUI or CLI
     */
    public int[] showStudents(){
        return students;
    }

    /**
     * Refills the cloud tile with the selected students, it differs from setStudents because it also sets the isUsed
     * attribute to false.
     */
    public void reFill(int[] students) {
        this.students = students;
        setUsed();
    }
    public boolean isUsed() {
        return isUsed;
    }
    private void setUsed()
    {
        isUsed = false;
    }
}
