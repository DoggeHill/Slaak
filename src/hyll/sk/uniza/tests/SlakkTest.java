package hyll.sk.uniza.tests;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.messages.*;
import hyll.sk.uniza.users.BasicUser;
import hyll.sk.uniza.users.LimitedUser;
import hyll.sk.uniza.users.PremiumUser;
import hyll.sk.uniza.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing
 * @author patri
 */
class SlakkTest {
    Date date = new Date();

    WelcomeMessage wm = new WelcomeMessage("ahoj");
    private Object IOException;

    SlakkTest() {
    }

    @Test
    @DisplayName("Welcome message test")
    public void testWelcomeMessage() {
        assertEquals("ahoj", wm.getFormat()
        );
    }

    @Test
    @DisplayName("Database")
    public void loadDatabases() {
        Assertions.assertThrows(IOException.class, () -> {
            DatabaseLoader.loadDatabase("nsdf");
        });
        Assertions.assertThrows(IOException.class, () -> DatabaseLoader.loadDatabase("texts.txts"));
    }

    @Test
    @DisplayName("Basic messaging 1 message")
    public void doTextMessage() {
        Date date = new Date();

        User Jano = new BasicUser("Jano");
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.sendMessages(Jano);

    }

    @Test
    @DisplayName("Basic messaging 2 messages")
    public void doText2Message() {
        Date date = new Date();

        User Jano = new BasicUser("Jano");
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.createMessage(new TextMessage("content2", date.getTime()));
        Fero.sendMessages(Jano);

    }

    @Test
    @DisplayName("Basic messaging above buffer limit messages")
    public void doTextAboveLimitBufferMessage() {
        Date date = new Date();

        User Jano = new LimitedUser("Jano");
        User Fero = new LimitedUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.createMessage(new TextMessage("content1", date.getTime()));
        Fero.createMessage(new TextMessage("content2", date.getTime()));
        Fero.createMessage(new TextMessage("content3", date.getTime()));
        Fero.createMessage(new TextMessage("content4", date.getTime()));
        Fero.createMessage(new TextMessage("content5", date.getTime()));
        Fero.createMessage(new TextMessage("content6", date.getTime()));
        Fero.createMessage(new TextMessage("content7", date.getTime()));
        Fero.createMessage(new TextMessage("content8", date.getTime()));
        Fero.createMessage(new TextMessage("content9", date.getTime()));
        Fero.createMessage(new TextMessage("content10", date.getTime()));
        Fero.sendMessages(Jano);

    }


    @Test
    @DisplayName("Basic messaging above message limit messages")
    public void doTextAboveLimitMessage() {
        Date date = new Date();

        User Jano = new LimitedUser("Jano");
        User Fero = new LimitedUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.sendMessages(Jano);

        Fero.createMessage(new TextMessage("content1", date.getTime()));
        Fero.createMessage(new TextMessage("content2", date.getTime()));
        Fero.createMessage(new TextMessage("content3", date.getTime()));
        Fero.createMessage(new TextMessage("content4", date.getTime()));
        Fero.sendMessages(Jano);

        Fero.createMessage(new TextMessage("content5", date.getTime()));
        Fero.createMessage(new TextMessage("content6", date.getTime()));
        Fero.createMessage(new TextMessage("content7", date.getTime()));
        Fero.sendMessages(Jano);

        Fero.sendMessages(Jano);

    }

    @Test
    @DisplayName("Buffer testing 1 message")
    public void bufferTest() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        String output = (Fero.getUsersMessageBuffer());
        Assertions.assertEquals("content ", output);

    }

    @Test
    @DisplayName("Buffer testing 2 message")
    public void bufferTest2() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.createMessage(new TextMessage("content2", date.getTime()));
        String output = (Fero.getUsersMessageBuffer());

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

        String output = (Fero.getUsersMessageBuffer());

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
        String output = (Fero.getUsersMessageBuffer());
        Assertions.assertEquals("", output);

    }

    @Test
    @DisplayName("Clear buffer prior")
    public void bufferTestRemoveMessage() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("ahoj", date.getTime()));
        Fero.createMessage(new TextMessage("Jano", date.getTime()));

        Fero.removeMessages();


        String output = (Fero.getUsersMessageBuffer());
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

        Fero.removeMessages(0);
        Fero.removeMessages(3);


        String output = (Fero.getUsersMessageBuffer());
        Assertions.assertEquals("Jano ako sa ", output);

    }

    @Test
    @DisplayName("Clear buffer prior")
    public void bufferTestRemoveInvalidMessage() {
        User Fero = new BasicUser("Fero");

        Fero.createMessage(new TextMessage("ahoj", date.getTime()));
        Fero.createMessage(new TextMessage("Jano", date.getTime()));


        Fero.removeMessages(0);
        Fero.removeMessages(3);


        String output = (Fero.getUsersMessageBuffer());
        Assertions.assertEquals("Jano ", output);

    }

    @Test
    @DisplayName("Send voice message")
    public void sendVoiceMessageFail() {
        User Fero = new BasicUser("Fero");
        User Jano = new BasicUser("Jano");

        try {
            Fero.createMessage(new VoiceMessage(3, date.getTime()));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> Fero.sendMessage(Jano));


    }

    @Test
    @DisplayName("Send voice message")
    public void sendVoiceMessage() {
        User Fero = new PremiumUser("Fero");
        User Jano = new PremiumUser("Jano");

        try {
            Fero.createMessage(new VoiceMessage(7, date.getTime()));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        Fero.sendMessage(Jano);

    }

    @Test
    @DisplayName("Send image message")
    public void sendImageMessage() {
        User Fero = new PremiumUser("Fero");
        User Jano = new PremiumUser("Jano");

        Fero.createMessage(new Pic("wef", 0));
        Fero.sendMessage(Jano);

    }
    @Test
    @DisplayName("Send image ovveriden message")
    public void sendImageOverridenMessage() {
        User Fero = new PremiumUser("Fero");
        User Jano = new PremiumUser("Jano");

        Fero.createMessage(new Pic("wef", 0));
        Fero.createMessage(new Pic("wef2", 0));
        Fero.sendMessage(Jano);

    }

    @Test
    @DisplayName("Send video message")
    public void sendVideoMessage() {
        User Fero = new PremiumUser("Fero");
        User Jano = new PremiumUser("Jano");

        Fero.createMessage(new Video());
        Fero.sendMessage(Jano);

    }
}