package hyll.sk.uniza.messages;


public class WelcomeMessage extends AbstractMessage{

    public WelcomeMessage(String content) {
        super(content, -1);
    }

    @Override
    public String toString(){
        return super.getFormat();
    }
}
