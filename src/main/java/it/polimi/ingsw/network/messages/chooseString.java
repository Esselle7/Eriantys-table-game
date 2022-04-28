package it.polimi.ingsw.network.messages;

/**
 * This class represent a String chosen by the client
 * sent to te server
 */
public class chooseString implements Message {
        private final String data;

        public chooseString(String data) {
        this.data = data;
    }
        public String getData() {
        return data;
    }
}
