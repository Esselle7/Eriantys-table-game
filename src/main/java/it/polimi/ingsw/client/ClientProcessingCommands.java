package it.polimi.ingsw.client;


import it.polimi.ingsw.client.connection.ConnectionClientSide;
import java.io.IOException;

/**
 * This class allows to implement a permanent
 * network listener: the aim is to have a thread that
 * continuously receive message from the network, elaborate
 * the response for all type of message and then return
 * the response to the server through the network
 */
public class ClientProcessingCommands implements Runnable {
    private final ConnectionClientSide serverConnection;
    private final View ui;

    /**
     * This is the constructor.
     *
     * @param serverConnection socket use to send and receive messages
     * @param ui the user interface chosen by the player
     */
    public ClientProcessingCommands(ConnectionClientSide serverConnection, View ui) {
        this.serverConnection = serverConnection;
        this.ui = ui;
    }

    /**
     * This method allows receiving continuously messages from the
     * server and executes in an infinite loop.
     * The loop will be killed once an IOException for connection
     * error occurred.
     */
    @Override
    public void run() {
        while (true) {
            try {
                ClientMessageImplement receivedMessage = (ClientMessageImplement) serverConnection.receiveMessage();
                receivedMessage.elaborateMessage(ui, serverConnection);
            } catch (IOException e1) {
                ui.printText("Connection lost. Closing current connection...");
                try {
                    serverConnection.close();
                } catch (IOException e2) {
                    ui.printText("Could not close. Killing program...");
                }
                System.exit(-1);
                return;
            }
        }
    }
}