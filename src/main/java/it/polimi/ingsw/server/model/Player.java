package it.polimi.ingsw.server.model;

public class Player extends ManagerStudent implements Comparable<Player> {
    private final String nickname;
    private Board playerBoard;
    private Deck assistantCards;
    private Card currentCard;

    public Player(String nickname)
    {
        this.nickname = nickname;
        currentCard = new Card();
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

    public void setAssistantCards(Deck assistantCards) {
        this.assistantCards = assistantCards;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void useCard(int cardNumber)
    {
        setCurrentCard(assistantCards.useCard(cardNumber));

    }

    public int compareTo(Player otherPlayer) {
        return Integer.compare(this.currentCard.getValue(),otherPlayer.currentCard.getValue());
    }
}
