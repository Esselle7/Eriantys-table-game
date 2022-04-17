package it.polimi.ingsw.server.controller;


public class GameController{
    private GameMoves gameMoves;
    private TurnHandler turnHandler;
    /* questa è la classe generale che accoglierà le richieste
        dalla view per elaborarle e chiamare le opportune mosse attraverso gameMoves
        Importante capire che questa classe riceverà i segnali dalla virtual view
        (non sappiamo ancora come, lo devi capire tu).
        Capire come implementare multi partite, istanziamo una istanza di game controller per
        ogni gioco? Credo di si. Server fare la classe server che fa storage delle partite, vedi
        tu come implementarla leggendo github del progetto passato.
        Valuta tu anche gli attributi di questa classe, ha senso tenere anche turnhandler? Oppure
        mantieni solo GameMoves come attributo siccome al suo intenro c'è già il turn handler?

    * */
}
