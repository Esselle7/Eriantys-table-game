package it.polimi.ingsw.client.connection;

import it.polimi.ingsw.network.messages.Message;

import java.io.IOException;

/**
 * This interface defines the principals
 * functions that allows a Client to communicate
 * over the network using message
 */
public interface ConnectionClientSide {

    /**
     * This method allows to send a message to the server
     * in a synchronous way
     *
     * @param message the message to send
     * @throws IOException if sending the message fails
     */
    void sendMessage(Message message) throws IOException;

    /**
     * This method allows to receive a message to the server
     * in a synchronous way
     *
     * @return the received message
     * @throws IOException if the message reception fails
     */
    Message receiveMessage() throws IOException;

    /**
     * This method allows to close the connection with
     * the server
     * @throws IOException if the close fails
     */
    void close() throws IOException;
}