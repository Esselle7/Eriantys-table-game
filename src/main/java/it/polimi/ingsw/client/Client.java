package it.polimi.ingsw.client;

import it.polimi.ingsw.client.cli.Cli;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import it.polimi.ingsw.client.connection.TCPClientSideConnection;
import it.polimi.ingsw.client.gui.Gui;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * This class implement a client. It allows: choosing which
 * user interface the player want to use, creating a TCP connection
 * between this and server, setup message listener to listen
 * for new message from the server and elaborating that messages.
 */
public class Client{

    private static View ui;
    private static int notAllowedInt;
    private static String notAllowedString;

    public static void main(String[] args){
        Socket socket = null;
        boolean connected = false;
        notAllowedInt = -1;
        notAllowedString = "none";

        chooseView();
        getUi().loadView();
        while (!connected) {
            try {
                if(getUi().isDefaultServer())
                    socket = new Socket(getDefaultAddress(),getDefaultPort());
                else
                {
                    List<Object> serverInfo = getUi().getServerInfo();
                    socket = new Socket((String) serverInfo.get(0), (Integer) serverInfo.get(1));
                }

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
            getUi().showNotification("Error connecting to the server. Please try again later.");
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

    public static int getNotAllowedInt() {
        return notAllowedInt;
    }

    public static String getNotAllowedString() {
        return notAllowedString;
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
            } else if(choosenView.equalsIgnoreCase("GUI"))
                ui = new Gui();
            else
                System.out.println("Invalid input.\n");
        }while(ui == null);
    }

}
