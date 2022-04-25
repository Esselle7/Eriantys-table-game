package it.polimi.ingsw.client.model;

import it.polimi.ingsw.server.model.TColour;

public class ClientBoard {
    private int[] entranceRoom;
    private int[] diningRoom;
    private int towerYard;
    private ClientTColour towerColour;

    public int[] getEntranceRoom() {
        return entranceRoom;
    }

    public void setEntranceRoom(int[] entranceRoom) {
        this.entranceRoom = entranceRoom;
    }

    public int[] getDiningRoom() {
        return diningRoom;
    }

    public void setDiningRoom(int[] diningRoom) {
        this.diningRoom = diningRoom;
    }

    public int getTowerYard() {
        return towerYard;
    }

    public void setTowerYard(int towerYard) {
        this.towerYard = towerYard;
    }

    public ClientTColour getTowerColour() {
        return towerColour;
    }

    public void setTowerColour(ClientTColour towerColour) {
        this.towerColour = towerColour;
    }
}
