package it.polimi.ingsw.client.model;

import java.util.List;

public class ClientIsland {
    private int[] placedStudent;
    private int towerCount;
    private ClientTColour towerColour;

    public int[] getPlacedStudent() {
        return placedStudent;
    }

    public void setPlacedStudent(int[] placedStudent) {
        this.placedStudent = placedStudent;
    }

    public int getTowerCount() {
        return towerCount;
    }

    public void setTowerCount(int towerCount) {
        this.towerCount = towerCount;
    }

    public ClientTColour getTowerColour() {
        return towerColour;
    }

    public void setTowerColour(ClientTColour towerColour) {
        this.towerColour = towerColour;
    }
}
