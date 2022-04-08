package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.Player;

public class TurnHandler {

    private Player CurrentPlayer;

    public Player getCurrentPlayer() {
        return CurrentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        CurrentPlayer = currentPlayer;
    }
}
