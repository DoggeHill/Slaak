package hyll.sk.uniza.users;

import hyll.sk.uniza.Slakk.DemotedMessageException;
import hyll.sk.uniza.messages.*;
import org.w3c.dom.Text;

import java.io.IOException;


public class User implements IUser {
    //this may be abstract?

    MessageBuffer messageBuffer;
    private String nickName;

    public User() {
        this.messageBuffer = new MessageBuffer(3);
        this.nickName = "Fero";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int numberOfAcceptedMessages() {
        return 0;
    }

    @Override
    public void sendMessage(IMessage message) {

    }


    @Override
    public void createMessage(IMessage message) {
        //Video is like IG Rails, will not be saved

        //TODO: this will be responsibility of MessageBuffer
        if (!(message instanceof IImage)) {
            try {
                message.constructMessage(State.SENDER, this.nickName);
            } catch (IOException | DemotedMessageException e) {
                e.printStackTrace();
            }
        }

        this.messageBuffer.saveMessageToBuffer(message);
    }


    public void sendMessages(IUser user) {
        TextMessage arrMess[] = null;
        try {
            arrMess = this.messageBuffer.getMessages();
        } catch(Exception e){
            System.out.println("Message not in buffer");
            return;
        }
        for (IMessage mess : arrMess) {
            if (mess instanceof TextMessage) {
                user.receiveMessage(mess);
            }
        }
    }
    //TODO: this will be overriden by method with argument message bcs of buffer

    //should be private
    public void receiveMessage(IMessage message) {
        try {
            message.constructMessage(State.RECEIVER, this.nickName);
        } catch (IOException | DemotedMessageException e) {
            e.printStackTrace();
        }
    }


}
