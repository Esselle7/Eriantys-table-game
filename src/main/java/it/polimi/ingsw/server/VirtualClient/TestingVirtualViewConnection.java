package it.polimi.ingsw.server.VirtualClient;

import it.polimi.ingsw.network.connectionTCP.IncomingTCP;
import it.polimi.ingsw.network.connectionTCP.OutcomingTCP;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.server.controller.Exceptions.ChooseCharacterCardException;

import java.io.IOException;

public class TestingVirtualViewConnection implements VirtualViewConnection {
    private final IncomingTCP InFromClient = null;
    private final OutcomingTCP OutToClient = null;
    private String nickname;

    public TestingVirtualViewConnection() throws IOException {
    }

    public IncomingTCP getInFromClient() {
        return InFromClient;
    }

    public OutcomingTCP getOutToClient() {
        return OutToClient;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    @Override
    public void ping() throws IOException {
        sendMessage(new Ping());
    }


    @Override
    public void sendMessage(Message messageToSend) throws IOException {
        try {
            OutToClient.sendMessage(messageToSend);
        }
        catch (IOException e)
        {
            OutToClient.setNotAlive();
            throw new IOException();
        }
    }

    @Override
    public Message receiveMessage() throws IOException {
        try {
            return InFromClient.receiveMessage();
        }
        catch (IOException e)
        {
            InFromClient.setNotAlive();
            throw new IOException();
        }
    }

    @Override
    public int receiveChooseInt() throws IOException, ChooseCharacterCardException {
        Message received = receiveMessage();
        if(received instanceof wantToChooseCharacterCard)
            throw new ChooseCharacterCardException(((wantToChooseCharacterCard) received).getCharacterCard());
        else
        {
            chooseInt choice = (chooseInt) received;
            return choice.getData();
        }


    }

    @Override
    public String receiveChooseString() throws IOException {
        chooseString string = (chooseString) receiveMessage();
        if(string == null)
            return "";
        else
            return string.getData();
    }

    @Override
    public void close() throws IOException {
        OutToClient.close();
        InFromClient.close();
    }
}

