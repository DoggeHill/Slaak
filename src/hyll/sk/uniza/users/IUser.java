package hyll.sk.uniza.users;

import hyll.sk.uniza.messages.IMessage;

public interface IUser {
    String getName();
    int numberOfAcceptedMessages();
    void sendMessage(IMessage message);
    void createMessage(IMessage message);
    void receiveMessage(IMessage message);
    void sendMessages(IUser user);
}
