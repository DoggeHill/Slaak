package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.State;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Representation of any message
 * @author
 */
public interface IMessage {
    String getFormat();
    long getTimeStamp();
    void constructMessage(State state, String nickName, String senderName) throws IOException, DemotedMessageException;
    FileWriter loadDatabase() throws IOException, DemotedMessageException;
}
