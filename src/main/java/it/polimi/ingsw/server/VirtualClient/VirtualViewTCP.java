package it.polimi.ingsw.server.VirtualClient;

import it.polimi.ingsw.network.connectionTCP.IncomingTCP;
import it.polimi.ingsw.network.connectionTCP.OutcomingTCP;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.Ping;
import it.polimi.ingsw.network.messages.chooseInt;
import it.polimi.ingsw.network.messages.chooseString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class VirtualViewTCP implements VirtualViewConnection {
    private final IncomingTCP InFromClient;
    private final OutcomingTCP OutToClient;

    public VirtualViewTCP(final Socket socket) throws IOException {

        InFromClient = new IncomingTCP(new ObjectInputStream(socket.getInputStream()));
        OutToClient = new OutcomingTCP(new ObjectOutputStream(socket.getOutputStream()));

        Thread inputThread = new Thread(InFromClient);
        inputThread.setName("TCPIn");

        Thread outputThread = new Thread(OutToClient);
        outputThread.setName("TCPOut");

        inputThread.start();
        outputThread.start();
    }
    @Override
    public void ping() throws IOException {
        sendMessage(new Ping());
    }


    @Override
    public void sendMessage(Message messageToSend) throws IOException {
        OutToClient.sendMessage(messageToSend);
    }

    @Override
    public Message receiveMessage() throws IOException {
        return InFromClient.receiveMessage();
    }

    @Override
    public int receiveChooseInt() throws IOException {
        chooseInt choice = (chooseInt) receiveMessage();

        return choice.getData();
    }

    @Override
    public String receiveChooseString() throws IOException {
        chooseString string = (chooseString) receiveMessage();
        return string.getData();
    }

    @Override
    public void close() throws IOException {
        OutToClient.close();
        InFromClient.close();
    }
}
