package it.polimi.ingsw.client;

import it.polimi.ingsw.client.cli.Cli;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static View ui;
   // private static int defaultPort = 5000;
   //private static String defaultAddress = "127.0.0.1";

    public static void main(String[] args) {
        Socket socket = null;
        boolean connected = false;
        chooseView();
        ui.loadView();

        while (!connected) {
            try {
                socket = new Socket(ui.getServerAddress(),ui.getServerPort());
                connected = true;
                ui.printText("Connected! Waiting for a game...");
            } catch (IOException e) {
                ui.printText("Could not connect to the server. Retrying...");
            }
        }


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
