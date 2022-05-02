package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.connection.ClientMessageImplement;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import it.polimi.ingsw.server.model.PlayGround;
import it.polimi.ingsw.server.model.Player;

public class UpdatePlayGroundCMI implements ClientMessageImplement {
    private final Object playGroundNew;

    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket){
        userInterface.update(getPlayGroundNew());
        for (Player p: getPlayGroundNew().getPlayersList()) {
            if(p.getNickname().equals(userInterface.getMyNickname()))
            {
                userInterface.update(p.getPlayerBoard(),p.getAssistantCards(),p.getCurrentCard());
                break;
            }
        }
    }
    public UpdatePlayGroundCMI(Object playGroundNew) {
        this.playGroundNew = playGroundNew;
    }


    public PlayGround getPlayGroundNew() {
        return (PlayGround)playGroundNew;
    }
}
