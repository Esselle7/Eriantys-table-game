package it.polimi.ingsw.client;

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
     void printTextWithColour(String text, String colour);

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
     * This method allows to print info about all the player to allows
     * current player to take decisions about his turn
     */
    void printPlayersInfo();

    /**
     * This method allows to print all the students on each island
     * and the number of tower (and the colour) of each island
     * (remember that if I unify two island they will be considered as
     * an only one island with tower count equals to 2)
     */
    void printIslandsInfo();

    /**
     * This method allows to print all the students in each cloud tiles
     */
    void printCloudTilesInfo();

    /**
     * This method allows to print all the professor controller
     */
    void printProfessorsControl();

    /**
     * This method allows to print the remaining assistan card
     * in the player deck
     */
    void printMyDeck();

    /**
     * This method allows to print the current card
     * of the player
     */
    void printMyCurrentCard();

    /**
     * This method allows to print the board of the player
     */
    void printMyBoard();


    /**
     * This method print congratulations on winning the game
     *
     * @return return true to the server to disconnect client
     */
    boolean winningView();

    /**
     * This method displays that this client
     * lost the game and print the winner nickname
     *
     * @param winner nickname of the winner
     * @return return true to the server to disconnect client
     */
    boolean losingView(String winner);

}