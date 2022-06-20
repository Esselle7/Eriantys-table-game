package it.polimi.ingsw.server.controller.Exceptions;

/**
 * Thrown if something tries to withdraw a colour which is not present in the dining room
 */
public class EmptyDiningRoom extends Exception{
    public EmptyDiningRoom(){}
}
