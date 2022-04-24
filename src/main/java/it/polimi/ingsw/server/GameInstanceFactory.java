package it.polimi.ingsw.server;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.network.messages.chooseString;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.VirtualClient.VirtualViewTCPFactory;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameInstanceFactory implements Runnable{
    private final VirtualViewTCPFactory virtualViewTCPFactory;

    public GameInstanceFactory(VirtualViewTCPFactory virtualViewTCPFactory) {
        this.virtualViewTCPFactory = virtualViewTCPFactory;
        Thread gamesGenerator = new Thread(virtualViewTCPFactory);
        gamesGenerator.start();
    }

    @Override
    public void run() {
        try {
            instancingGamesLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void instancingGamesLoop() throws IOException {
        List<VirtualViewConnection> gamePlayers = null;
        while (true) {
            printConsole("Creating new lobby");
            printConsole("Waiting for lobby Leader ...");
            try{
                gamePlayers = setUpLobby();
            }
            catch (InterruptedException | IOException e)
            {
                if(gamePlayers != null)
                    for(VirtualViewConnection c : gamePlayers)
                        c.close();
                break;
            }
        }
    }

    private List<VirtualViewConnection> setUpLobby() throws InterruptedException, IOException
    {
        List<VirtualViewConnection> gamePlayers = new ArrayList<>();
        List<String> users = new ArrayList<>();
        try
        {
            gamePlayers.add(virtualViewTCPFactory.getVirtualClientConnection());
            // qui notifico il player 0 che è il leader --> MESSAGGIO STRINGA DI NOTIFICA
            printConsole("Lobby leader found!");
            printConsole("Ask lobby size to lobby leader, waiting response...");
            int lobbySize = 0;// chiedo la lobby size a players 0 --> MESSAGGIO ASK LOBBY SIZE
            printConsole("Lobby size is:"+ lobbySize + "players!");
            printConsole("Waiting for Player 2 ...");
            // qui notifico player 0 che stiamo aspettando altri giocatori --> MESSAGGIO STRINGA DI NOTIFICA
            gamePlayers.add(virtualViewTCPFactory.getVirtualClientConnection());
            printConsole("Player 2 found! Waiting for Player 3 ...");
            // qui notifico il player 1 che è il secondo giocatore di una partita a lobbySize giocatori e che attendiamo il terzo
            // e notifico il player 0 che ho trovato il secondo giocatore
            if(lobbySize == 3)
            {
                gamePlayers.add(virtualViewTCPFactory.getVirtualClientConnection());
                // notifico player 2 che è il terzo ed ultimo giocatore e che il gioco è trovato
                // notifico tutti i giocatori che il gioco è istanziato e inizia e che tra poco sceglieranno nickname
                printConsole("Player 3 found!");
            }
            printConsole("Creating game ...");
            for(VirtualViewConnection clients : gamePlayers)
                clients.ping();

            for(VirtualViewConnection connection : gamePlayers){
                String nickname = connection.receiveChooseString();
                while (users.contains(nickname))
                {
                    // invia che non si può avere il nickname
                    nickname = connection.receiveChooseString();
                }
                connection.setNickname(nickname); // ricevi username, modifica il messaggio
                users.add(nickname);
            }
            // qui istanzio turn handler dandogli le connection e non string di players, poi dentro turn handler mi devo quindi salvare le connection

        }catch (InterruptedException | IOException e)
        {
            throw new InterruptedException();
        }
        return gamePlayers;
    }

    private void printConsole(String textToPrint)
    {
        System.out.println(SystemColor.MAGENTA + "> "+ textToPrint);
    }
}
