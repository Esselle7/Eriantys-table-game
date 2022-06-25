package it.polimi.ingsw.server;

import it.polimi.ingsw.TextColours;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.VirtualClient.VirtualViewTCPFactory;
import it.polimi.ingsw.server.controller.Exceptions.ChooseCharacterCardException;
import it.polimi.ingsw.server.controller.TurnHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    private List<VirtualViewConnection> gamePlayers;

    /**
     * The constructor create receive a tcp factory and create
     * a thread to start the tcp listener
     * @param virtualViewTCPFactory the listener for the network socket.
     */
    public GameInstanceFactory(VirtualViewTCPFactory virtualViewTCPFactory) {
        this.virtualViewTCPFactory = virtualViewTCPFactory;
        gamePlayers = new ArrayList<>();
        Thread connectionsAccepter = new Thread(virtualViewTCPFactory);
        printConsole("THIS IS THE SERVER FOR ERYANTIS GAME");
        printConsole("WELCOME ADMIN.");
        connectionsAccepter.start();
    }

    public VirtualViewTCPFactory getVirtualViewTCPFactory() {
        return virtualViewTCPFactory;
    }

    public List<VirtualViewConnection> getGamePlayers() {
        return gamePlayers;
    }



    /**
     * This method allows to start the game generator loop. In case
     * there is an interrupt or i/o exception this method will
     * call the connection releaser
     */
    @Override
    public void run() {

        while(true) {
            try {
                instancingGameLoop();
            } catch (InterruptedException | IOException e) {
                connectionReleaser();
            }
        }
    }

    /**
     * This method allows to released connection previously added
     * to a game, so it add the connection in the blocking queue in the
     * tcp virtual view factory and then delete the game.
     */
    public synchronized void connectionReleaser()
    {
        System.out.println("> A client was disconnected!");
        int aliveConnection = 0;
        try
        {
            for (VirtualViewConnection c: getGamePlayers()
            ) {
                if(c.getInFromClient().isAlive() && c.getOutToClient().isAlive())
                {
                    aliveConnection++;
                    getVirtualViewTCPFactory().addClientConnection(c);
                    c.sendMessage(new NotificationCMI("Sorry, a client in your lobby left the game ..."));
                    c.sendMessage(new NotificationCMI("You will be add to another game asap ..."));
                }
            }
            gamePlayers = new ArrayList<>();
            printConsole(aliveConnection + " Alive Connection found and released!");
        }catch (IOException ignored)
        {}
    }


    /**
     * This method continuously create new game instances
     * until the blocking queue in virtual tcp factory is empty.
     * If the queue is empty this method will wait until it
     * can create the current game instance.
     * It also instances and launch the thread
     * that contains the turn handler to let the players
     * to play the game.
     * @throws IOException if something in sending and receive message
     * (or in take a connection from the queue) fails
     * @throws InterruptedException if something in taking a connection from the blocking queue fails (disconnection)
     */
    private void instancingGameLoop() throws InterruptedException, IOException {
        while (true) {
            gamePlayers = new ArrayList<>();
            printConsole("Creating new lobby");
            printConsole("Waiting for lobby Leader ...");
            int gameMode = setUpLobby();
            TurnHandler th = new TurnHandler(getGamePlayers(),gameMode);
            Thread gameManager = new Thread(th);
            gameManager.start();
        }
    }

    /**
     * This method allows to create a game lobby, to send
     * notification and request message to the client and to
     * receive a response.
     * @throws InterruptedException  if something in taking a connection from the blocking queue fails (disconnection)
     * @throws IOException If something in send or receiving messages fails
     */
    private int setUpLobby() throws InterruptedException, IOException
    {
        List<Integer> result=findLeader();
        int lobbySize;
        lobbySize = result.get(0);
        for(int countPlayer = 2; countPlayer <= lobbySize; countPlayer++) {
            findPlayer(countPlayer,lobbySize);
            if(countPlayer < lobbySize)
                getGamePlayers().get(countPlayer-1).sendMessage(new NotificationCMI("Waiting for remaining player ..."));
        }
        return result.get(1);
    }

    /**
     * This method allows to find a lobby leader and to request to it
     * the lobby size.
     * @return lobby sie chosen by the leader
     * @throws InterruptedException  if something in taking a connection from the blocking queue fails (disconnection)
     * @throws IOException if there are problems with the socket connection
     */
    private synchronized List<Integer> findLeader() throws InterruptedException, IOException{
        List<Integer> result = new ArrayList<>();
        getGamePlayers().add(getVirtualViewTCPFactory().getVirtualLeaderConnection());
        getGamePlayers().get(0).sendMessage(new NotificationCMI("Game found and you are the lobby leader!"));
        printConsole("Lobby leader found!");
        printConsole("Ask lobby size to lobby leader, waiting response...");
        getGamePlayers().get(0).sendMessage(new chooseLobbySizeCMI());
        int lobbySize;
        while(true)
        {
            try
            {
                lobbySize = getGamePlayers().get(0).receiveChooseInt();
                break;
            }
            catch (ChooseCharacterCardException ignored)
            {}
        }

        printConsole("Lobby size is: "+ lobbySize + " players!");
        result.add(lobbySize);
        getGamePlayers().get(0).sendMessage(new chooseExpertModeCMI());
        int gameMode;
        while(true)
        {
            try
            {
                gameMode = getGamePlayers().get(0).receiveChooseInt();
                break;
            }
            catch (ChooseCharacterCardException ignored)
            {}
        }
        result.add(gameMode);
        if(gameMode == 1)
            printConsole("Expert Mode Chosen!");
        else
            printConsole("Normal Mode Chosen!");
        getGamePlayers().get(0).sendMessage(new NotificationCMI("Waiting remaining players..."));

        return result;
    }

    /**
     * This method allows finding remaining players of the game.
     * @param player number of player to find (in this case 2 or 3)
     * @param lobbySize the current lobby size
     * @throws InterruptedException  if something in taking a connection from the blocking queue fails (disconnection)
     * @throws IOException if there are problems with the socket connection
     */
    private synchronized void findPlayer(int player,int lobbySize) throws InterruptedException, IOException {
        printConsole("Waiting for "+player+" player ...");
        getGamePlayers().add(getVirtualViewTCPFactory().getVirtualClientConnection());
        printConsole("Player "+player+" found!");
        for(int index = 0; index < player;index++)
            getGamePlayers().get(index).sendMessage(new Ping());
        getGamePlayers().get(player-1).sendMessage(new NotificationCMI("You have been assigned to a game of "+lobbySize+" players"));
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
