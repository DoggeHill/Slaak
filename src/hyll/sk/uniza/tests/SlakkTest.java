package hyll.sk.uniza.tests;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.messages.*;
import hyll.sk.uniza.users.BasicUser;
import hyll.sk.uniza.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SlakkTest {
    Date date = new Date();

    WelcomeMessage wm = new WelcomeMessage("ahoj");
    private Object IOException;

    SlakkTest() throws IOException {
    }

    @Test
    @DisplayName("Welcome message test")
    public void testMultiply() {
        assertEquals("ahoj", wm.getFormat(),
                "Regular multiplication should work");
    }


    @Test
    @DisplayName("Welcome message test")
    public void testMessageTypes() {
        assertEquals("ahoj", wm.getFormat(),
                "Regular multiplication should work");

    }

    /*
    IMessage textMessage = new TextMessage("content", 0);
    IMessage voiceMessage = new VoiceMessage(25, 0);
    IMessage picture = new Pic("content", 0);
    IMessage video = new Video("content", 0);

    IUser Fero = new User();
*/
    @Test
    @DisplayName("Database")
    public void loadDatabases() throws IOException {
      /*  Assertions.assertThrows(IOException.class, () -> {
            DatabseLoader.loadDatabase("nsdf");
        });
*/
        Assertions.assertThrows(IOException.class, () -> {
            DatabaseLoader.loadDatabase("texts.txt");
        });
    }

    @Test
    @DisplayName("Basic messaging 1 message")
    public void doTextMessage() throws IOException {
        Date date = new Date();

        User Jano= new BasicUser("Jano");
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.sendMessages(Jano);

    }

    @Test
    @DisplayName("Buffer testing 1 message")
    public void bufferTest() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        String output = (((User) Fero).getUsersMessageBuffer());
        Assertions.assertEquals("content", output);

    }

    @Test
    @DisplayName("Buffer testing 2 message")
    public void bufferTest2() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.createMessage(new TextMessage("content2", date.getTime()));
        String output = (((User) Fero).getUsersMessageBuffer());

        Assertions.assertEquals("content content2 ", output);

    }

    @Test
    @DisplayName("Buffer testing 5 message")
    public void bufferTest5() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.createMessage(new TextMessage("content2", date.getTime()));
        Fero.createMessage(new TextMessage("content3", date.getTime()));
        Fero.createMessage(new TextMessage("content4", date.getTime()));
        Fero.createMessage(new TextMessage("content5", date.getTime()));

        String output = (((User) Fero).getUsersMessageBuffer());

        Assertions.assertEquals("content content2 content3 content4 content5 ", output);

    }

    @Test
    @DisplayName("Buffer testing 2 users")
    public void bufferTest2Users() {
        User Fero = new BasicUser("Fero");
        User Jano = new BasicUser("Jano");

        Fero.createMessage(new TextMessage("contentFero", date.getTime()));
        Fero.createMessage(new TextMessage("content2Fero", date.getTime()));

       Jano.createMessage(new TextMessage("contentJano", date.getTime()));
       Jano.createMessage(new TextMessage("content2Jano", date.getTime()));



        String output = (((User) Fero).getUsersMessageBuffer());
        String output2 = (((User) Jano).getUsersMessageBuffer());

        Assertions.assertEquals("contentFero content2Fero ", output);
        Assertions.assertEquals("contentJano content2Jano ", output2);

    }

    @Test
    @DisplayName("Buffer testing after sending message")
    public void bufferTestSendMessage() {
        User Fero = new BasicUser("Fero");
        User Jano = new BasicUser("Jano");

        Fero.createMessage(new TextMessage("ahoj", date.getTime()));
        Fero.createMessage(new TextMessage("Jano", date.getTime()));

        Fero.sendMessages(Jano);
        String output = (((User) Fero).getUsersMessageBuffer());
        Assertions.assertEquals("", output);

    }

    @Test
    @DisplayName("Clear buffer prior")
    public void bufferTestRemoveMessage() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("ahoj", date.getTime()));
        Fero.createMessage(new TextMessage("Jano", date.getTime()));

        ((User) Fero).removeMessages();


        String output = (((User) Fero).getUsersMessageBuffer());
        Assertions.assertEquals("", output);

    }

    @Test
    @DisplayName("Clear buffer prior")
    public void bufferTestRemoveFirstLastMessage() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("ahoj", date.getTime()));
        Fero.createMessage(new TextMessage("Jano", date.getTime()));
        Fero.createMessage(new TextMessage("ako", date.getTime()));
        Fero.createMessage(new TextMessage("sa", date.getTime()));
        Fero.createMessage(new TextMessage("mas", date.getTime()));

        ((User) Fero).removeMessages(0);
        ((User) Fero).removeMessages(3);


        String output = (((User) Fero).getUsersMessageBuffer());
        Assertions.assertEquals("Jano ako sa ", output);

    }

    @Test
    @DisplayName("Clear buffer prior")
    public void bufferTestRemoveInvalidMessage() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("ahoj", date.getTime()));
        Fero.createMessage(new TextMessage("Jano", date.getTime()));


        ((User) Fero).removeMessages(0);
        ((User) Fero).removeMessages(3);


        String output = (((User) Fero).getUsersMessageBuffer());
        Assertions.assertEquals("Jano ", output);

    }




}