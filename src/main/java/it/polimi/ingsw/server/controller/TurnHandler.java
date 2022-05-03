package it.polimi.ingsw.server.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import it.polimi.ingsw.TextColours;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.server.ControllerViewObserver;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.controller.Exceptions.*;
import it.polimi.ingsw.server.model.*;
import java.util.List;

public class TurnHandler extends ControllerViewObserver implements Runnable {
    private Player CurrentPlayer;
    private VirtualViewConnection currentClient;

    private final GameMoves gameMoves;
    private List <Player> playerOrder = null;
    private boolean gameOn;
    private boolean lastTurn = false;
    private String winner = null;

    private final List<VirtualViewConnection> gamePlayers;

    private void setWinner(String playerNickname){ winner = playerNickname; }

    private String getWinner(){ return winner; }

    private void setGameOn(boolean game){ gameOn = game;}

    private boolean getGame(){ return gameOn; }

    private void endGame(){ gameOn = false;}

    public boolean getLastTurn(){ return lastTurn;}

    public void setLastTurn(boolean lastTurn){ this.lastTurn = lastTurn;}

    public TurnHandler(List<VirtualViewConnection> gamePlayers){
        gameOn = true;
        this.gamePlayers = gamePlayers;
        gameMoves = new GameMoves();
    }


    public List<VirtualViewConnection> getGamePlayers() {
        return gamePlayers;
    }

    private void setPlayerOrder(List <Player> newPlayerOrder){this.playerOrder = newPlayerOrder;}

    private List <Player> getPlayerOrder(){return playerOrder;}

    public GameMoves getGameMoves() {
        return gameMoves;
    }

    public Player getCurrentPlayer() {
        return CurrentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        CurrentPlayer = currentPlayer;
        currentClient = currentPlayer.getClient();
        getGameMoves().setCurrentPlayer(currentPlayer);
    }

    public VirtualViewConnection getCurrentClient() {
        return currentClient;
    }



    private void setupGame(){
        //Setup phase
        //When storing all the players' names remember to scramble the array in order to choose randomly the order
        //for the first turn, as per the rules
        getGameMoves().setUpGame(getGamePlayers().size(),getGamePlayers());
        setGameOn(true);
    }

    /**
     * This method, as long as the game is going (gameOn = true) cycles through the planning phase and the action
     * phase of each turn.
     * At the end of each turn gameMoves.checkWin() is executed and if the game is over, gameOn is set to false and the name of
     * the winner is printed, otherwise "New Turn" is printed and the game goes on.
     */
    public void run(){
        setupGame();
        try {
            while (getGame()) {
                //Planning phase
                refillCloudTiles();
                chooseTurnAssistantCards();

                //Action phase
                for (Player player : playerOrder) {
                    setCurrentPlayer(player);
                    getCurrentClient().sendMessage(new NotificationCMI("It's your turn!"));
                    getCurrentClient().sendMessage(new NotificationCMI("This is the current playground:"));
                    getCurrentClient().sendMessage(new InfoForDecisionsCMI());
                    moveStudents();
                    getGameMoves().checkProfessorsControl();
                    getCurrentClient().sendMessage(new NotificationCMI("This is the new playground after your move:"));
                    getCurrentClient().sendMessage((new InfoForDecisionsCMI()));
                    moveMotherNature();
                    influenceUpdate(gameMoves.getCurrentGame().getIslandWithMotherNature());
                    getCurrentClient().sendMessage(new NotificationCMI("This is the final playground after your moves:"));
                    getCurrentClient().sendMessage((new InfoForDecisionsCMI()));

                    chooseCloudTiles();
                }

                //In case there's an empty deck or the student bag is empty, this has to be the last turn
                if (getLastTurn())
                    throw new GameWonException();
                else
                    printConsole("------------------NEW TURN----------------------");
            }
        } catch (EmptyTowerYard e){
            setWinner(getGameMoves().checkForEmptyTowerYard().getNickname());
            setGameOn(false);
        } catch (GameWonException e){
            setWinner(getGameMoves().findWinnerTower());
            setGameOn(false);
        }catch (IOException e)
        {
            for (VirtualViewConnection c: getGamePlayers()
                 ) {
                try {
                    c.close();
                } catch (IOException ignored) {}
            }
            setWinner(null);
            setGameOn(false);
        }
        if(getWinner() != null)
        {
            printConsole(getWinner()+" is the winner of this lobby!");
            for(VirtualViewConnection c : getGamePlayers())
            {
                try
                {
                    c.sendMessage(new winnerCMI(getWinner()));
                    c.close();
                }catch (IOException ignored){}
            }
        }
    }

