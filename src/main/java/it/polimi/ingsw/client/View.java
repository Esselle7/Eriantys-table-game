package it.polimi.ingsw.client;

import it.polimi.ingsw.server.model.*;

/**
 * Interface that defines the methods of the interfaces (CLI and GUI)
 * that need to be implemented to guarantee a correct game flow.
 */
public interface View {

    Card getMyCurrentCard();

    void setMyCurrentCard(Card currentCard);

    String getMyNickname();

    PlayGround getPlayGround();

    void setPlayGround(PlayGround playGround);

    Board getMyBoard();

    void setMyBoard(Board myBoard);

    Deck getMyDeck();

    void setMyDeck(Deck myDeck);

    void setMyNickname(String myNickname);

    /**
     * This method allows to show the player a notification
     * @param text the notification to show (as a string)
     */
    void showNotification(String text);

    /**
     * This method request to the client if it want to
     * connect to the default server (127.0.0.1/50) or if
     * it wants to insert new ip/port
     */
    boolean isDefaultServer();

    /**
     * This method get IP of the server where the client
     * wants to connect to
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
     * This method allows to show info about the player that uses that cli
     */
    void showMyInfo();

    /**
     * This method allows to print the remaining assistant card
     * in the player deck
     */
    void showMyDeck();

    /**
     * This method allows to print all the students in each cloud tiles
     */
    void showCloudTilesInfo();

    /**
     * This method allows to print al necessary information for player to
     * play a turn
     */
    void showInfoForDecisions();

    /**
     * This method allows to choose an assistant card for the turn
     * @return the value of the card to use
     *
     */
    int chooseAssistantCard();

    /**
     * This method allows to choose which student the player
     * want to move from the entrance room
     * @return the colour corresponding int to the colour chosen
     */
    int chooseStudentColourToMove();

    /**
     * This method allows the player to choose an island in order
     * to move a student or mother nature on it
     * @return the index of the selected island
     */
    int chooseIsland();

    /**
     * This method allows to choose a cloud tile from
     * the play ground
     * @return the index of the selected cloud tile
     */
    int chooseCloudTile();

    /**
     * This method allows the player to choose where to place
     * a student (if in the dining room or in an island)
     * @return 0 if island, 1 if dining
     */
    int chooseWhereToMove();

    /**
     * This method allows to update
     * model in Client
     * @param myBoardNew updated client board
     * @param myDeckNew updated client deck
     * @param myCurrentCardNew updated client current card
     */
    void update(Board myBoardNew, Deck myDeckNew, Card myCurrentCardNew);

    /**
     * This method allows to update
     * model in Client
     * @param playGroundNew updated playground with all
     *                      information about the game
     */
    void update(Object playGroundNew);


    /**
     * This method displays the game winner
     * @param winner nickname of the winner
     */
    void displayWinner(String winner);

}