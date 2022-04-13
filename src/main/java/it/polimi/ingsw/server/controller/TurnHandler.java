package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

public class TurnHandler {

    private Player CurrentPlayer;

    public Player getCurrentPlayer() {
        return CurrentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        CurrentPlayer = currentPlayer;
    }

    public Board getCurrentPlayerBoard()
    {
        return CurrentPlayer.getPlayerBoard();
    }
}
