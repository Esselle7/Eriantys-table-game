package it.polimi.ingsw.network.messages;

public class wantToChooseCharacterCard implements Message{
    private final int characterCard;
    public wantToChooseCharacterCard(int characterCard) {
        this.characterCard = characterCard;
    }

    public int getCharacterCard() {
        return characterCard;
    }
}
