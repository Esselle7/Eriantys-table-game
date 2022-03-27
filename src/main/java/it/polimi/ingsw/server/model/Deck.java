package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A deck of assistant cards.
 * There are only 4 decks.
 * For each deck there are 10 assistant cards
 * We can instance only 4 deck, so we used
 * a revisiting of singleton pattern
 *
 */

public class Deck {
    private final Wizard deckName;
    private static List<Card> assistantCards;
    private static boolean decksSeatedUp = false;

    /**
     * Private constructor that create a
     * single deck with 10 assistant cards
     * @param deckName the name of the deck
     *                 choosen between Wizard enum
     * @param motherNatureSteps each element of this
     *                          list contains the maximum
     *                          mother nature steps allowed
     *                          by an assistant card
     */
    private Deck(Wizard deckName,List<Integer> motherNatureSteps){
        this.deckName = deckName;
        for (int index = 0; index < motherNatureSteps.size(); index++) {
            assistantCards.add(new Card(index+1,motherNatureSteps.get(index)));
        }

    }

    /**
     * Static method that create the four decks
     * only if there are no previous instance,
     * so we should call thi method one time
     * per game
     * @param motherNatureSteps parameter to give in input
     *                          to deck constructor
     * @return if the decks aren't setted up yet, the return
     *         value is the list of the four decks
     * @throws InstantiationError if the decks are already
     *                            setted up return error
     */
    public static List<Deck> setUpDecks(List<Integer> motherNatureSteps) throws InstantiationError{
        if(!decksSeatedUp)
        {
            assistantCards = new ArrayList<>();
            List<Deck> wizardDecks = new ArrayList<>();
            for(Wizard wizardName : Wizard.values())
                wizardDecks.add(new Deck(wizardName,motherNatureSteps));
            decksSeatedUp = true;
            return wizardDecks;
        }
        else
            throw new InstantiationError();
    }

    public Wizard getDeckName() {
        return deckName;
    }

    public List<Card> getResidualCards()
    {
        return assistantCards;
    }

    /**
     * This method will use an assistant card
     * for a round of game
     * @param value the value of the card
     *              to use
     * @return true if there was a card
     *          with the value in input
     *          and so we can use it, false
     *          if there isn't a card with
     *          value = value in input
     */
    public boolean useCard(int value)
    {
        for (Card toFind : assistantCards)
            if (toFind.getValue() == value) {
                assistantCards.remove(toFind);
                return true;
            }
        return false;

    }

    public boolean isEmpty()
    {
        return assistantCards.size() == 0;
    }

    public static boolean isDecksSeatedUp() {
        return decksSeatedUp;
    }
}
