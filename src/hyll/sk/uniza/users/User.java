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
    private IMessage messageToSend;

    public User(String nickName) {
        this.nickName = nickName;
        System.out.println("new buffer");
        this.messageBuffer = new MessageBuffer<>(10, this.nickName);
        this.messageToSend = null;
    }

    protected String getName() {
        return this.nickName;
    }


    protected int numberOfAcceptedMessages() {
        return 0;
    }


    public void sendMessage(User user) {

        user.receiveMessage(this.messageToSend, this.nickName);
    }
    protected IMessage getMessageToSend(){
        return this.messageToSend;
    }



    public void createMessage(IMessage message) {
        if (message instanceof TextMessage) {
            this.messageBuffer.saveMessageToBuffer((TextMessage) message);
            System.out.println("Saving to buffer");
        } else{
            this.messageToSend = message;
        }

    }

    //Debug
    public String getUsersMessageBuffer() {
        TextMessage[] tx = this.messageBuffer.getMessages();
        StringBuilder sb = new StringBuilder();
        for (TextMessage textMessage : tx) {
            if (textMessage != null) {
                sb.append(textMessage.getFormat()).append(" ");
            }
        }
        return sb.toString();
    }

    public void sendMessages(User user) {
        if(this.messageToSend != null){
            user.receiveMessage(this.messageToSend, this.nickName );
            System.out.println("here");
            return;
        }

        TextMessage[] arrMess;
        try {
            arrMess = this.messageBuffer.getMessages();
        } catch (Exception e) {
            System.out.println("Message not in buffer");
            return;
        }

        for (IMessage mess : arrMess) {
                user.receiveMessage(mess, this.nickName);
                if(mess == null) continue;
            System.out.println(mess);
        }
        this.messageBuffer.clearBuffer();
    }

    public void removeMessages(){
        this.messageBuffer.clearBuffer();
    }

    public void removeMessages(int i){
        this.messageBuffer.clearBuffer(i);
    }

    //should be private
    protected void receiveMessage(IMessage message, String senderName) {
        if(message == null) return;
        System.out.println(message);

        try {
            message.constructMessage(State.RECEIVER, this.nickName, senderName);
        } catch (IOException | DemotedMessageException e) {
            e.printStackTrace();
        }
    }


}
