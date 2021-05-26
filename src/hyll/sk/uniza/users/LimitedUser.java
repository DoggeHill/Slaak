package hyll.sk.uniza.users;


/**
 * Class to represent limited user
 * Limitations:
 *      same as basic user
 *      can send only 10 messages
 * @author patri
 */

public class LimitedUser extends BasicUser {
    private static final byte LIMIT = 2;

    public LimitedUser(String nickName) {
        super(nickName);
        //super.setBufferSize(2);

    }

    public void sendMessage(User user) {
        if (user.numberOfAcceptedMessages() >= LIMIT) {
            System.out.println("You have exceeded limit in your trial...");
        } else {
            super.sendMessage(user);
        }


    }
    @Override
    protected int getLimit(){
        return LIMIT;
    }


}
