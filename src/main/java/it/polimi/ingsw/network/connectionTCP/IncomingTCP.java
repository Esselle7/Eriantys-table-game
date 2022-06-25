package it.polimi.ingsw.network.connectionTCP;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.Ping;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * This class will allow objects to implement a TCP
 * input stream to handle different message from the
 * network. This class store all the received messages
 * in a queue ready to be process.
 */
public class IncomingTCP implements Runnable {
    private final BlockingQueue<Message> inputQueue = new LinkedBlockingQueue<>();
    private final ObjectInputStream inputStream;
    private Instant previousTimestamp = Instant.now();
    private final Object timestampLock = new Object();
    private boolean alive;

    public IncomingTCP(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
        alive = true;
    }

    /**
     * Verify if the incoming connection
     * @return true if the incoming connection is alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Set the incoming connection to die
     */
    public void setNotAlive() {
        alive = false;
    }
    /**
     * This method allows to close the inputStream
     * @throws IOException if the connection can't be closed
     */
    public void close() throws IOException {
        setNotAlive();
        inputStream.close();
    }

    /**
     * This method allows to read all messages from the network
     * untill an exception occur. If the message type is a
     * ping message then this method stores the time stamp
     * of the ping received.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Message message = (Message) inputStream.readObject();
                if (message instanceof Ping)
                    synchronized (timestampLock) {
                        previousTimestamp = Instant.now();
                    }
                else
                    inputQueue.add(message);
            } catch (ClassNotFoundException | IOException e) {
                previousTimestamp = Instant.EPOCH;
                setNotAlive();
                return;
            }
        }
    }

    /**
     * This method allows to take a message from
     * the queue in order to elaborate it
     *
     * @return the first message in the InputQueue
     * @throws IOException if there is an interrupt or
     *                      because the time elapsed between
     *                      now and the previous ping is too long
     */
    public Message receiveMessage() throws IOException {
        try {
            Message message;
            do {
                synchronized (timestampLock) {
                    if (Duration.between(previousTimestamp, Instant.now()).toMillis() > 1100)
                        throw new IOException();
                }
                message = inputQueue.poll(150, TimeUnit.MILLISECONDS);
            } while (message == null);
            return message;
        } catch (InterruptedException e) {
            setNotAlive();
            throw new IOException();
        }
    }
}