package it.polimi.ingsw.network.messages;
import java.time.Instant;

/**
 * This class allows to detect client disconnections
 */
public class Ping implements Message {
    private final Instant timeStamp = Instant.now();

    public Instant getTimeStamp() {
        return timeStamp;
    }
}
