package it.polimi.ingsw.server.VirtualClient;

import it.polimi.ingsw.network.connectionTCP.IncomingTCP;
import it.polimi.ingsw.network.connectionTCP.OutcomingTCP;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;

import java.io.IOException;

/**
 * This class represent a generic method to have
 * a connection between server and client. It can be
 * implemented with which network protocol you want.
 */
public interface VirtualViewConnection {

    String getNickname();

    void setNickname(String nickname);

    /**
     * This method allows to get the ingoing connection
     * @return the ingoing TCP connection
     */
    IncomingTCP getInFromClient();

    /**
     * This method allows to get the outgoing connection
     * @return the outgoing TCP connection
     */
    OutcomingTCP getOutToClient();

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
    int receiveChooseInt() throws IOException, chooseCharacterCardException;


}

