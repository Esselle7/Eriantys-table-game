package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.connection.ConnectionClientSide;

import java.io.IOException;

public class winnerCMI implements ClientMessageImplement {
    String winner;
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket) throws IOException, InterruptedException {
        userInterface.displayWinner(getWinner());
    }

    public winnerCMI(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}
