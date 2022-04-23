package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.server.model.*;

import java.util.*;

public class Cli implements View {
    private final Scanner input;
    private String myNickname;

    private PlayGround currentGame;
    private Board myBoard;
    private List<Island> islandList;
    private List<CloudTile> cloudTileList;
    private Deck myDeck;




    public Cli()
    {
        input = new Scanner(System.in);
    }


    public void printText(String text)
    {
        System.out.println(CliColour.PURPLE_BRIGHT + "> " + text + CliColour.RESET);
    }


    public void printTextWithColour(String text, CliColour colour)
    {
        System.out.println(colour + "> " + text + CliColour.RESET);
    }

    /**
     * This method get IP of the server where the client
     * wants to connect to
     * @return The IP of the server to connect to
     */
    public String getServerAddress() {
        printText("Please insert remote Server IP:");
        return input.nextLine();
    }

    public int getServerPort(){
        printText("Please insert remote Server port:");
        return input.nextInt();
    }

    /**
     * This method type in output if the connection
     * between client and server was established
     *
     * @param isConnected True if the connection was established, false otherwise.
     */
    public void connectionOutcome(boolean isConnected) {
        if (isConnected)
            printText("Connection established. Waiting for a game...\n");
        else
            printText("Error: Server unreachable, please try again lather\n");
    }

    /**
     * This method allows the player to join an instance
     * of the game created by someone else.
     * @param numberOfPlayers The number of players of the game that
     *                        player will participate
     */
    public void joinGame(int numberOfPlayers) {
        printText("You have been assigned to a game with  " + numberOfPlayers + " players game mode\n");
    }

    public void loadView() {

        String start;
        eryantisFigure();
        printText("\nWELCOME! WE ARE GLAD TO SEE YOU. ");
        do {
            printText("-- please type START to play --");
            start = input.nextLine().toUpperCase();
        } while (!start.equals("START"));

    }

    private void eryantisFigure()
    {
        System.out.println("\n" +
                "      ___           ___           ___           ___           ___           ___                       ___     \n" +
                "     /\\  \\         /\\  \\         |\\__\\         /\\  \\         /\\__\\         /\\  \\          ___        /\\  \\    \n" +
                "    /::\\  \\       /::\\  \\        |:|  |       /::\\  \\       /::|  |        \\:\\  \\        /\\  \\      /::\\  \\   \n" +
                "   /:/\\:\\  \\     /:/\\:\\  \\       |:|  |      /:/\\:\\  \\     /:|:|  |         \\:\\  \\       \\:\\  \\    /:/\\ \\  \\  \n" +
                "  /::\\~\\:\\  \\   /::\\~\\:\\  \\      |:|__|__   /::\\~\\:\\  \\   /:/|:|  |__       /::\\  \\      /::\\__\\  _\\:\\~\\ \\  \\ \n" +
                " /:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\     /::::\\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\     /:/\\:\\__\\  __/:/\\/__/ /\\ \\:\\ \\ \\__\\\n" +
                " \\:\\~\\:\\ \\/__/ \\/_|::\\/:/  /    /:/~~/~    \\/__\\:\\/:/  / \\/__|:|/:/  /    /:/  \\/__/ /\\/:/  /    \\:\\ \\:\\ \\/__/\n" +
                "  \\:\\ \\:\\__\\      |:|::/  /    /:/  /           \\::/  /      |:/:/  /    /:/  /      \\::/__/      \\:\\ \\:\\__\\  \n" +
                "   \\:\\ \\/__/      |:|\\/__/     \\/__/            /:/  /       |::/  /     \\/__/        \\:\\__\\       \\:\\/:/  /  \n" +
                "    \\:\\__\\        |:|  |                       /:/  /        /:/  /                    \\/__/        \\::/  /   \n" +
                "     \\/__/         \\|__|                       \\/__/         \\/__/                                   \\/__/    \n");
    }

    /**
     * This method check if the nickname entered
     * is too long or empty
     */
    public void nicknameFormatError()
    {
        printText("Nickname chosen is invalid:");
        printText("you must insert nickname between 2 and 8 characters");
    }


    /**
     * This method notify the player that his nickname
     * was accepted by the server.
     */
    public void notifyValidNick()
    {
        printText("Nickname accepted");
    }

    /**
     * This method shows in output that this client
     * is creating a new game and will not join an already
     * exist game
     */
    public void createGame() {
        printText("You are creating a new game.");
    }

    /**
     * This method allows to insert the game
     * number of players
     *
     * @return The number of players decided by the creator of the game
     */
    public int askGameMode()
    {
        int numberOfPlayers;
        printText("Choose game mode between:");
        printText("2 players Game Mode");
        printText("3 players Game Mode");
        while (true) {
            try {
                numberOfPlayers = input.nextInt();
                if (numberOfPlayers != 2 && numberOfPlayers != 3)
                    printText("Please insert 2 or 3 players Game Mode.");
                else
                    return numberOfPlayers;

            } catch (InputMismatchException ex) {
                printText("Error: input not valid, type '2' or '3'");
                input.next();
            }
        }
    }

    /**
     * This method tells the player that the inserted nickname was not available.
     * This error can occur when the length of the nickname is too long or when the same nick was already chosen by another player.
     */
    public void notAvailableNickname()
    {
        if (myNickname.length() >= 9 || myNickname.length()<2)
            printText("This nickname is too long, choose a shorter one!  (Max length is 8)");
        else
            printText("Your chosen nickname is already used by an other player");

    }

    /**
     * This method print congratulations on winning the game
     *
     * @return return true to the server to disconnect client
     */
    public boolean winningView()
    {
        printText("\nCongratulations, you have won the game !");
        printText("Thanks to participate, see you soon .");
        return true;
    }

    /**
     * This method print that other player is choosing
     * the assistant card to initiate a turn
     */
    public void waitOthersChoseAssistantCard()
    {
        printText("Other players are choosing assistant cards for this game...");
    }

    /**
     * This method print all chosen assistant cards
     * for the turn
     * @param otherPlayers other nickname of the participants
     * @param currentCardValue chosen assistant card value for each player
     * @param currentCardSteps chosen assistant card mother nature steps for each player
     */
    public void otherPlayerAssistantCards(List<String> otherPlayers, int[] currentCardValue, int[] currentCardSteps)
    {
        for (String playerNickname:otherPlayers) {
            printText(playerNickname + "choose" + currentCardValue[otherPlayers.indexOf(playerNickname)] + " value assistant card with " + currentCardSteps[otherPlayers.indexOf(playerNickname)] + "mother nature steps ,");
        }
    }

    /**
     * This method print the start player
     *
     * @param startPlayer start player nickname
     */
    public void printStartPlayer(String startPlayer)
    {
        printText(startPlayer + "is the start player");
    }

    /**
     * This method advise that is other player turn
     *
     * @param currentPlayer nickname of the player that is playing his turn
     */
    public void otherPlayerTurn(String currentPlayer)
    {
        printText("Current player:" + currentPlayer);
        printText("Please wait until is you turn");
    }


    /**
     * This method displays that this client
     * lost the game and print the winner nickname
     *
     * @param winner nickname of the winner
     * @return return true to the server to disconnect client
     */
    public boolean losingView(String winner)
    {
        printText("We are sorry, you lost the game ...");
        printText("The winner is " + winner);
        printText("Join or create another game, you will be more lucky");
        return true;
    }






}
