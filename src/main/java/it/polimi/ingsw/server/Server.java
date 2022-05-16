package it.polimi.ingsw.server;

import it.polimi.ingsw.server.VirtualClient.VirtualViewTCPFactory;
import java.io.IOException;

/**
 * This is the main class of the server. This class create an instace for
 * of the class virtual view tcp factory in order to accept and manage all
 * the connection received by different client. This class also create an instance
 * of game instance factory in order to create the lobby for all the games.
 */

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        VirtualViewTCPFactory clientTCPFactory = new VirtualViewTCPFactory(5000);
        GameInstanceFactory gameInstanceFactory = new GameInstanceFactory(clientTCPFactory);
        Thread main = new Thread(gameInstanceFactory);
        main.setName("gamesGenerator");
        main.start();
        main.join(); // wait for all the other thread created

    }
}
