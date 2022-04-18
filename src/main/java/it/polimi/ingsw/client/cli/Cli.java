package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.PlayerClient;
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

        loadView();
    }

    /**
     * This method get IP of the server where the client
     * wants to connect to
     * @return The IP of the server to connect to
     */
    public String getServerAddress() {
        System.out.println("Please insert remote Server IP:");
        return input.nextLine();
    }

    /**
     * This method type in output if the connection
     * between client and server was established
     *
     * @param isConnected True if the connection was established, false otherwise.
     */
    public void connectionOutcome(boolean isConnected) {
        if (isConnected)
            System.out.println("Connection established\n");
        else
            System.out.println("Error: Server unreachable\n");
    }

    /**
     * This method allows the player to join an instance
     * of the game created by someone else.
     * @param numberOfPlayers The number of players of the game that
     *                        player will participate
     */
    public void joinGame(int numberOfPlayers) {
        System.out.print("You have been assigned to a game with  " + numberOfPlayers + " players game mode\n");
    }

    /**
     * This method shows in output that this client
     * is creating a new game and will not join an already
     * exist game
     */
    public void createGame() {
        System.out.println("You are creating a new game.");
    }

    private void loadView() {

        String start;
        eryantisFigure();
        System.out.println("\nWELCOME! WE ARE GLAD TO SE YOU. ");
        do {
            System.out.println("-- please type START to play --");
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
        System.out.print("Nickname chosen is invalid:");
        System.out.println("you must insert nickname between 2 and 8 characters");
    }


    /**
     * This method notify the player that his nickname
     * was accepted by the server.
     */
    public void notifyValidNick()
    {
        System.out.println("Nickname accepted");
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
        System.out.println("Choose game mode between:");
        System.out.println("2 players Game Mode");
        System.out.println("3 players Game Mode");
        while (true) {
            try {
                numberOfPlayers = input.nextInt();
                if (numberOfPlayers != 2 && numberOfPlayers != 3)
                    System.out.println("Please insert 2 or 3 players Game Mode.");
                else
                    return numberOfPlayers;

            } catch (InputMismatchException ex) {
                System.out.println("Error: input not valid, type '2' or '3'");
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
            System.out.println("This nickname is too long, choose a shorter one!  (Max length is 8)");
        else
            System.out.println("Your chosen nickname is already used by an other player");

    }

    /**
     * This method print congratulations on winning the game
     *
     * @return return true to the server to disconnect client
     */
    public boolean winningView()
    {
        System.out.println("\nCongratulations, you have won the game !");
        System.out.println("Thanks to participate, see you soon .");
        return true;
    }

    /**
     * This method print that other player is choosing
     * the assistant card to initiate a turn
     */
    public void waitOthersChoseAssistantCard()
    {
        System.out.print("Other players are choosing assistant cards for this game...");
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
            System.out.println(playerNickname + "choose" + currentCardValue[otherPlayers.indexOf(playerNickname)] + " value assistant card with " + currentCardSteps[otherPlayers.indexOf(playerNickname)] + "mother nature steps ,");
        }
    }

    /**
     * This method print the start player
     *
     * @param startPlayer start player nickname
     */
    public void printStartPlayer(String startPlayer)
    {
        System.out.println(startPlayer + "is the start player");
    }

    /**
     * This method advise that is other player turn
     *
     * @param currentPlayer nickname of the player that is playing his turn
     */
    public void otherPlayerTurn(String currentPlayer)
    {
        System.out.println("Current player:" + currentPlayer);
        System.out.println("Please wait until is you turn");
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
        System.out.println("We are sorry, you lost the game ...");
        System.out.println("The winner is " + winner);
        System.out.println("Join or create another game, you will be more lucky");
        return true;
    }







}
