package it.polimi.ingsw.server.controller.Exceptions;

public class chooseCharacterCardException extends Throwable {
    private final int characterCard;
    public chooseCharacterCardException(int characterCard) {
        this.characterCard = characterCard;
    }

    public int getCharacterCard() {
        return characterCard;
    }
}
