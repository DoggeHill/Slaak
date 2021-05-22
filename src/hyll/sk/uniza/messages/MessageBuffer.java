package hyll.sk.uniza.messages;

import java.util.ArrayList;

public class MessageBuffer {


    private final int size;
    private ArrayList<IMessage> messages;

    public MessageBuffer(int size){
        this.size = size;
        this.messages = new ArrayList<>();
    }

    public void saveMessageToBuffer(IMessage message){
        this.messages.add(message);

    }
    //tu bude muesieť byť generická trieda
    public TextMessage[] getMessages(){
        TextMessage[] tx = new TextMessage[2];
        tx[0] = (TextMessage) this.messages.get(0);
        return tx;
    }
}
