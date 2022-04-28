package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import it.polimi.ingsw.server.model.Board;
import it.polimi.ingsw.server.model.Card;
import it.polimi.ingsw.server.model.Deck;

import java.io.IOException;

public class UpdateMyDataCMI implements ClientMessageImplement {
    private final Board myBoardNew;
    private final Deck myDeckNew;
    private final Card myCurrentCardNew;

    public UpdateMyDataCMI(Board myBoardNew, Deck myDeckNew, Card myCurrentCardNew) {
        this.myBoardNew = myBoardNew;
        this.myDeckNew = myDeckNew;
        this.myCurrentCardNew = myCurrentCardNew;
    }

    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket){
        userInterface.update(getMyBoardNew(),getMyDeckNew(),getMyCurrentCardNew());
    }

    public Board getMyBoardNew() {
        return myBoardNew;
    }

    public Deck getMyDeckNew() {
        return myDeckNew;
    }

    public Card getMyCurrentCardNew() {
        return myCurrentCardNew;
    }
}
