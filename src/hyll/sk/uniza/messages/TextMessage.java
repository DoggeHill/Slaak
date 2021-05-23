package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.helpers.State;

import java.io.FileWriter;
import java.io.IOException;


/**
 * TextMessage representation
 *
 * @author patri
 */
public class TextMessage implements IMessage {

    private final String content;
    private final long timeStamp;

    public TextMessage(String content, long timeStamp) {
        this.content = content;
        this.timeStamp = timeStamp;
    }

    @Override
    public String getFormat() {
        return this.content;
    }

    @Override
    public long getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * Simple create a record in the texts database
     * @param state      of the message
     * @param nickName   name of the user
     * @param senderName name of the receiver
     * @throws IOException             err
     * @throws DemotedMessageException special err
     */
    @Override
    public void constructMessage(State state, String nickName, String senderName) throws IOException, DemotedMessageException {
        FileWriter fw = loadDatabase();
        fw.write("from: " + senderName + " to: " + nickName + " " + this.content + " at: " + this.timeStamp + "\r\n");
        System.out.println("Storing message: " + this.getFormat() + " into database");
        fw.close();
    }

    @Override
    public FileWriter loadDatabase() throws IOException, DemotedMessageException {
        return DatabaseLoader.loadDatabase("texts.txt");
    }

}
