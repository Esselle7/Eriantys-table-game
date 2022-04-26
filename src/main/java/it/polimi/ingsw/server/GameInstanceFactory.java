package it.polimi.ingsw.server;

import it.polimi.ingsw.TextColours;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.messages.NicknameCMI;
import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.LobbySizeCMI;
import it.polimi.ingsw.network.messages.chooseString;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.VirtualClient.VirtualViewTCPFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allows to instance different games lobby
 * by take the player from the first element of
 * the queue stored in virtual tcp factory (this
 * queue store all the accepted connections)
 */
public class GameInstanceFactory implements Runnable{
    private final VirtualViewTCPFactory virtualViewTCPFactory;

    /**
     * The constructor create receive a tcp factory and create
     * a thread to start the tcp listener
     * @param virtualViewTCPFactory the listener for the network socket.
     */
    public GameInstanceFactory(VirtualViewTCPFactory virtualViewTCPFactory) {
        this.virtualViewTCPFactory = virtualViewTCPFactory;
        Thread connectionsAccepter = new Thread(virtualViewTCPFactory);
        connectionsAccepter.start();
    }

    /**
     * This method allows to start the game generator loop
     */
    @Override
    public void run() {
        try {
            instancingGamesLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method continuously create new game instances
     * until the blocking queue in virtual tcp factory is empty.
     * If the queue is empty this method will wait until it
     * can create the current game instance.
     * @throws IOException if something in sending and receive message
     * (or in take a connection from the queue) fails
     */
    private void instancingGamesLoop() throws IOException {
        List<VirtualViewConnection> gamePlayers = null;
        printConsole("THIS IS THE SERVER FOR ERYANTIS GAME");
        printConsole("WELCOME ADMIN.");
        while (true) {
            printConsole("Creating new lobby");
            printConsole("Waiting for lobby Leader ...");
            try{
                gamePlayers = setUpLobby();
            }
            catch (InterruptedException | IOException e)
            {
                if(gamePlayers != null) // if there are connections in this lobby then close the connections
                    for(VirtualViewConnection c : gamePlayers)
                        c.close();
                break;
            }
        }
    }

    /**
     * This method allows to create a game lobby, to send
     * notification and request message to the client and to
     * receive a response. It also instances and launch the thread
     * that contains the turn handler to let the players
     * to play the game.
     * @return The list of client connections
     * @throws InterruptedException If something in taking elements from queue fails
     * @throws IOException If something in send or receiving messages fails
     */

    private List<VirtualViewConnection> setUpLobby() throws InterruptedException, IOException
    {
        List<VirtualViewConnection> gamePlayers = new ArrayList<>();
        List<String> users = new ArrayList<>();
        try
        {
            gamePlayers.add(virtualViewTCPFactory.getVirtualClientConnection());
            VirtualViewConnection leader = gamePlayers.get(0);
            leader.sendMessage(new NotificationCMI("You are the lobby leader!"));
            printConsole("Lobby leader found!");
            printConsole("Ask lobby size to lobby leader, waiting response...");
            leader.sendMessage(new LobbySizeCMI());
            int lobbySize = leader.receiveChooseInt();
            printConsole("Lobby size is:"+ lobbySize + "players!");
            printConsole("Waiting for Player 2 ...");
            leader.sendMessage(new NotificationCMI("Waiting remaining players..."));
            gamePlayers.add(virtualViewTCPFactory.getVirtualClientConnection());
            printConsole("Player 2 found!");
            leader.sendMessage(new NotificationCMI("Player 2 found!"));
            gamePlayers.get(1).sendMessage(new NotificationCMI("You have been assigned to a game of "+lobbySize+" players"));
            if(lobbySize == 3)
            {
                printConsole("Waiting for Player 3 ...");
                leader.sendMessage(new NotificationCMI("Waiting for Player 3 ..."));
                gamePlayers.get(1).sendMessage(new NotificationCMI("Waiting for Player 3 ..."));
                gamePlayers.add(virtualViewTCPFactory.getVirtualClientConnection());
                printConsole("Player 3 found!");
                leader.sendMessage(new NotificationCMI("Player 3 found!"));
                gamePlayers.get(1).sendMessage(new NotificationCMI("Player 3 found!"));
                gamePlayers.get(2).sendMessage(new NotificationCMI("You have been assigned to a game of "+lobbySize+" players already found"));
            }
            for(VirtualViewConnection clients : gamePlayers)
                clients.sendMessage(new NotificationCMI("Starting game ..."));
            printConsole("Creating game ...");
            for(VirtualViewConnection clients : gamePlayers)
                clients.ping();

            for(VirtualViewConnection clients : gamePlayers){
                clients.sendMessage(new NicknameCMI());
                String nickname = clients.receiveChooseString();
                while (users.contains(nickname))
                {
                    clients.sendMessage(new NotificationCMI("The nickname chosen is already taken"));
                    clients.sendMessage(new NicknameCMI());
                    nickname = clients.receiveChooseString();
                }
                clients.setNickname(nickname);
                users.add(nickname);
            }
            // qui istanzio turn handler dandogli le connection e non string di players, poi dentro turn handler mi devo quindi salvare le connection

        }catch (InterruptedException | IOException e)
        {
            throw new InterruptedException();
        }
        return gamePlayers;
    }

    /**
     * This method print the input in MAGENTA for the server side console
     * @param textToPrint the text to be printed
     */
    private void printConsole(String textToPrint)
    {
        System.out.println(TextColours.PURPLE_BRIGHT + "> "+ textToPrint);
    }
}
