package hyll.sk.uniza.users;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.messages.IMessage;
import hyll.sk.uniza.messages.TextMessage;

import java.io.IOException;

public class BasicUser extends User {

    public BasicUser(String nickName) {
        super(nickName);
    }


    public void sendMessage(User user) {
        if (!(super.getMessageToSend() instanceof TextMessage)) {
            try {
                DatabaseLoader.deleteAudioFile();
                //TODO: delete all types
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new IllegalArgumentException("This user can only send texts... ");
        } else {
            super.sendMessage(user);
        }
    }
}
