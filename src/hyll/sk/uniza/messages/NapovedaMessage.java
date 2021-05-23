package hyll.sk.uniza.messages;


/**
 * Help message
 * @author patri
 */
public class NapovedaMessage extends AbstractMessage {

    private static final String content= "Welcome to Slaak help center \n" +
            "List of commands:\n" +
            "1. create [parameter][name]   creates user. B-> basic, L-> limited, P-> premium \n"+
            "2. showUsers show all the users\n" +
            "3. sendMessage [sender] [recipient] [parameter][content] T-> text V-> Voice P->Pic W->Video\n" +
            "4. sendAllTexts [sender] [recipient] send everything from buffer\n" +
            "5. createText creates text message and send it to the buffer\n" +
            "6. search seraches text messages:\n" +
            "   1. [user] displays all user's messages\n" +
            "   2. [user] [content] find all messages by content\n" +
            "   3. [user] [content] [messageType] T-> text V-> Voice P->Pic W->Video open certain message\n" +
            "\n Hoped I have helped.....";


    public NapovedaMessage() {
        super(content,-1);
    }

}
