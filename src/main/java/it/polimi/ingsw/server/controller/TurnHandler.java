package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

import java.util.Collections;

public class TurnHandler {
    private Player CurrentPlayer;
    private GameController gameController;

    public GameController getGameController() {return gameController;}
    public void setGameController(GameController gameController) {this.gameController = gameController;}
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

    public void initializeTurn()
    {
        CurrentPlayer = null;
        for (Player player: getGameController().getCurrentGame().getPlayersList()) {
            chooseTurnAssistantCard(player);
        }
        Collections.sort(getGameController().getCurrentGame().getPlayersList()); // sorto per valore carta assistente
        setCurrentPlayer(getGameController().getCurrentGame().getPlayersList().get(0));
    }

    private void turn()
    {
        // qui descrivo un turno per un giocatore specifico
    }

    public void chooseTurnAssistantCard(Player player)
    {
        // qui ricevo per ogni player la carta assistente scelta e la immagazzino in player
    }
}
