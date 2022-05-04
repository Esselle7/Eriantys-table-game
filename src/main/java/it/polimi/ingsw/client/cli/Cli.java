package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.TextColours;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.server.model.*;

import java.io.IOException;
import java.util.*;

/**
 * This is the class that create a cli for
 * players interactions with the game
 */
public class Cli implements View {
    private final Scanner input;
    private String myNickname = null;
    private final ClientColour studentColour;

    private PlayGround playGround = null;
    private Board myBoard = null;
    private Deck myDeck = null;
    private Card myCurrentCard = null;
    private final String defaultColour;

    public Cli()
    {
        input = new Scanner(System.in);
        studentColour = new ClientColour();
        defaultColour = TextColours.PURPLE_BRIGHT;
    }

    public String getDefaultColour() {
        return defaultColour;
    }

    public Scanner getInput() {
        return input;
    }

    @Override
    public Card getMyCurrentCard() {
        return myCurrentCard;
    }

    @Override
    public void setMyCurrentCard(Card myCurrentCard) {
        this.myCurrentCard = myCurrentCard;
    }


    public ClientColour getStudentColour() {
        return studentColour;
    }

    @Override
    public String getMyNickname() {
        return myNickname;
    }

    @Override
    public PlayGround getPlayGround() {
        return playGround;
    }

    @Override
    public void setPlayGround(PlayGround playGround) {
        this.playGround = playGround;
    }

    @Override
    public Board getMyBoard() {
        return myBoard;
    }

    @Override
    public void setMyBoard(Board myBoard) {
        this.myBoard = myBoard;
    }

    @Override
    public Deck getMyDeck() {
        return myDeck;
    }

    @Override
    public void setMyDeck(Deck myDeck) {
        this.myDeck = myDeck;
    }

    @Override
    public void setMyNickname(String myNickname) {
        this.myNickname = myNickname;
    }

    @Override
    public void printText(String text)
    {
        System.out.println(getDefaultColour() + "> " + text + TextColours.RESET);
    }

    @Override
    public void printTextWithColour(String text, String colour)
    {
        System.out.println(colour + "> " + text + TextColours.RESET);
    }

    @Override
    public boolean isDefaultServer() {
        printText("Please type 'NEW' to insert new Server IP/PORT, else type 'DEF'");
        String chose;
        while(true)
        {
            chose = getInput().nextLine().toUpperCase();
            if(chose.equals("DEF"))
                return true;
            else if(chose.equals("NEW"))
                return false;
            else{
                printText("Please follow the instructions! You insert an invalid input");
            }
        }
    }

    public String getServerAddress() {
        printText("Please insert remote Server IP:");
        return getInput().nextLine();
    }

    public int getServerPort(){
        printText("Please insert remote Server port:");
        return Integer.parseInt(getInput().nextLine());
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
        printText("Please insert a nickname: ");
        String nickname = null;
        while(nickname == null || nickname.equals("") || nickname.equals(" "))
            nickname = getInput().nextLine();
        setMyNickname(nickname);
        return getMyNickname();
    }

