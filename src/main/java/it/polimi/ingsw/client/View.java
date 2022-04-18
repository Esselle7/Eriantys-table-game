package it.polimi.ingsw.client;

import java.util.List;

/**
 * Interface that defines the methods of the interfaces (CLI and GUI)
 * that need to be implemented to guarantee a correct game flow.
 */
public interface View {


    /**
     * This method allows to insert the server IP
     *
     * @return The IP of the server to connect to
     */
    String getServerAddress();


    /**
     * This method allows to join a created game
     * by inserting the number of players
     * @param numberOfPlayers The number of players of the game the player has been assigned to
     */
    void joinGame(int numberOfPlayers);


    /**
     * This method allows a player to create
     * a new game
     */
    void createGame();


    /**
     * This method check if the connection to the server was successful
     * established
     *
     * @param isConnected True if the connection was established, false otherwise
     */
    void connectionOutcome(boolean isConnected);


    /**
     * This method check if the nickname entered
     * is too long or empty
     */
    void nicknameFormatError();


    /**
     * This method notify the player that his nickname
     * was accepted by the server.
     */
    void notifyValidNick();



    /**
     * This method allows to insert the game
     * number of players
     *
     * @return The number of players decided by the creator of the game
     */
    int askGameMode();


    /**
     * This method tells the player that the inserted nickname was not available.
     * This error can occur when the length of the nickname is too long or when the same nick was already chosen by another player.
     */
    void notAvailableNickname();


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