package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.State;

import java.io.FileWriter;
import java.io.IOException;

public class Video implements IMessage, IImage{


    //TODO: finish this later


    private final String content;
    private final long timeStamp;

    public Video(String content, long timeStamp){
        this.content = content;
        this.timeStamp = timeStamp;
    }

    @Override
    public String getFormat() {
        return null;
    }

    @Override
    public long getTimeStamp() {
        return 0;
    }

    @Override
    public void constructMessage(State state, String nickName, String senderName) throws IOException {

    }

    @Override
    public FileWriter loadDatabase() throws IOException {
        return null;
    }

    @Override
    public float getDimension() {
        return 0;
    }

    @Override
    public float getSize() {
        return 0;
    }


}
