package it.polimi.ingsw.server.controller.Exceptions;

/**
 * This exception represent that a play is trying to move mother nature
 * for too many steps (the maximum number of steps is stored in the player class)
 */
public class ExceededMotherNatureStepsException extends Exception{
    public ExceededMotherNatureStepsException() {
    }
}
