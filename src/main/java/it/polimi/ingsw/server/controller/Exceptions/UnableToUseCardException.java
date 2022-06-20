package it.polimi.ingsw.server.controller.Exceptions;

/**
 * Thrown if a player tries to use a card that has already been used in the current turn
 */
public class UnableToUseCardException extends Exception{
    public UnableToUseCardException() {
    }
}
