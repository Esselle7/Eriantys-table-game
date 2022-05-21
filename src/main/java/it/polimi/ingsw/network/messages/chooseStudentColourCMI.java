package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;

import java.io.IOException;

public class chooseStudentColourCMI implements ClientMessageImplement {
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) throws IOException, InterruptedException {
        int studentColour = userInterface.chooseStudentColour();
        if(studentColour != Client.getNotAllowedInt()){
            socket.sendMessage(new chooseInt(studentColour));
        }
        else
        {
            socket.sendMessage(new wantToChooseCharacterCard(userInterface.chooseCharacterCard()));
        }
    }
}