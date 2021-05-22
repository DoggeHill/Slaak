package hyll.sk.uniza.helpers;

import hyll.sk.uniza.messages.IImage;
import hyll.sk.uniza.messages.IMessage;
import hyll.sk.uniza.messages.TextMessage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class MessageBuffer<E extends TextMessage> {


    private final int size;
    private final String userName;
    private ArrayList<E> messages;


    public MessageBuffer(int size, String name) {
        this.size = size;
        this.messages = new ArrayList<>();
        this.userName = name;
    }

    public void saveMessageToBuffer(E message) {
        if (!(message instanceof IImage)) {
            this.messages.add( message );
        }
    }

    public void clearBuffer(){
        this.messages.clear();
    }

    public void clearBuffer(int i){
        if(i > this.messages.size()){
            System.out.println("Invalid number");
            return;
        }
        this.messages.remove(i);
    }

    //tu bude muesieť byť generická trieda
    public TextMessage[] getMessages() {
        TextMessage[] tx = new TextMessage[this.size];
        byte ittCnt = 0;
        for (IMessage message : this.messages) {
            tx[ittCnt] = (TextMessage) this.messages.get(ittCnt++);
        }

        return tx;
    }
}
