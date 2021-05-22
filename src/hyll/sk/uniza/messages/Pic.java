package hyll.sk.uniza.messages;

import java.io.FileWriter;
import java.io.IOException;

public class Pic implements IMessage, IImage{

    private final String content;
    private final long timeStamp;

    public Pic(String content, long timeStamp){
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
    public void constructMessage(State state, String nickName) throws IOException {

    }

    @Override
    public FileWriter loadDatabase() throws IOException {
        return DatabaseLoader.loadDatabase("pics.txt");
    }

    @Override
    public float getLength(IMessage message) {
        return 0;
    }

    @Override
    public float getSize(IMessage sprava) {
        return 0;
    }
}
