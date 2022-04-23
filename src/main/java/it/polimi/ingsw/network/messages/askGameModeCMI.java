package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.ClientMessageImplement;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import java.io.IOException;

public class askGameModeCMI implements ClientMessageImplement {

    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) throws IOException {

        socket.sendMessage(new gameModeMessage(userInterface.askGameMode()));
    }
}
