package it.polimi.ingsw.server.model;

import java.io.Serializable;

/**
 * Colours of the game, useful to display as a class in case a new colour will be added in the future
 */
public abstract class Colour implements Serializable {
    public static int RED = 0;
    public static int GREEN = 1;
    public static int BLUE = 2;
    public static int PINK = 3;
    public static int YELLOW = 4;
    public static int colourCount = 5;
}
