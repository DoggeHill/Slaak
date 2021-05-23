package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.State;

import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is in the developement
 * @author patri
 */
public class Video implements IMessage, IImage{

    public Video(){
        System.out.println("This type format is not yet supported!");
    }
    @Override
    public float getDimension() {
        return 0;
    }

    @Override
    public float getSize() {
        return 0;
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
    public void constructMessage(State state, String nickName, String senderName) throws IOException, DemotedMessageException {

    }

    @Override
    public FileWriter loadDatabase() throws IOException, DemotedMessageException {
        return null;
    }
}
