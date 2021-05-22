package hyll.sk.uniza.users;

import hyll.sk.uniza.messages.IMessage;

public class UserManager implements IUser{

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

    }

    @Override
    public void receiveMessage(IMessage message) {

    }

    @Override
    public void sendMessages(IUser user) {

    }

}
