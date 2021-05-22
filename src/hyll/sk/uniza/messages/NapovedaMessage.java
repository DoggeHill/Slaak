package hyll.sk.uniza.messages;

public class NapovedaMessage extends AbstractMessage {

    private static String content= "evwe";
    public NapovedaMessage() {
        super(content,-1);
    }

    @Override
    public String toString() {
        return super.getFormat();
    }


}