    /**
     * This method refills all the cloud tiles with the appropriate number of students specified in GameSettings only if
     * there are enough studentPaws to refill all of them, if not set this turn as the last
     */
    public void refillCloudTiles() {
        //Check if there are enough studentPaws to refill all the cloud tiles, if not, don't refill them and set this turn as the last
        if (getGameMoves().getTotalStudentPaws() > getGameMoves().getCurrentSettings().getStudentsCloudTile() * getGameMoves().getCurrentGame().getPlayersList().size()){
            for (CloudTile cloudTile : getGameMoves().getCurrentGame().getCloudTiles()) {
                cloudTile.reFill(getGameMoves().generateStudents(getGameMoves().getCurrentSettings().getStudentsCloudTile()));
            }
            // manda a tutti messaggio di aggiornamento playground tramite oggetti virtualviewtcp
        } else
            setLastTurn(true);
    }

    /**
     * This method asks the player which colour is the student that he wants to move, then it asks whether he wants to move
     * it in the dining room or on an island (hence asking which island he wants to move it to). The process is repeated
     * until the player has moved the number of students he has to move as per gameSettings.
     */
    private void moveStudents() throws IOException{
        int studentColour;
        int whereToMove;
        int i = 0;
        while(i < getGameMoves().getCurrentSettings().getStudentsToMove()) {
            try{
                getCurrentClient().sendMessage(new chooseStudentColourToMoveCMI());
                studentColour = getCurrentClient().receiveChooseInt(); // il resto degli errori saranno gestiti così, non ho avuto aancora tempo per correggere tutto
                getCurrentClient().sendMessage(new chooseWhereToMove());
                whereToMove = getCurrentClient().receiveChooseInt();
                if (whereToMove == 0) {
                    getGameMoves().moveStudentEntranceToDining(studentColour);
                    // manda a tutti messaggio a current di aggiornamento mydata
                } else{
                    getCurrentClient().sendMessage(new chooseIslandCMI());
                    int island = getCurrentClient().receiveChooseInt();
                    getGameMoves().moveStudentsEntranceToIsland(studentColour, island);
                    // manda a tutti messaggio di aggiornamento playground tramite oggetti virtualviewtcp
                }
                i++;
            }
            catch (noStudentForColour e) {
                printConsole("No student for colour entrance exception occurs!");
                getCurrentClient().sendMessage(new NotificationCMI("You don't have that student in your entrance room!"));
            }
            catch (FullDiningRoomTable e1)
            {
                printConsole("Full dining room exception occurs!");
                getCurrentClient().sendMessage(new NotificationCMI("You don't have enough space to move that student to the dining room!"));
            }
        }
    }

    /**
     * This method starts by selecting each player, setting him as the currentPlayer and asking him which card he wants
     * to draw while also checking if the card had already been drawn by another player (using gameMoves.useAssistantCard()).
     * In case the player only has already drawn cards in his hands, an exception is made (as per the rules) and he is
     * allowed to draw an already drawn card of his choice
     */
    private void chooseTurnAssistantCards() throws IOException
    {
        List <Player> newPlayerOrder = getGameMoves().getCurrentGame().getPlayersList();
        List <Card> usedCards =  new ArrayList<>();
        int selectedCardNumber;
        Card selectedCard;
        for (Player player: playerOrder){
            setCurrentPlayer(player);
            getCurrentClient().sendMessage(new InfoMyDeckCMI());
            while(true){
                //In case the player has only already drawn cards in his hands
                if(usedCards.containsAll(getCurrentPlayer().getAssistantCards().getResidualCards())){
                    do{
                        getCurrentClient().sendMessage(new chooseAssistantCardCMI());
                        selectedCardNumber = getCurrentClient().receiveChooseInt();
                        selectedCard = player.getAssistantCards().useCard(selectedCardNumber);
                    } while(selectedCard == null);
                    player.setCurrentCard(selectedCard);
                    break;
                }
                //useAssistantCard checks whether the card has already been drawn or not by another player by using the
                //gameMoves.checkValidity() method. If that's the case, the loop goes on until it breaks when the player
                //selects a valid card
                try{
                    getCurrentClient().sendMessage(new chooseAssistantCardCMI());
                    getGameMoves().useAssistantCard(getCurrentClient().receiveChooseInt());
                    usedCards.add(getCurrentPlayer().getCurrentCard());
                    break;
                } catch (UnableToUseCardException e){
                    printConsole("Player fails to choose assistant card");
                    getCurrentClient().sendMessage(new NotificationCMI("Another player use that assistant card in this turn!"));
                }
            }
            getCurrentClient().sendMessage(new NotificationCMI("Waiting for other players to chose assistant card ..."));
            // manda a tutti messaggio di aggiornamento playground tramite oggetti virtualviewtcp
            // manda a current player messaggio aggiornamneto current card
        }
        newPlayerOrder.sort(Comparator.comparing(player1 -> player1.getCurrentCard().getValue()));
        setPlayerOrder(newPlayerOrder);
        setCurrentPlayer(getPlayerOrder().get(0));
        if(getGameMoves().existDeckEmpty())
            setLastTurn(true);
    }

