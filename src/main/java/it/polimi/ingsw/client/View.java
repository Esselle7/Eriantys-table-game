package it.polimi.ingsw.client;

import it.polimi.ingsw.client.cli.CliColour;
import java.util.List;

/**
 * Interface that defines the methods of the interfaces (CLI and GUI)
 * that need to be implemented to guarantee a correct game flow.
 */
public interface View {


    /**
     * This method allows to print in the cli
     * the text given in input in PURPLE colour
     * @param text the text to print
     */
    void printText(String text);


    /**
     * This method allows to print the text given in input
     * with the colour also given in input
     * @param text the text to print
     * @param colour the text colour
     */
     void printTextWithColour(String text, CliColour colour);

    /**
     * This method allows to insert the server IP
     *
     * @return The IP of the server to connect to
     */
    String getServerAddress();

    /**
     * This method allows to insert the server port
     *
     * @return The port of the server to connect to
     */
    int getServerPort();

    /**
     * This method allows to communicate to the
     * client a notification
     */
    void printNotification(String notification);


    /**
     * This is a "welcome" method to the user
     */
    void loadView();


    /**
     * This method check if the connection to the server was successful
     * established
     *
     * @param isConnected True if the connection was established, false otherwise
     */
    void connectionOutcome(boolean isConnected);

    /**
     * This method allows to choose a valid nickname for the game
     */
    String choseNickname();

    /**
     * This method check if the nickname entered
     * is too long or empty
     */
    void nicknameFormatError();


    /**
     * This method allows to insert the game
     * number of players
     *
     * @return The number of players decided by the creator of the game
     */
    int askGameMode();


    /**
     * This method print congratulations on winning the game
     *
     * @return return true to the server to disconnect client
     */
    boolean winningView();


    /**
     * This method print that other player is choosing
     * the assistant card to initiate a turn
     */
    void waitOthersChoseAssistantCard();

    /**
     * This method print all chosen assistant cards
     * for the turn
     * @param otherPlayers other nickname of the participants
     * @param currentCardValue chosen assistant card value for each player
     * @param currentCardSteps chosen assistant card mother nature steps for each player
     */
    void otherPlayerAssistantCards(List<String> otherPlayers, int[] currentCardValue, int[] currentCardSteps);



    /**
     * This method print the start player
     *
     * @param startPlayer start player nickname
     */
    void printStartPlayer(String startPlayer);




    /**
     * This method advise that is other player turn
     *
     * @param currentPlayer nickname of the player that is playing his turn
     */
    void otherPlayerTurn(String currentPlayer);


    /**
     * This method displays that this client
     * lost the game and print the winner nickname
     *
     * @param winner nickname of the winner
     * @return return true to the server to disconnect client
     */
    boolean losingView(String winner);

}