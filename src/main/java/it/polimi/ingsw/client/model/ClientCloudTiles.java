package it.polimi.ingsw.client.model;

public class ClientCloudTiles {
    private int[] students;
    private boolean isUsed;

    public int[] getStudents() {
        return students;
    }

    public void setStudents(int[] students) {
        this.students = students;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
