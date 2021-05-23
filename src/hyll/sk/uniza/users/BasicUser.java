package hyll.sk.uniza.users;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.messages.TextMessage;
import hyll.sk.uniza.messages.VoiceMessage;

import java.io.IOException;

/**
 * Class to represent basic user
 * Limitations:
 *      Buffer size:5
 *      Can send only texts
 * @author patri
 */
public class BasicUser extends User {

    public BasicUser(String nickName) {
        super(nickName);
        super.setBufferSize(5);
    }

    public void sendMessage(User user) {
        if (!(super.getMessageToSend() instanceof TextMessage)) {
            try {
                //we need to delete those files, because we have created them but had not sent
                if (super.getMessageToSend() instanceof VoiceMessage) {
                    DatabaseLoader.deleteAudioFile();
                } else {
                    DatabaseLoader.deletepictureFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new IllegalArgumentException("This user can only send texts... ");
        } else {
            super.sendMessage(user);
        }
    }

    @Override
    protected int getLimit() {
        return -1;
    }


}
