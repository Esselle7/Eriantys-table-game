package it.polimi.ingsw.server.controller.Exceptions;

/**
 * This exception represent that a player is trying to use
 * a card that another players has already used in that turn
 */
public class UnableToUseCardException extends Exception{
    public UnableToUseCardException() {
    }
}
