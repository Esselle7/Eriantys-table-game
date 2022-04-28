package it.polimi.ingsw.network.messages;


public class chooseString implements Message {
        private final String data;

        public chooseString(String data) {
        this.data = data;
    }
        public String getData() {
        return data;
    }
}
