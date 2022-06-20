package it.polimi.ingsw.server.controller.Exceptions;

/**
 * Thrown if the tower yard is empty, necessary since the game has been won by a player
 */
public class EmptyTowerYard extends Exception{
    public EmptyTowerYard(){}
}
