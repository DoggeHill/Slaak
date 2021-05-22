package hyll.sk.uniza.messages;

import hyll.sk.uniza.Slakk.DemotedMessageException;

import java.io.FileWriter;
import java.io.IOException;

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

    @Override
    public void constructMessage(State state, String nickName) throws IOException, DemotedMessageException {
        FileWriter fw = loadDatabase();
        fw.write(nickName + " " + this.content + " " + this.timeStamp + "\r\n");
        System.out.println("Storing message: " + this.getFormat() + " into database");
        fw.close();
    }

    @Override
    public FileWriter loadDatabase() throws IOException, DemotedMessageException {
        return DatabaseLoader.loadDatabase("texts.txt");
    }

}
