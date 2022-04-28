package it.polimi.ingsw.client;

import it.polimi.ingsw.client.cli.Cli;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import it.polimi.ingsw.client.connection.TCPClientSideConnection;import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class implement a client. It allows: choosing which
 * user interface the player want to use, creating a TCP connection
 * between this and server, setup message listener to listen
 * for new message from the server and elaborating that messages.
 */
public class Client{

    private static View ui;

    public static void main(String[] args){
        Socket socket = null;
        boolean connected = false;

        chooseView();
        getUi().loadView();
        while (!connected) {
            try {
                if(getUi().isDefaultServer())
                    socket = new Socket(getDefaultAddress(),getDefaultPort());
                else
                    socket = new Socket(getUi().getServerAddress(),getUi().getServerPort());
                connected = true;
                getUi().connectionOutcome(connected);
            } catch (IOException e) {
                getUi().connectionOutcome(connected);
                System.exit(-1);
            }
        }

        ConnectionClientSide currentConnection;
        ClientProcessingCommands clientProcessingCommands = null;
        try {
            currentConnection = new TCPClientSideConnection(socket);
            clientProcessingCommands = new ClientProcessingCommands(currentConnection, ui);
        } catch (IOException e) {
            getUi().printText("Error connecting to the server. Please try again later.");
            System.exit(-1);
        }
        Thread messageListener = new Thread(clientProcessingCommands);
        messageListener.start();
    }

    public static View getUi() {
        return ui;
    }

    public static int getDefaultPort() {
        return 5000;
    }

    public static String getDefaultAddress() {
        return "127.0.0.1";
    }

    /**
     * This method allows the user to choose
     * which user interface they want to use
     */
    private static void chooseView() {
        ui = null;
        String choosenView;
        Scanner input = new Scanner(System.in);
        System.out.println("Type which user interface you want to use (CLI or GUI)");
        do {
            System.out.println("> ");
            choosenView = input.nextLine();
            if (choosenView.equalsIgnoreCase("CLI")) {
                ui = new Cli();
            } else
                System.out.println("Invalid input.\n");
        }while(ui == null);
    }

}
