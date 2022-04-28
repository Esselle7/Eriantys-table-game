package it.polimi.ingsw.network.connectionTCP;


import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.Ping;

import java.io.IOException;
import java.io.ObjectOutputStream;

import static java.lang.Thread.*;

/**
 * This class will allow objects to implement a TCP
 * output stream send different message over the network.
 */
public class OutcomingTCP implements Runnable {
    private final ObjectOutputStream outputStream;

    public OutcomingTCP(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * This method allows to close the outputStream
     * @throws IOException if the connection can't be closed.
     */
    public void close() throws IOException {
        outputStream.close();
    }

    /**
     * This method continuously send ping message
     * throw the network to the IncomingTCP remote in
     * order to take alive the connection till an
     * exception occurs.
     */
    @Override
    public void run() {
        while (true) {
            try {
                synchronized (outputStream) {
                    outputStream.writeObject(new Ping());
                }
                int sleepTime = 1000;
                sleep(sleepTime);
            } catch (IOException | InterruptedException e) {
                return;
            }
        }
    }

    /**
     * This method allows to send a message over
     * the network
     * @param message the message to be sent over the network
     * @throws IOException if the message can't be sent
     */
    public void sendMessage(Message message) throws IOException {
        synchronized (outputStream) {
            outputStream.writeObject(message);
        }
    }
}
