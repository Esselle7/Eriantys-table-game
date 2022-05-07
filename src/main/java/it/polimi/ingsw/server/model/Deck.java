package it.polimi.ingsw.server.model;

import java.io.Serializable;
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

public class Deck implements Serializable {
    private final Wizard deckName;
    private final List<Card> assistantCards;

    /**
     * Public constructor that create a
     * single deck with 10 assistant cards
     * @param deckName the name of the deck
     *                 choosen between Wizard enum
     */
    public Deck(Wizard deckName){
        this.deckName = deckName;
        int[] motherNatureSteps = {1,1,2,2,3,3,4,4,5,5};
        assistantCards = new ArrayList<>();
        for (int index = 0; index < motherNatureSteps.length; index++) {
            assistantCards.add(new Card(index+1,motherNatureSteps[index]));
        }
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
     * @return the card
     *         with the value in input
     *         and so we can use it, false
     *         if there isn't a card with
     *         value = value in input
     */
    public Card useCard(int value)
    {
        Card result;
        for (Card toFind : assistantCards)
            if (toFind.getValue() == value) {
                result = new Card(toFind.getValue(), toFind.getMotherNatureSteps());
                assistantCards.remove(toFind);
                return result;
            }
        return null; // not very good, throw an exception maybe
    }

    public boolean isEmpty()
    {
        return assistantCards.size() == 0;
    }

}
