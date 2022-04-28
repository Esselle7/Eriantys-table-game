package it.polimi.ingsw.server;
import it.polimi.ingsw.server.model.*;

public interface ControllerViewObserver {
    void update(Object toUpdate);
}

/*
La VirtualView dovrà implementare ControllerViewObserver e al suo interno
sarà necessario implementare i diversi metodi update (@Override)
che mandano dei Message di tipo diverso per effettuare il cambiamento (esempio di messaggio qui sotto)
il client avrà infatti un CaseSwitch per la lettura dei messaggi che a sua volta invocherà
un metodo della view del client (cercando il metodo) per applicare la modifica

    @Override
    public void update(PlayGround toUpdate) {
        sendMessage(new Message("update", new PlayGroundClient(toUpdate)));
    }

    @Override
    public void update(Island toUpdate) {
        sendMessage(new Message("update", new BoardClient(toUpdate)));
    }

    case Message.UPDATEPLAYGROUND:
                //Searching the method in ClientView
                try {
                    method = view.getClass().getMethod(receivedMessage.getMethod(), PlayGround.class);
                    try {
                        return method.invoke(view, receivedMessage.getPlayground());
                    } catch exception

*/
