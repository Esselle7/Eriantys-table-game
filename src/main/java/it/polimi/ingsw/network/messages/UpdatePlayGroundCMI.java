package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import it.polimi.ingsw.server.model.PlayGround;
import java.io.IOException;

public class UpdatePlayGroundCMI implements ClientMessageImplement {
    private final PlayGround playGroundNew;

    public UpdatePlayGroundCMI(PlayGround playGroundNew) {
        this.playGroundNew = playGroundNew;
    }

    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket){
        userInterface.update(getPlayGroundNew());

    }

    public PlayGround getPlayGroundNew() {
        return playGroundNew;
    }
}
