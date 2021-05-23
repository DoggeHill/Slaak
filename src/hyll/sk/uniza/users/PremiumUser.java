package hyll.sk.uniza.users;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.messages.TextMessage;

import java.io.IOException;

public class PremiumUser extends User {

    public PremiumUser(String nickName) {
        super(nickName);
        super.setBufferSize(99);

    }


    public void sendMessage(User user) {

        super.sendMessage(user);

    }

    @Override
    protected int getLimit() {
        return -1;
    }


}
