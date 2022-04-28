package it.polimi.ingsw.network.messages;

/**
 * This class represent an integer chosen by the client
 * sent to te server
 */
public class chooseInt implements Message {
    private final int data;

    public chooseInt(int data) {
        this.data = data;
    }
    public int getData() {
        return data;
    }
}

