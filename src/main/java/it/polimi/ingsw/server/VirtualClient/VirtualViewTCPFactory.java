package it.polimi.ingsw.server.VirtualClient;


import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.chooseCreateOrAddGame;
import it.polimi.ingsw.network.messages.chooseExpertModeCMI;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class allows to store, get and add in a blocking queue
 * all the connections between the server and multiple clients.
 *In the javadoc of other method we mention this class as a factory
 * and also as a listener because this class listen for new
 * opened socket and create from the socket an element
 * with virtual view tcp dynamic type.
 */
public class VirtualViewTCPFactory implements Runnable {
    private final ServerSocket serverSocket;
    private final BlockingQueue<VirtualViewConnection> virtualClientQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<VirtualViewConnection> virtualLeaderQueue = new LinkedBlockingQueue<>();

    public VirtualViewTCPFactory(int hostPort) throws IOException {
        serverSocket = new ServerSocket(hostPort);
    }

    public BlockingQueue<VirtualViewConnection> getVirtualClientQueue() {
        return virtualClientQueue;
    }

    /**
     * This method allows to continuously check for incoming
     * connection, after receiving a connection request from a client
     * this method store the socket in a blocking queue.
     */
    @Override
    public void run() {
        while (true) {
            Socket newVirtualClient;
            try {
                newVirtualClient = serverSocket.accept();
                System.out.println("> New client connected!");
                try {
                    VirtualViewConnection newConnection = new VirtualViewTCP(newVirtualClient);
                    newConnection.sendMessage(new chooseCreateOrAddGame());
                    int leader=-1;
                    try {
                        leader = newConnection.receiveChooseInt();
                    } catch (chooseCharacterCardException ignored) {
                    }
                    if(leader == 1)
                    {
                        virtualLeaderQueue.put(newConnection);
                        newConnection.sendMessage(new NotificationCMI("Since you choice you will be added to a game as soon as possible ..."));
                    }
                    else
                        virtualClientQueue.put(newConnection);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            } catch (IOException ignored) {}

        }
    }

    /**
     * This method allows to get a connection from the queue
     * and to return it but only if after pinging the client
     * we get a response.
     * @return return a virtual client connection
     * @throws InterruptedException if the server can't ping the client
     */
    public VirtualViewConnection getVirtualClientConnection() throws InterruptedException {
        while (true) {
            try {
                VirtualViewConnection virtualClient = virtualClientQueue.take();
                virtualClient.ping();
                return virtualClient;
            } catch (IOException ignored)  {
            }
        }
    }

    /**
     * This method allows to add a virtual Client connection to
     * the blocking queue, if it wasn't previously stored in that queue
     * @param virtualClient the virtual client connection to add
     */
    public void addClientConnection(VirtualViewConnection virtualClient) {
       if(!virtualClientQueue.contains(virtualClient))
            virtualClientQueue.add(virtualClient);
    }

    /**
     * This method allows to get a connection from the leaders queue
     * and to return it but only if after pinging the client
     * we get a response.
     * @return return a virtual client connection
     * @throws InterruptedException if the server can't ping the client
     */
    public VirtualViewConnection getVirtualLeaderConnection() throws InterruptedException {
        while (true) {
            try {
                VirtualViewConnection virtualClient = virtualLeaderQueue.take();
                virtualClient.ping();
                return virtualClient;
            } catch (IOException ignored)  {
            }
        }
    }

    /**
     * This method allows to add a virtual leaders Client connection to
     * the blocking queue, if it wasn't previously stored in that queue
     * @param virtualClient the virtual client connection to add
     */
    public void addLeaderConnection(VirtualViewConnection virtualClient) {
        if(!virtualLeaderQueue.contains(virtualClient))
            virtualLeaderQueue.add(virtualClient);
    }
}
