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
    private final List<Card> assistantCards;

    /**
     * Public constructor that creates a deck as well as 10 assistant cards
     */
    public Deck(){
        int[] motherNatureSteps = {1,1,2,2,3,3,4,4,5,5};
        assistantCards = new ArrayList<>();
        for (int index = 0; index < motherNatureSteps.length; index++) {
            assistantCards.add(new Card(index+1,motherNatureSteps[index]));
        }
    }

    public List<Card> getResidualCards()
    {
        return assistantCards;
    }

    /**
     * This method checks whether the selected card is present in the deck, if yes it removes it from the deck and returns it,
     * if not it simply returns null
     * @param value the value of the card to return
     * @return the returned card, null if there's no card in the deck for the corresponding value
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
