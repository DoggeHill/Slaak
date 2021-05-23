package hyll.sk.uniza.users;

import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.MessageBuffer;
import hyll.sk.uniza.helpers.State;
import hyll.sk.uniza.messages.*;

import java.io.IOException;


/**
 * Class to represent abstract superclass user
 *
 * @author patri
 */

public abstract class User {

    private MessageBuffer<TextMessage> messageBuffer;
    private final String nickName;
    private IMessage messageToSend;
    private int numberSentMessages;

    protected abstract int getLimit();


    public User(String nickName) {
        this.nickName = nickName;
        this.messageToSend = null;
        this.numberSentMessages = 0;
    }

    public String getName() {
        return this.nickName;
    }

    protected void setBufferSize(int i) {
        this.messageBuffer = new MessageBuffer<>(i);
    }

    protected int numberOfAcceptedMessages() {
        return this.numberSentMessages;
    }

    /**
     * Send message to the user
     * @param user name
     */
    public void sendMessage(User user) {
        if(this.messageToSend == null){
            System.out.println("Nothing to send");
            return;
        }
        user.receiveMessage(this.messageToSend, this.nickName);
        this.numberSentMessages++;
    }

    protected IMessage getMessageToSend() {
        return this.messageToSend;
    }

    public void createMessage(IMessage message) {
        if (message instanceof TextMessage) {
            this.messageBuffer.saveMessageToBuffer((TextMessage) message);
            System.out.println("Saving to buffer");
        } else {
            this.messageToSend = message;
        }
    }

    /**
     * Core sending mechanism
     * @param user object
     */
    public void sendMessages(User user) {
        if (this.messageToSend != null) {
            user.receiveMessage(this.messageToSend, this.nickName);
            this.numberSentMessages++;
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
            if (user.getLimit() != -1 && this.numberSentMessages >= user.getLimit()) {
                System.out.println("You have exceeded limit in your trial...");
                this.numberSentMessages = 0;
                return;
            }
            this.numberSentMessages++;
            if (mess == null) continue;
            user.receiveMessage(mess, this.nickName);
            //System.out.println(mess);

        }
        this.removeMessages();
    }


    /**
     * Clear the buffer
     */
    public void removeMessages() {
        this.messageBuffer.clearBuffer();
    }

    /**
     * Delete specific item in the buffer
     * @param i item
     */
    public void removeMessages(int i) {
        this.messageBuffer.clearBuffer(i);
    }


    /**
     * Receive message and create what is needed
     * @param message IMessage
     * @param senderName user's name
     */
    protected void receiveMessage(IMessage message, String senderName) {
        if (message == null) {
            return;
        }
        //System.out.println(message);
        try {
            message.constructMessage(State.RECEIVER, this.nickName, senderName);
        } catch (IOException | DemotedMessageException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used for debugging and testing
     * @return buffer info
     */
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


}
