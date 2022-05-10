package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import it.polimi.ingsw.server.model.PlayGround;

public class UpdatePlayGroundCMI implements ClientMessageImplement {
    private final Object playGroundNew;

    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket){
       userInterface.update(getPlayGroundNew());

    }
    public UpdatePlayGroundCMI(Object playGroundNew) {
        this.playGroundNew = playGroundNew;
    }


    public Object getPlayGroundNew() {
        return playGroundNew;
    }
}
