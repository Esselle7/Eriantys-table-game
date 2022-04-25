package it.polimi.ingsw.client.model;

import it.polimi.ingsw.server.model.Board;
import it.polimi.ingsw.server.model.Card;

public class ClientPlayer {
    private String nickname;
    private ClientBoard playerBoard;
    private ClientCard currentCard;

    public String getNickname() {
        return nickname;
    }

    public ClientBoard getPlayerBoard() {
        return playerBoard;
    }


    public ClientCard getCurrentCard() {
        return currentCard;
    }

}
