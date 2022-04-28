package it.polimi.ingsw.server.VirtualClient;


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

    public VirtualViewTCPFactory(int hostPort) throws IOException {
        serverSocket = new ServerSocket(hostPort);
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
                System.out.println("New client connected!");
                try {
                    VirtualViewConnection newConnection = new VirtualViewTCP(newVirtualClient);
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
            } catch (IOException e)  {
                System.out.println("Bad connection, delete...");
            }
        }
    }

    /**
     * This method allows to add a virtual Client connection to
     * the blocking queue
     * @param virtualClient the virtual client connection to add
     */
    public void addClientConnection(VirtualViewConnection virtualClient) {
        virtualClientQueue.add(virtualClient);
    }
}