package it.polimi.ingsw.server.controller.Exceptions;

/**
 * Thrown if the player wants to choose a character card
 */
public class ChooseCharacterCardException extends Throwable {
    private final int characterCard;
    public ChooseCharacterCardException(int characterCard) {
        this.characterCard = characterCard;
    }

    public int getCharacterCard() {
        return characterCard;
    }
}
