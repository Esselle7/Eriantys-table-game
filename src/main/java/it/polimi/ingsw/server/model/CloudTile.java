package it.polimi.ingsw.server.model;

/**
 * Class that represent
 * a cloud tile of the game.
 * It is composed by the students
 * that a player at each turn can
 * take
 */

public class CloudTile {
    private int[] students;

    public CloudTile(int[] students) {
        this.students = students;
        boolean isUsed = false;
    }

    public int[] getStudents() {
        return students;
    }

    public void setStudents(int[] students) {
        this.students = students;
    }
}
