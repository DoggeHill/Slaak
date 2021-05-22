package hyll.sk.uniza.messages;

import hyll.sk.uniza.Slakk.DemotedMessageException;

import java.io.FileWriter;

public abstract class AbstractMessage extends TextMessage {

    public AbstractMessage(String content, long timeStamp) {
        super(content, timeStamp);
    }

    @Override
    public void constructMessage(State state, String nickName) throws DemotedMessageException {
        throw new DemotedMessageException("This message cannot be constructed");
    }

    @Override
    public FileWriter loadDatabase() throws DemotedMessageException {
        throw new DemotedMessageException("Database for this message cannot be loaded");

    }
}