    /**
     * This method asks the player which island he wants to move mother nature to. In case he asks to move mothernature
     * to an island where he can't move it to, he gets asked again until he asks for a valid number of steps.
     */
    private void moveMotherNature()throws IOException{
        while (true) {
            try {
                getCurrentClient().sendMessage(new chooseIslandCMI());

                getGameMoves().moveMotherNature(getCurrentClient().receiveChooseInt());
                break;
            } catch (ExceededMotherNatureStepsException e) {
                printConsole("Mother nature steps");
                getCurrentClient().sendMessage(new NotificationCMI("You exceeded mother nature steps"));
            }
        }
        getCurrentPlayer().setMotherNatureSteps(0);
        // manda a tutti messaggio di aggiornamento playground tramite oggetti virtualviewtcp
    }

    /**
     * This method asks the player updates the influence in case checkInfluence() returns something different from null
     * (no even influence nor 0 influence for every player) and the new calculated player's tower colour is different from
     * the actual tower colour on the island.
     * In case there's no tower on the island setInfluenceToIsland() is called, otherwise changeInfluenceToIsland() is called
     * @throws EmptyTowerYard propagating from Board.decreaseTowerYard
     * @throws GameWonException propagating from IslandController.islandUnification
     */
    public void influenceUpdate(Island motherNatureIsland) throws EmptyTowerYard, GameWonException{
        //Updating influence in case checkInfluence() is not null and if motherNatureIsland's towerColour is different from
        //the new influence-calculated player's towerColour
        if(!motherNatureIsland.isBanned()) {
            Player islandPlayer = getGameMoves().getIslandController().checkInfluence(motherNatureIsland);
            if (islandPlayer != null && islandPlayer.getPlayerBoard().getTowerColour() != motherNatureIsland.getTowerColour()) {
                if (motherNatureIsland.getTowerCount() == 0)
                    getGameMoves().setInfluenceToIsland();
                else
                    getGameMoves().changeInfluenceToIsland();
                getGameMoves().getIslandController().islandUnification(getGameMoves().getCurrentGame().getIslandWithMotherNature());
            }
            // manda a tutti messaggio di aggiornamento playground tramite oggetti virtualviewtcp
            // manda a current player aggiornamneto tramite oggetti virtualviewtcp
        } else {
            motherNatureIsland.setBanned(false);
            //diminuisci bancardnumber
        }
    }

    /**
     * In case this is not the last turn, this method asks the current player which cloudTile he wants to take his new
     * students from, in case the chosen cloudTile has been already taken, CloudTileAlreadyTakenException is handled
     * and the player is asked again.
     */
    private void chooseCloudTiles() throws  IOException{
        while(!getLastTurn()){
            try {
                getCurrentClient().sendMessage(new chooseCloudTileCMI());
                getGameMoves().takeStudentsFromCloudTile(getCurrentClient().receiveChooseInt());
                break;
            } catch(CloudTileAlreadyTakenException e){
                printConsole("CloudTile already taken exception occurs!");
                getCurrentClient().sendMessage(new NotificationCMI("The selected CloudTile is empty!"));
            }
        }
        // manda a tutti messaggio di aggiornamento playground tramite oggetti virtualviewtcp
        // manda a current player aggiornamneto tramite oggetti virtualviewtcp

    }

    /**
     * This method print the input in MAGENTA for the server side console
     * @param textToPrint the text to be printed
     */
    private void printConsole(String textToPrint)
    {
        System.out.println(TextColours.PURPLE_BRIGHT + "> "+ textToPrint);
    }

    private void resetCards(){

    }


    @Override
    public void update() {

        for (VirtualViewConnection c : getGamePlayers()) {
            try{
                c.sendMessage(new UpdatePlayGroundCMI(getGameMoves().getCurrentGame()));
            }
            catch (IOException e)
            {
                try {
                    c.close();
                }
                catch (IOException ignored){}

            }

        }

    }
}
