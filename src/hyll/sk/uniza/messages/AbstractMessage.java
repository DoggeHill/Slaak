package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.State;

import java.io.FileWriter;

public abstract class AbstractMessage extends TextMessage {

    public AbstractMessage(String content, long timeStamp) {
        super(content, timeStamp);
    }

    public void printString() {
        System.out.println( super.getFormat());
    }

    @Override
    public void constructMessage(State state, String nickName, String senderName) throws DemotedMessageException {
        throw new DemotedMessageException("This message cannot be constructed");
    }

    @Override
    public FileWriter loadDatabase() throws DemotedMessageException {
        throw new DemotedMessageException("Database for this message cannot be loaded");

    }
}
