package hyll.sk.uniza.messages;


/**
 * Help message
 * @author patri
 */
public class NapovedaMessage extends AbstractMessage {

    private static final String content= """
            Welcome to Slaak help center\s
            List of commands:
            1. create [parameter][name]   creates user. B-> basic, L-> limited, P-> premium\s
            2. showUsers show all the users
            3. sendMessage [sender] [recipient] [parameter][content] T-> text V-> Voice P->Pic W->Video
            4. sendAllTexts [sender] [recipient] send everything from buffer
            5. createText creates text message and send it to the buffer
            6. search seraches text messages:
               1. [user] displays all user's messages
               2. [user] [content] find all messages by content
               3. [user] [content] [messageType] T-> text V-> Voice P->Pic W->Video open certain message
            7. searchDate [user] [today/yesterday] searches today's, yesterday's messages of some user
             Hoped I have helped.....""";

    public NapovedaMessage() {
        super(content,-1);
    }
}
