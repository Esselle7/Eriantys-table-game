package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.ClientMessageImplement;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ConnectionClientSide;

public class JoinGameCMI implements ClientMessageImplement {
    private final int numberOfPlayers;
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) {
        userInterface.joinGame(getNumberOfPlayers());
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public JoinGameCMI(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
