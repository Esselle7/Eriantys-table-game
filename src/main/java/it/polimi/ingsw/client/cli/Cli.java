package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.TextColours;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.server.controller.Exceptions.chooseCharacterCardException;
import it.polimi.ingsw.server.controller.expert.CharacterCard;
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
    private final int expert;

    public Cli()
    {
        input = new Scanner(System.in);
        studentColour = new ClientColour();
        defaultColour = TextColours.PURPLE_BRIGHT;
        expert = 1;
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
    public void showNotification(String text)
    {
        printText(text);
    }

    /**
     * This method allows to print the text given in input
     * with the colour also given in input
     * @param text the text to print
     * @param colour the text colour
     */
    private void printTextWithColour(String text, String colour)
    {
        System.out.println(colour + "> " + text + TextColours.RESET);
    }

    /**
     * This method allows to print in the cli
     * the text given in input in PURPLE colour
     * @param text the text to print
     */
    private void printText(String text)
    {
        System.out.println(getDefaultColour() + "> " + text + TextColours.RESET);
    }

    /**
     * This method allows to print in a cute way
     * that the players has to perform an action
     * @param text the action to perform
     */
    private void printAction(String text)
    {
        printText("> ACTION: "+ text+ " <<");
    }

    @Override
    public boolean isDefaultServer() {
        printText("Please type 'NEW' to insert a new Server IP/PORT otherwise, if you want to go with the default settings, type 'DEF'");
        String chose;
        while(true)
        {
            chose = getInput().nextLine().toUpperCase();
            if(chose.equals("DEF"))
                return true;
            else if(chose.equals("NEW"))
                return false;
            else{
                printText("Please follow the instructions! You sent an invalid input");
            }
        }
    }



    public List<Object> getServerInfo()
    {
        List<Object> toReturn = new ArrayList<>();
        toReturn.add(getServerAddress());
        toReturn.add(getServerPort());
        return toReturn;
    }

    /**
     * This method get IP of the server where the client
     * wants to connect to
     * @return The IP of the server to connect to
     */
    private String getServerAddress() {
        printText("Please insert a remote Server IP:");
        return getInput().nextLine();
    }
    /**
     * This method allows to insert the server port
     *
     * @return The port of the server to connect to
     */
    private int getServerPort(){
        printText("Please insert a remote Server port:");
        return Integer.parseInt(getInput().nextLine());
    }

    public int wantToBeLeader()
    {
        printAction("You want to create a new game or you want to be added to an existing one? (please type LEADER or ADD TO EXISTING)");
        String input;
        while(true)
        {
            input = getInput().nextLine();
            if(input.equalsIgnoreCase("LEADER"))
                return 1;
            if(input.equalsIgnoreCase("ADD TO EXISTING"))
                return 0;
            printText("Please follow the instruction above!!");
        }
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
            printText("Connection established. Waiting...\n");
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
        printText("Invalid Nickname:");
        printText("you must insert a nickname from 2 to 8 characters long");
    }



    public int askGameMode()
    {
       // ReFreshConsole();
        int numberOfPlayers;
        printText("Choose a game mode between:");
        printText("2 players Game Mode");
        printText("3 players Game Mode");
        while (true) {
            try {
                numberOfPlayers = Integer.parseInt(getInput().nextLine());
                if (numberOfPlayers != 2 && numberOfPlayers != 3)
                    printText("Please choose between 2 or 3 players mode");
                else
                    return numberOfPlayers;

            } catch (InputMismatchException | NumberFormatException ex) {
                printText("Error: input not valid, type '2' or '3'");
                getInput().next();
            }
        }
    }

    public int chooseExpertMode()
    {
        printText("Choose your game mode between:");
        printText("EXPERT MODE (Character cards)");
        printText("NORMAL MODE");
        String choice;
        while (true) {
            choice = getInput().nextLine();
            if (choice.equalsIgnoreCase("EXPERT") || choice.equalsIgnoreCase("EXPERT MODE"))
                return 1;
            if (choice.equalsIgnoreCase("NORMAL") || choice.equalsIgnoreCase("NORMAL MODE"))
                return 0;
            else
                printText("Invalid input, please type expert or normal");
        }

    }

    public void showInfoForDecisions()
    {
        printText("------------OTHER PLAYERS' INFO------------");
        printPlayersInfo();
        printText("----------------------------------");
        System.out.println();
        printText("------------ISLAND CONTROL INFO------------");
        printIslandsInfo();
        printText("----------------------------------");
        System.out.println();
        printText("------------PROFESSOR CONTROL INFO------------");
        printProfessorsControl();
        printText("----------------------------------");
        System.out.println();
        showMyInfo();
        System.out.println();
    }

    public void showMyInfo()
    {
        printText("------------THIS IS YOUR INFO------------");
        printMyCurrentCard();
        printMyBoard();

    }
    public void showMyDeck()
    {
        printText(getMyNickname() + "'s remaining Assistant cards: ");
        for (Card card: getMyDeck().getResidualCards()) {
            printText("Card Value: "+card.getValue()+", Card Mother Nature Steps: "+card.getMotherNatureSteps());
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
                printTextWithColour("> Current Mothernature Steps: " + p.getMotherNatureSteps(), playerColour);
                printTextWithColour("------------ENTRANCE ROOM----------",playerColour);
                printStudentsInfo(p.getPlayerBoard().getEntranceRoom(),playerColour);
                printTextWithColour("------------DINING ROOM------------",playerColour);
                printStudentsInfo(p.getPlayerBoard().getDiningRoom(),playerColour);
                printTextWithColour("------------TOWER YARD-------------",playerColour);
                printTextWithColour(p.getPlayerBoard().getTowerYard() +" "+ p.getPlayerBoard().getTowerColour()+ " remaining tower(s) in the Tower Yard", playerColour);
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
            if(island.isBanned())
                printText("Influence Ban Present on this Island");
            if(island.isTowersBanned())
                printText("Towers banned from influence calculation on this island");
            if(island.getTowerColour() != null)
                printText("Number of "+island.getTowerColour()+" tower(s) on it: "+ island.getTowerCount());
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
                printStudentsInfo(cloudTile.showStudents(), getDefaultColour());
            }
            indexCloudTiles++;
            printText(" ");
        }
    }

    @Override
    public void showCharacterCardsInfo() {
        printAction("Please type the index of the Character card of your choice:");
        printText("You have "+getMyBoard().getCoins()+" coins available");
        int index = 1;
        for(CharacterCard c : getPlayGround().getDrawnCards())
        {
            printText("Character card number " + index);
            printText("Description: "+c.getDescription());
            if(c.getStudents() != null) {
                printText("The students on this card are: ");
                printStudentsInfo(c.getStudents(), TextColours.YELLOW);
            }
            printText("Price: "+c.getPrice());
            printText("");
            index++;
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
                printText(currentProfessor + " professor is controlled by: " + nickname);
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
        printText(getMyNickname()+"'s current card: ");
        printText("Card value: "+ getMyCurrentCard().getValue()+", Card Mother nature Steps "+ getMyCurrentCard().getMotherNatureSteps());
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
        printText(getMyBoard().getTowerYard() +" "+ getMyBoard().getTowerColour()+ " remaining tower(s) in the Tower Yard");
    }

    @Override
    public int chooseAssistantCard()
    {
        String in = "";
        int choice;
        printAction("Please choose an assistant card between the remaining assistant cards:");
        while(true)
        {
            try{
                in = getInput().nextLine();
                choice = Integer.parseInt(in);
                return choice;
            }catch (NumberFormatException e)
            {
                if(in.equalsIgnoreCase("CHARACTER CARD"))
                {
                    return Client.getNotAllowedInt(); // modify that name
                }
                printText("ERROR: Please type a Card number!");
            }
        }
    }

    @Override
    public int chooseStudentColourToMove()
    {
        printAction("Please select a student from your entrance room to move");
        showMyInfo();
        while(true)
        {
            String studentColour = getInput().nextLine();
            if(studentColour.equalsIgnoreCase("CHARACTER CARD"))
            {
                return Client.getNotAllowedInt(); // modify that name
            }
            for (int index = 0; index < Colour.colourCount; index ++) {
                if(getStudentColour().getStudentColours()[index].equals(studentColour.toUpperCase()))
                    return index;
            }
            printText("Invalid student colour!");
        }
    }

    public int chooseStudentColour(){
        printAction("Please select a colour: ");
        while(true)
        {
            String studentColour = getInput().nextLine();
            if(studentColour.equalsIgnoreCase("CHARACTER CARD"))
            {
                return Client.getNotAllowedInt(); // modify that name
            }
            for (int index = 0; index < Colour.colourCount; index ++) {
                if(getStudentColour().getStudentColours()[index].equals(studentColour.toUpperCase()))
                    return index;
            }
            printText("Invalid student colour!");
        }
    }

    @Override
    public int chooseIsland()
    {
        String in = "";
        printAction("Please type the Island index:");
        while(true)
        {
            try{
                in = getInput().nextLine();
                int choice = Integer.parseInt(in);
                if(choice >0 && choice<=getPlayGround().getIslands().size())
                    return  choice;
                else
                    printText("ERROR: Invalid index!");
            }catch (NumberFormatException e)
            {
                if(in.equalsIgnoreCase("CHARACTER CARD"))
                {
                    return Client.getNotAllowedInt(); // modify that name
                }

                printText("ERROR: Please type an Island number!");
            }
        }
    }

    @Override
    public int chooseYesOrNo()
    {
        String in = "";
        printAction("Please type your choice number:");
        while(true)
        {
            try{
                in = getInput().nextLine();
                int choice = Integer.parseInt(in);
                if(choice == 0 || choice == 1)
                    return  choice;
                else
                    printText("ERROR: Invalid input!");
            }catch (NumberFormatException e)
            {
                if(in.equalsIgnoreCase("CHARACTER CARD"))
                {
                    return Client.getNotAllowedInt(); // modify that name
                }

                printText("ERROR: Please type a valid number!");
            }
        }
    }

    @Override
    public int chooseCloudTile()
    {
        printAction("Please type the Cloud Tile index:");
        showCloudTilesInfo();
        String in="";
        int choice;
        while(true)
        {
            try{
                in = getInput().nextLine();
                choice = Integer.parseInt(in);
                if(choice >0 && choice<=getPlayGround().getPlayersList().size())
                    return choice;
                else
                    printText("ERROR: Invalid index!");
            }catch (NumberFormatException e)
            {
                if(in.equalsIgnoreCase("CHARACTER CARD"))
                {
                    return Client.getNotAllowedInt(); // modify that name
                }
                printText("ERROR: Please type a Cloud Tile number!");
            }
        }

    }

    @Override
    public int chooseWhereToMove()
    {
        printAction("You want to move the selected student to the Dining Room or to an Island?");
        while(true)
        {
            String choice = getInput().nextLine();
            if(choice.equalsIgnoreCase("CHARACTER CARD"))
            {
                return Client.getNotAllowedInt(); // modify that name
            }
            else if(choice.equalsIgnoreCase("DINING") || choice.equalsIgnoreCase("DINING ROOM"))
                return 0;
            else if(choice.equalsIgnoreCase("ISLAND"))
                return 1;

            printText("ERROR: Please type 'DINING ROOM' or 'ISLAND'");
        }

    }

    @Override
    public int chooseCharacterCard()
    {
        if(getPlayGround().getGameMode() != expert)
        {
            printText("You cannot access the expert mode menu because you're not playing in expert mode!");
            return Client.getNotAllowedInt();
        }
        printText("-------------------CHARACTER CARD MENU--------------");
        printText("*Type BACK to exit menu");
        boolean enoughCoins = false;
        for (CharacterCard c: getPlayGround().getDrawnCards()
             ) {
            if (c.getPrice() <= getMyBoard().getCoins()) {
                enoughCoins = true;
                break;
            }
        }
        if(!enoughCoins)
        {
            printText("You don't have enough coins to buy a card, please try again later...");
            return Client.getNotAllowedInt();
        }
        showCharacterCardsInfo();
        while(true)
        {
            String in = getInput().nextLine();
            if(in.equalsIgnoreCase("BACK"))
            {
                printText("Exiting character card menu...");
                return -1;
            }
            try {
                int card = Integer.parseInt(in);
                if (card < getPlayGround().getDrawnCards().size() + 1 && card > 0) {
                    if (getPlayGround().getDrawnCards().get(card - 1).getPrice() <= getMyBoard().getCoins())
                        return card;
                    else
                        printText("Please choose a character card you can afford!");
                } else
                    throw new NumberFormatException();
            } catch (NumberFormatException e){
                printText("Please choose a valid character card index!");
            }
        }

    }

    @Override
    public void update(Board myBoardNew, Deck myDeckNew, Card myCurrentCardNew,int myCoins)
    {
        setMyBoard(myBoardNew);
        setMyCurrentCard(myCurrentCardNew);
        setMyDeck(myDeckNew);
        getMyBoard().setCoins(myCoins);
    }


    @Override
    public void update(Object playGroundNew)
    {
        PlayGround playGroundUpdate = (PlayGround) playGroundNew;
        setPlayGround(playGroundUpdate);
        for (Player p: playGroundUpdate.getPlayersList()) {
            if(p.getNickname().equals(getMyNickname()))
            {
                update(p.getPlayerBoard(),p.getAssistantCards(),p.getCurrentCard(),p.getPlayerBoard().getCoins());
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
            printText("Congratulations, you won the game !");
            printText("Thanks for playing, see you soon...");
        }
        else
        {
            printText("Unfortunately you lost the game, maybe next time!");
            printText("The winner is " + winner);
            printText("Join or create another game, you'll be luckier");
        }
    }






}
