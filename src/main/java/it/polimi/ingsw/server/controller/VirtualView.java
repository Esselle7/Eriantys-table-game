package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.controller.Exceptions.noColourException;

public class VirtualView {
    //Ask Which Colour the student wants
    //IT'S IMPORTANT to add the NoColourException here, not anywhere else, mainly because we would have to add
    //this exception on every method using a color
    public int askColour() throws noColourException {return 0;}
    //0 if the player wants to move students to the dining room or 1 if he wants to move to an island
    public int askWetherIslandOrDining(){return 0;}
    //Return the index of an island
    public int askWhichIsland(){return 0;}
    //Return the index of an assistant card in the player assistant card deck
    public int askTurnAssistantCard(){return 0;}
    //Ask the index number of the intended cloud tile
    public int askCloudTile(){return 0;}
}