    public void loadView() {

        String start;
        eryantisFigure();
        printText("\nWELCOME! WE ARE GLAD TO SEE YOU. ");
        do {
            printText("-- please type START to play --");
            start = getInput().nextLine().toUpperCase();
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
       // ReFreshConsole();
        int numberOfPlayers;
        printText("Choose game mode between:");
        printText("2 players Game Mode");
        printText("3 players Game Mode");
        while (true) {
            try {
                numberOfPlayers = Integer.parseInt(getInput().nextLine());
                if (numberOfPlayers != 2 && numberOfPlayers != 3)
                    printText("Please insert 2 or 3 players Game Mode.");
                else
                    return numberOfPlayers;

            } catch (InputMismatchException ex) {
                printText("Error: input not valid, type '2' or '3'");
                getInput().next();
            }
        }
    }

    public void showInfoForDecisions()
    {
        printText("------------OTHERS PLAYER INFO------------");
        printPlayersInfo();
        printText("----------------------------------");
        System.out.println();
        printText("------------ISLANDS INFO------------");
        printIslandsInfo();
        printText("----------------------------------");
        System.out.println();
        printText("------------PROFESSORS CONTROL INFO------------");
        printProfessorsControl();
        printText("----------------------------------");
        System.out.println();
    }

    public void showMyInfo()
    {
        printText("------------THESE ARE YOUR INFO------------");
        printMyCurrentCard();
        printMyBoard();

    }
    public void showMyDeck()
    {
        printText(getMyNickname()+"'s remains assistant card: ");
        for (Card card: getMyDeck().getResidualCards()) {
            printText("Card Value: "+card.getValue()+", Mother Nature Steps: "+card.getMotherNatureSteps());
        }
    }


    /**
     * This method allows to print the number of student of each colour
     * in an array of student target given in input with the default colour
     * @param target the students array (each cell is referred to a student colour
     *               and contains the number of student of that colour
     */
    private void printStudentsInfo(int[] target, String colour)
    {
        boolean printed = false;
        for(int index = 0; index < getStudentColour().getColourCount(); index++ )
        {
            int studentsToPrint = target[index];
            if (studentsToPrint > 0)
            {
                printed = true;
                printTextWithColour(getStudentColour().getStudentColours()[index]+"Students : " + studentsToPrint,TextColours.studentColours[index]);

            }
        }
        if(!printed)
            printTextWithColour("** Empty **", colour);
    }

    /**
     * This method allows to print info about all the player to allows
     * current player to take decisions about his turn
     */
    private void printPlayersInfo()
    {
        int indexPlayerColour = 0;

        for (Player p: getPlayGround().getPlayersList()) {
            if(!p.getNickname().equals(getMyNickname()))
            {
                String playerColour = TextColours.playerColours[indexPlayerColour];
                printTextWithColour("> Nickname: " + p.getNickname().toUpperCase(),playerColour);
                printTextWithColour("> Current card value: " + p.getCurrentCard().getValue(), playerColour);
                printTextWithColour("> Current MotherNature Steps: " + p.getCurrentCard().getMotherNatureSteps(), playerColour);
                printTextWithColour("------------ENTRANCE ROOM----------",playerColour);
                printStudentsInfo(p.getPlayerBoard().getEntranceRoom(),playerColour);
                printTextWithColour("------------DINING ROOM------------",playerColour);
                printStudentsInfo(p.getPlayerBoard().getDiningRoom(),playerColour);
                printTextWithColour("------------TOWER YARD-------------",playerColour);
                printTextWithColour(p.getPlayerBoard().getTowerYard()+" remains "+p.getPlayerBoard().getTowerColour()+ " tower in the Tower Yard", playerColour);
            }
            indexPlayerColour++;

        }
    }

    /**
     * This method allows to print all the students on each island
     * and the number of tower (and the colour) of each island
     * (remember that if I unify two island they will be considered as
     * an only one island with tower count equals to 2)
     */
    private void printIslandsInfo()
    {
        int indexIsland = 1;
        for (Island island: getPlayGround().getIslands()) {
            printText("ISLAND "+indexIsland+": ");
            printStudentsInfo(island.getPlacedStudent(),getDefaultColour());
            if(island.getTowerColour() != null)
                printText("Number of "+island.getTowerColour()+" tower on it: "+ island.getTowerCount());
            else
                printText("No tower on this island");
            if(island == getPlayGround().getIslandWithMotherNature())
                printText("This Island has Mother Nature on it");
            indexIsland++;
            System.out.println();
        }
    }


    public void showCloudTilesInfo()
    {
        int indexCloudTiles = 1;
        for(CloudTile cloudTile : getPlayGround().getCloudTiles())
        {
            printText("CLOUD TILE "+indexCloudTiles+": ");
            if(cloudTile.isUsed())
                printText("Already used...");
            else
            {
                printStudentsInfo(cloudTile.getStudents(),getDefaultColour());
                indexCloudTiles++;
            }
        }
    }

    /**
     * This method allows to print all the professor controller
     */
    private void printProfessorsControl()
    {
        for (int indexProfessor = 0; indexProfessor<getPlayGround().getProfessorsControl().length;indexProfessor++) {
            String nickname = getPlayGround().getProfessorsControl()[indexProfessor];
            String currentProfessor = getStudentColour().getStudentColours()[indexProfessor];
            if(nickname != null)
                printText(currentProfessor+" professor is controlled by: "+nickname);
            else
                printText(currentProfessor + " professor is not controlled by anyone");
        }
    }



    /**
     * This method allows to print the current card
     * of the player
     */
    private void printMyCurrentCard()
    {
        printText(getMyNickname()+"'s current card:");
        printText("Card value: "+ getMyCurrentCard().getValue()+", Mother nature Steps "+ getMyCurrentCard().getMotherNatureSteps());
    }

    /**
     * This method allows to print the board of the player
     */
    private void printMyBoard()
    {
        printText(getMyNickname()+"'s board: ");
        printText("------------MY ENTRANCE ROOM----------");
        printStudentsInfo(getMyBoard().getEntranceRoom(),getDefaultColour());
        printText("------------MY DINING ROOM------------");
        printStudentsInfo(getMyBoard().getDiningRoom(),getDefaultColour());
        printText("------------MY TOWER YARD-------------");
        printText(getMyBoard().getTowerYard()+" remains"+getMyBoard().getTowerColour()+ " tower in the Tower Yard");
    }

    @Override
    public int chooseAssistantCard()
    {
        printText("Please choose an assistant card between the remains assistant cards:");
        return Integer.parseInt(getInput().nextLine());

    }

    @Override
    public int chooseStudentColourToMove()
    {
        printText("Please select a student from your entrance room to move");
        showMyInfo();
        String studentColour = getInput().nextLine();
        for (int index = 0; index < Colour.colourCount; index ++) {
            if(getStudentColour().getStudentColours()[index].equals(studentColour.toUpperCase()))
                return index;
        }
        return 0;
    }

    @Override
    public int chooseIsland()
    {
        printText("Please type the Island index where you want to move:");
        return  Integer.parseInt(getInput().nextLine());
    }

    @Override
    public int chooseCloudTile()
    {
        printText("Please type the Cloud Tile index:");
        showCloudTilesInfo();
        return Integer.parseInt(getInput().nextLine());

    }

    @Override
    public int chooseWhereToMove()
    {
        printText("You want to move the selected student to the Dining Room or to an Island?");
        String choice = getInput().nextLine();
        while(true)
        {
            if(choice.equalsIgnoreCase("DINING") || choice.equalsIgnoreCase("DINING ROOM"))
                return 0;
            else if(choice.equalsIgnoreCase("ISLAND"))
                return 1;
            else
                printText("Please type 'DINING ROOM' or 'ISLAND'");
        }

    }

    @Override
    public void update(Board myBoardNew, Deck myDeckNew, Card myCurrentCardNew)
    {
        setMyBoard(myBoardNew);
        setMyCurrentCard(myCurrentCardNew);
        setMyDeck(myDeckNew);

    }

    @Override
    public void update(Object playGroundNew)
    {

        PlayGround playGroundUpdate = (PlayGround) playGroundNew;
        setPlayGround(playGroundUpdate);
        for (Player p: playGroundUpdate.getPlayersList()) {
            if(p.getNickname().equals(getMyNickname()))
            {
                update(p.getPlayerBoard(),p.getAssistantCards(),p.getCurrentCard());
                break;
            }
        }

    }

    private void ReFreshConsole()
    {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {}

        eryantisFigure();
        printText("--------------------GAME INFO--------------------------");
        showInfoForDecisions();
        printText("--------------------PERSONAL INFO----------------------");
        showMyInfo();


    }

    @Override
    public void displayWinner(String winner)
    {
        if(winner.equals(getMyNickname()))
        {
            printText("Congratulations, you have won the game !");
            printText("Thanks to participate, see you soon .");
        }
        else
        {
            printText("We are sorry, you lost the game ...");
            printText("The winner is " + winner);
            printText("Join or create another game, you will be more lucky");
        }
    }






}
