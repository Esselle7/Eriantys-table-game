package it.polimi.ingsw.server.VirtualClient;

import it.polimi.ingsw.network.messages.Message;

import java.io.IOException;

public interface VirtualViewConnection {

    /**
     * This method allows to send ping message
     * to client
     * @throws IOException if connection lost
     */
    void ping() throws IOException;
    /**
     * This method allows to close the connection
     * @throws IOException if the method can't close the connection
     */
    void close() throws IOException;

    /**
    * This method allows to send message over the network
    * to the client
    * @param messageToSend the message to send
    * @throws IOException if the method can't send message
    */
    void sendMessage(Message messageToSend) throws IOException;

    /**
    * This method allows to receive message
    * from the client
    * @return the message received
    * @throws IOException if the method can't receive message
    */
    Message receiveMessage() throws IOException;

    /**
     * This method allows to receive string form
     * the client
     * @return the string received
     * @throws IOException if the method can't receive any data
     */
    String receiveChooseString() throws IOException;

    /**
     * This method allows to receive int chosen
     * by the client between different choices
     * @return the int chosen by the client
     * @throws IOException if the method can't receive any data
     */
    int receiveChooseInt() throws IOException;


}
