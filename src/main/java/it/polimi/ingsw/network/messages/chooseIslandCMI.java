package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.connection.ConnectionClientSide;

import java.io.IOException;

public class chooseIslandCMI implements ClientMessageImplement {
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) throws IOException, InterruptedException {
        int island = userInterface.chooseIsland();
        if(island != Client.getNotAllowedInt()){
            socket.sendMessage(new chooseInt(island));
        }
        else
        {
            socket.sendMessage(new wantToChooseCharacterCard(userInterface.chooseCharacterCard()));
        }
    }
}
