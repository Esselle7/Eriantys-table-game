package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.client.ClientMessageImplement;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.connection.ConnectionClientSide;
import java.io.IOException;

/**
 * This class allows sending String notification
 * from the Server to te client and the client
 * will print the notification String
 */
public class NotificationCMI implements ClientMessageImplement {
    private final String notification;
    @Override
    public void elaborateMessage(View userInterface, ConnectionClientSide socket){
        userInterface.printNotification(getNotification());
    }
    public NotificationCMI(String notification)
    {
        this.notification = notification;
    }

    public String getNotification() {
        return notification;
    }
}
