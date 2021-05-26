package hyll.sk.uniza.users;



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
