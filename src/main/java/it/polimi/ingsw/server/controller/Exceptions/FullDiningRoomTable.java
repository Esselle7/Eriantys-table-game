package it.polimi.ingsw.server.controller.Exceptions;

/**
 * Thrown if something is trying to add a colour in a table already full of students of that colour
 */
public class FullDiningRoomTable extends Exception{
    public FullDiningRoomTable(){}
}
