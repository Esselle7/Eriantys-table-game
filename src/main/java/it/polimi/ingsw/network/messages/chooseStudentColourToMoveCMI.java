package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;

import java.io.IOException;

public class chooseStudentColourToMoveCMI implements ClientMessageImplement {
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) throws IOException, InterruptedException {
        try{
            socket.sendMessage(new chooseInt(userInterface.chooseStudentColourToMove()));
        }
        catch (chooseCharacterCardException e)
        {
            socket.sendMessage(new wantToChooseCharacterCard(e.getCharacterCard()));
        }
    }
}
