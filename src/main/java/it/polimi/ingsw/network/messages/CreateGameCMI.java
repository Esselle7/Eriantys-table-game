package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.ClientMessageImplement;
import it.polimi.ingsw.client.connection.ConnectionClientSide;

public class CreateGameCMI implements ClientMessageImplement {
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) {
        userInterface.createGame();
    }
}
