package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.server.model.*;
import java.util.*;

/**
 * This is the class that create a cli for
 * players interactions with the game
 */
public class Cli implements View {
    private final Scanner input;
    private String myNickname;

    private ClientPlayGround playGround;
    private ClientBoard myBoard;
    private ClientDeck myDeck;
    private ClientCard currentCard;

    private final ClientColour studentColour;

    public Cli()
    {
        input = new Scanner(System.in);
        studentColour = new ClientColour();
    }

    public ClientCard getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(ClientCard currentCard) {
        this.currentCard = currentCard;
    }

    public ClientColour getStudentColour() {
        return studentColour;
    }

    public String getMyNickname() {
        return myNickname;
    }

    public ClientPlayGround getPlayGround() {
        return playGround;
    }

    public void setPlayGround(ClientPlayGround playGround) {
        this.playGround = playGround;
    }

    public ClientBoard getMyBoard() {
        return myBoard;
    }

    public void setMyBoard(ClientBoard myBoard) {
        this.myBoard = myBoard;
    }


    public ClientDeck getMyDeck() {
        return myDeck;
    }

    public void setMyDeck(ClientDeck myDeck) {
        this.myDeck = myDeck;
    }

    public void setMyNickname(String myNickname) {
        this.myNickname = myNickname;
    }

    @Override
    public void printText(String text)
    {
        System.out.println(CliColour.PURPLE_BRIGHT + "> " + text + CliColour.RESET);
    }

    @Override
    public void printTextWithColour(String text, String colour)
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

    @Override
    public void printNotification(String notification)
    {
        printText(notification);
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

    @Override
    public String choseNickname()
    {
        printText("Please insert a nickname between 3 and 9 chars: ");
        setMyNickname(input.nextLine());
        while (getMyNickname().length() >= 9 || getMyNickname().length()<2)
        {
            nicknameFormatError();
            setMyNickname(input.nextLine());
        }

        return getMyNickname();
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
     * This method allows to print the number of student of each colour
     * in an array of student target given in input
     * @param target the students array (each cell is referred to a student colour
     *               and contains the number of student of that colour
     * @param playerColour the colour of the text to print, different for each player
     */
    private void printStudentsInfo(int[] target, String playerColour)
    {
        for(int index = 0; index < getStudentColour().getColourCount(); index++ )
        {
            printTextWithColour(getStudentColour().getStudentColours()[index]+"Students : " + target[index], playerColour);
        }
    }

    /**
     * This method allows to print the number of student of each colour
     * in an array of student target given in input with the default colour
     * @param target the students array (each cell is referred to a student colour
     *               and contains the number of student of that colour
     */
    private void printStudentsInfo(int[] target)
    {
        for(int index = 0; index < getStudentColour().getColourCount(); index++ )
        {
            int studentsToPrint = target[index];
            if (studentsToPrint > 0)
                printText(getStudentColour().getStudentColours()[index]+"Students : " + studentsToPrint);
        }
    }

   @Override
    public void printPlayersInfo()
    {
        int indexPlayerColour = 0;

        for (ClientPlayer p: getPlayGround().getPlayersList()) {
            if(!p.getNickname().equals(getMyNickname()))
            {
                String playerColour = CliColour.playerColours[indexPlayerColour];
                printTextWithColour("> Nickname: " + p.getNickname().toUpperCase(),playerColour);
                printTextWithColour("> Current card value: " + p.getCurrentCard().getValue(), playerColour);
                printTextWithColour("> Current MotherNature Steps: " + p.getCurrentCard().getMotherNatureSteps(), playerColour);
                printTextWithColour("> Entrance room : ",playerColour);
                printStudentsInfo(p.getPlayerBoard().getEntranceRoom(), playerColour);
                printTextWithColour("> Dining room : ", playerColour);
                printStudentsInfo(p.getPlayerBoard().getDiningRoom(), playerColour);
                printTextWithColour("> Tower Yard: ", playerColour);
                printTextWithColour(p.getPlayerBoard().getTowerYard()+"tower remained ", playerColour);
            }
            indexPlayerColour++;

        }
    }

    @Override
    public void printIslandsInfo()
    {
        int indexIsland = 1;
        for (ClientIsland island: getPlayGround().getIslands()) {
            printText("ISLAND "+indexIsland+": ");
            printStudentsInfo(island.getPlacedStudent());
            printText("Number of "+island.getTowerColour()+" tower on it: "+ island.getTowerCount());
            if(island == getPlayGround().getIslandWithMotherNature())
                printText("This Island has Mother Nature on it");
            indexIsland++;
        }
    }

    @Override
    public void printCloudTilesInfo()
    {
        int indexCloudTiles = 1;
        for(ClientCloudTiles cloudTiles : getPlayGround().getCloudTiles())
        {
            printText("CLOUD TILE "+indexCloudTiles+": ");
            printStudentsInfo(cloudTiles.getStudents());
            indexCloudTiles++;
        }
    }

    @Override
    public void printProfessorsControl()
    {
        for (int indexProfessor = 0; indexProfessor<getPlayGround().getProfessorsControl().length;indexProfessor++) {
            String nickname = getPlayGround().getProfessorsControl()[indexProfessor];
            String currentProfessor = getStudentColour().getStudentColours()[indexProfessor];
            if(nickname != null)
                printText(currentProfessor+"professor is controlled by: "+nickname);
            else
                printText(currentProfessor + "professor is not controlled by anyone");
        }
    }

    @Override
    public void printMyDeck()
    {
        printText(getMyNickname()+"'s remains assistant card: ");
        for (ClientCard card: getMyDeck().getAssistantCards()) {
            printText("Card Value: "+card.getValue()+", Mother Nature Steps: "+card.getMotherNatureSteps());
        }
    }

   @Override
    public void printMyCurrentCard()
    {
        printText(getMyNickname()+"'s current card:");
        printText("Card value: "+ getCurrentCard().getValue()+", Mother nature Steps "+ getCurrentCard().getMotherNatureSteps());
    }

    @Override
    public void printMyBoard()
    {
        printText(getMyNickname()+"'s board: ");
        printStudentsInfo(getMyBoard().getEntranceRoom());
        printStudentsInfo(getMyBoard().getDiningRoom());
        printText(getMyBoard().getTowerYard()+"remains"+getMyBoard().getTowerColour()+ "tower in the Tower Yard");

    }



    @Override
    public boolean winningView()
    {
        printText("\nCongratulations, you have won the game !");
        printText("Thanks to participate, see you soon .");
        return true;
    }

    public boolean losingView(String winner)
    {
        printText("We are sorry, you lost the game ...");
        printText("The winner is " + winner);
        printText("Join or create another game, you will be more lucky");
        return true;
    }






}
