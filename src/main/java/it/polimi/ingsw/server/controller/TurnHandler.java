package it.polimi.ingsw.server.controller;

import it.polimi.ingsw.server.model.*;

import java.util.Collections;

public class TurnHandler {
    private Player CurrentPlayer;
    private GameMoves gameMoves;

    public GameMoves getGameMoves() {
        return gameMoves;
    }

    public void setGameMoves(GameMoves gameMoves) {
        this.gameMoves = gameMoves;
    }

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
        for (Player player: getGameMoves().getCurrentGame().getPlayersList()) {
            chooseTurnAssistantCard(player);
        }
        Collections.sort(getGameMoves().getCurrentGame().getPlayersList()); // sorto per valore carta assistente
        setCurrentPlayer(getGameMoves().getCurrentGame().getPlayersList().get(0));
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
