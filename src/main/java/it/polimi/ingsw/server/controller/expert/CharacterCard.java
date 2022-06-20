package it.polimi.ingsw.server.controller.expert;
import it.polimi.ingsw.network.messages.NotificationCMI;
import it.polimi.ingsw.network.messages.UpdatePlayGroundCMI;
import it.polimi.ingsw.server.VirtualClient.VirtualViewConnection;
import it.polimi.ingsw.server.controller.*;
import it.polimi.ingsw.server.controller.Exceptions.*;
import java.io.IOException;
import java.io.Serializable;

/**
 * This class serves as a blueprint for all the character cards, it implements on its own
 * the card buying process and basic getters and setters.
 * Each card has a price, a flag to know if it has ever been used (in order to raise the price or not), a description
 * and an ID as well as other attributes which can be used by the single cards.
 */
public abstract class CharacterCard implements Serializable {
    int price;
    boolean hasBeenUsed;
    String description = "";
    String specificDescription;
    int[] students;
    int id;
    int banCardNumber;

    /**
     * Main Constructor
     * @param price to set for the card
     * @param id card id
     */
    public CharacterCard(int price, int id){
        this.hasBeenUsed = false;
        this.price = price;
        this.specificDescription = null;
        this.students = null;
        this.id = id;
        banCardNumber = 0;
    }

    /**
     * This method handles the buying process of a card by decreasing its buyer's coins and increasing
     * the card's price in case it has never been selected before.
     * @param turnHandler the turnHandler which the card belongs to
     */
    public void buyCard(TurnHandler turnHandler) throws IOException, NotEnoughCoins {
        turnHandler.getCurrentClient().sendMessage(new NotificationCMI("Card successfully acquired"));
        turnHandler.getCurrentPlayer().getPlayerBoard().decreaseCoins(price);
        if(!hasBeenUsed) {
            price++;
            turnHandler.getCurrentPlayer().getPlayerBoard().decreaseCoinReserve();
            hasBeenUsed = true;
            turnHandler.getCurrentClient().sendMessage(new NotificationCMI("The card price has been increased to " + price));
        }
    }

    /**
     * This method initializes the attributes at the beginning of the game
     */
    public void initializeCard(TurnHandler turnHandler){
    }

    /**
     * This method handles the card's effects
     */
    public abstract void useCardImpl(TurnHandler turnHandler) throws ChooseCharacterCardException, IOException, NotEnoughCoins, EmptyTowerYard,UnableToUseCardException, GameWonException;

    /**
     * This method calls useCardImpl and updates every player's playground
     */
    public void useCard(TurnHandler turnHandler) throws IOException, EmptyTowerYard, NotEnoughCoins, GameWonException, UnableToUseCardException {
        try {
            useCardImpl(turnHandler);
        }catch (ChooseCharacterCardException ignored){}
        for(VirtualViewConnection c : turnHandler.getGamePlayers())
        {
            c.sendMessage(new UpdatePlayGroundCMI(turnHandler.getGameMoves().getCurrentGame()));
        }

    }

    public int getPrice() {
        return price;
    }

    /**
     * This method zeroes the card's effects
     */
    public void resetCard(TurnHandler turnHandler) {}

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudents(int[] students) {
        this.students = students;
    }

    public int[] getStudents() {
        return students;
    }

    public int getId() {
        return id;
    }

    public int getBanCardNumber() {
        return banCardNumber;
    }
}
