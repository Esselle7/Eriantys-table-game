package it.polimi.ingsw.server.model;

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
