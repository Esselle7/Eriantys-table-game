package it.polimi.ingsw.network.messages;

import java.io.Serializable;

/**
 * This is a generic interface for messages
 * that allows to create multiple static serializable
 * object. This interface will be implemented only for
 * communicating data with a specific type.
 */
public interface Message extends Serializable {
}
