package hyll.sk.uniza.helpers;

import hyll.sk.uniza.messages.IMessage;
import hyll.sk.uniza.messages.TextMessage;

import java.util.ArrayList;

/**
 * Message buffer is buffer for fype of IMessage TextMessage used for temporary message storing
 *
 * @param <E> type of message ot be stored in the buffer(safety reasons)
 * @author patri
 */
public class MessageBuffer<E extends TextMessage> {
    private final int size;
    private final ArrayList<E> messages;

    public MessageBuffer(int size) {
        this.size = size;
        this.messages = new ArrayList<>();
    }

    public void saveMessageToBuffer(E message) {
        if (this.messages.size() >= this.size) {
            System.out.println("Buffer full! not saved to buffer, clear buffer first!");
            return;
        }
        this.messages.add(message);
        System.out.println("Saved to buffer...");

    }

    /**
     * Clears user's entire buffer
     */
    public void clearBuffer() {
        this.messages.clear();
    }

    /**
     * Clear specific message in buffer
     * @param i position
     */
    public void clearBuffer(int i) {
        if (i > this.messages.size()) {
            System.out.println("Invalid number");
            return;
        }
        this.messages.remove(i);
    }

    /**
     *
     * @return array of messages in the buffer
     */
    public TextMessage[] getMessages() {
        TextMessage[] tx = new TextMessage[this.size];
        byte ittCnt = 0;
        for (IMessage ignored : this.messages) {
            tx[ittCnt] = this.messages.get(ittCnt++);
        }
        return tx;
    }
}
