package hyll.sk.uniza.users;

import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.MessageBuffer;
import hyll.sk.uniza.helpers.State;
import hyll.sk.uniza.messages.*;

import java.io.IOException;


public abstract class User {
    //this may be abstract?

    MessageBuffer<TextMessage> messageBuffer;
    private String nickName;

    public User(String nickName) {
        this.nickName = nickName;
        this.messageBuffer = new MessageBuffer(10, this.nickName);
    }

    public String getName() {
        return this.nickName;
    }


    public int numberOfAcceptedMessages() {
        return 0;
    }


    public void sendMessage(IMessage message) {

    }



    public void createMessage(IMessage message) {
        if (message instanceof TextMessage) {
            this.messageBuffer.saveMessageToBuffer((TextMessage) message);
        }
    }

    //Debug
    public String getUsersMessageBuffer() {
        TextMessage[] tx = this.messageBuffer.getMessages();
        StringBuilder sb = new StringBuilder();
        for (TextMessage textMessage : tx) {
            if (textMessage instanceof TextMessage) {
                sb.append(textMessage.getFormat() + " ");
            }
        }
        return sb.toString();
    }

    public void sendMessages(User user) {
        TextMessage arrMess[] = null;
        try {
            arrMess = this.messageBuffer.getMessages();
        } catch (Exception e) {
            System.out.println("Message not in buffer");
            return;
        }
        for (IMessage mess : arrMess) {
            if (mess instanceof TextMessage) {
                user.receiveMessage(mess, this.nickName);
            }
        }
        this.messageBuffer.clearBuffer();
    }

    public void removeMessages(){
        this.messageBuffer.clearBuffer();
    }

    public void removeMessages(int i){
        this.messageBuffer.clearBuffer(i);
    }


    //TODO: this will be overriden by method with argument message bcs of buffer

    //should be private
    public void receiveMessage(IMessage message, String senderName) {
        try {
            message.constructMessage(State.RECEIVER, this.nickName, senderName);
        } catch (IOException | DemotedMessageException e) {
            e.printStackTrace();
        }
    }


}
