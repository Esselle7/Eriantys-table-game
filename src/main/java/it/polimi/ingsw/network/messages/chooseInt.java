package it.polimi.ingsw.network.messages;

public class chooseInt implements Message {
    private final int data;

    public chooseInt(int data) {
        this.data = data;
    }
    public int getData() {
        return data;
    }
}

