package it.polimi.ingsw.server.model;

public class Player extends ManagerStudent {
    private final String nickname;
    private Board playerBoard;
    private Deck assistantCards;

    public Player(String nickname)
    {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Deck getAssistantCards() {
        return assistantCards;
    }

    public void setDeck(Deck assistantCards)
    {
        this.assistantCards = assistantCards;
    }

    public void setPlayerBoard(Board playerBoard)
    {
        this.playerBoard = playerBoard;
    }

    public Card useCard(int cardNumber)
    {
        return assistantCards.useCard(cardNumber);

    }


}
