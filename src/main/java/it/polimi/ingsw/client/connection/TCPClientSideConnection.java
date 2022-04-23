package it.polimi.ingsw.client.connection;

import it.polimi.ingsw.network.connectionTCP.IncomingTCP;
import it.polimi.ingsw.network.connectionTCP.OutcomingTCP;
import it.polimi.ingsw.network.messages.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class allows to implement the connection in outgoing
 * and incoming to/from the server by using TCP protocol.
 * The idea under this implementation is to have two different queue
 * manage by two different thread that stores all the message (to send
 * and to receive)
 */
public class TCPClientSideConnection implements ConnectionClientSide{
    private final IncomingTCP InFromServer;
    private final OutcomingTCP OutToServer;

    /**
     * Constructor for creating the connection between
     * client and server
     * @param socket the socket on the server provided
     * @throws IOException if a connection error occurs
     */
    public TCPClientSideConnection(final Socket socket) throws IOException {
        OutToServer = new OutcomingTCP(new ObjectOutputStream(socket.getOutputStream()));
        InFromServer = new IncomingTCP(new ObjectInputStream(socket.getInputStream()));
        new Thread(OutToServer).start();
        new Thread(InFromServer).start();
    }

    @Override
    public void close() throws IOException {
        OutToServer.close();
        InFromServer.close();
    }

    @Override
    public void sendMessage(final Message messageToSend) throws IOException {
        OutToServer.sendMessage(messageToSend);
    }

    @Override
    public Message receiveMessage() throws IOException {
        return InFromServer.receiveMessage();
    }
}
