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
    private boolean isUsed;

    public CloudTile(int[] students) {
        this.students = students;
        isUsed = false;
    }

    public int[] getStudents() {
        isUsed = true;
        return students;

    }

    public void reFill(int[] students) {
        this.students = students;
        isUsed = false;
    }

    public boolean isUsed() {
        return isUsed;
    }
}