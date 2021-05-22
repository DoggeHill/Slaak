package hyll.sk.uniza.messages;

import hyll.sk.uniza.Slakk.DemotedMessageException;

import java.io.FileWriter;
import java.io.IOException;

public interface IMessage {
    String getFormat();
    long getTimeStamp();
    void constructMessage(State state, String nickName) throws IOException, DemotedMessageException;
    FileWriter loadDatabase() throws IOException, DemotedMessageException;
}
