package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import java.io.IOException;

/**
 * This class represent a lobby size request message from the server
 * to the client.
 */
public class LobbySizeCMI implements ClientMessageImplement {
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) throws IOException{
        int lobbySize = userInterface.askGameMode();
        socket.sendMessage(new chooseInt(lobbySize));
    }
}
