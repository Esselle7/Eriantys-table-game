package it.polimi.ingsw.network.messages;

public class gameModeMessage implements Message{
    private final int numberOfPlayers;

    public gameModeMessage(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
