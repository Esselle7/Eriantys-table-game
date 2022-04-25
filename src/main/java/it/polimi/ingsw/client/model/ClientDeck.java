package it.polimi.ingsw.client.model;


import java.util.List;

public class ClientDeck {
    private Wizard deckName;
    private List<ClientCard> assistantCards;

    public Wizard getDeckName() {
        return deckName;
    }

    public void setDeckName(Wizard deckName) {
        this.deckName = deckName;
    }

    public List<ClientCard> getAssistantCards() {
        return assistantCards;
    }

    public void setAssistantCards(List<ClientCard> assistantCards) {
        this.assistantCards = assistantCards;
    }
}
