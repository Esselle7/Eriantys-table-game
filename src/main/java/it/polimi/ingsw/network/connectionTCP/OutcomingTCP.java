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
    private boolean alive;

    public OutcomingTCP(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
        alive = true;
    }


    /**
     * This method allows to close the outputStream
     * @throws IOException if the connection can't be closed.
     */
    public void close() throws IOException {
        setNotAlive();
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
                    outputStream.reset();
                }
                int sleepTime = 1000;
                Thread.sleep(sleepTime);
            } catch (IOException | InterruptedException e) {
               setNotAlive();
               System.out.println("Warning: there is a Client unreachable by Ping message... We will fix later");
               return;
            }
        }
    }

    /**
     * Verify if the outgoing connection
     * @return true if the outgoing connection is alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Set the outgoing connection to die
     */
    public void setNotAlive() {
        alive = false;
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
            outputStream.reset();
        }
    }
}
