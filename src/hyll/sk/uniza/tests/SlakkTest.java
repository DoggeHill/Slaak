package hyll.sk.uniza.tests;

import hyll.sk.uniza.messages.*;
import hyll.sk.uniza.users.IUser;
import hyll.sk.uniza.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SlakkTest {

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

    IMessage textMessage = new TextMessage("content", 0);
    IMessage voiceMessage = new VoiceMessage(25, 0);
    IMessage picture = new Pic("content", 0);
    IMessage video = new Video("content", 0);

    IUser Fero = new User();

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


}