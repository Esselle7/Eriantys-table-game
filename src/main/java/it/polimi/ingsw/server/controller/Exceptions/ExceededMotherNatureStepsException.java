package it.polimi.ingsw.server.controller.Exceptions;

/**
 * This exception represent that a play is trying to move mother nature
 * for too many steps (max steps are store in the current player card)
 */
public class ExceededMotherNatureStepsException extends Exception{
    public ExceededMotherNatureStepsException() {
    }
}
