package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.connection.ConnectionClientSide;

import java.io.IOException;

public class chooseYesOrNoCMI implements ClientMessageImplement {
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) throws IOException, InterruptedException {
        int number = userInterface.chooseYesOrNo();
        if(number != Client.getNotAllowedInt()){
            socket.sendMessage(new chooseInt(number));
        }
        else
        {
            socket.sendMessage(new wantToChooseCharacterCard(userInterface.chooseCharacterCard()));
        }
    }
}
