package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.helpers.MessageType;
import hyll.sk.uniza.helpers.State;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Voicemessage object
 * Voice record is a representation of the voice message. Voice recording mechanism is inspired by:
 * https://www.codejava.net/coding/capture-and-record-sound-into-wav-file-with-java-sound-api
 *
 * @author patri
 */
public class VoiceMessage implements IMessage {
    private final long recordTime;
    private final long timeStamp;
    private final int orderNumber;
    private final File wavFile;

    /**
     * Defines an audio format
     */
    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
    }

    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    // the line from which audio data is captured
    TargetDataLine line;

    public VoiceMessage(long length, long timeStamp) throws IOException {
        //convert ms to sec
        this.recordTime = length * 1000;
        this.timeStamp = timeStamp;
        this.wavFile = DatabaseLoader.audioFile();
        this.orderNumber = DatabaseLoader.getPosition(MessageType.AUDIO);
    }


    @Override
    public String getFormat() {
        return ":" + this.recordTime + "  " + this.orderNumber;
    }

    @Override
    public long getTimeStamp() {
        return this.timeStamp;
    }

    @Override
    public void constructMessage(State state, String nickName, String senderName) throws IOException {

        //Create threat and in the end stop audio recorder
        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(recordTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.line.stop();
            this.line.close();
            System.out.println("Finished");
        });

        stopper.start();
        this.start();

        //store message in to the voice message database
        FileWriter fw = this.loadDatabase();
        fw.write("from: " + senderName + " to: " + nickName + " voice length:" + this.recordTime + " order number: " + (this.orderNumber - 1) + " at: " + this.timeStamp + "\r\n");
        System.out.println("Storing message: " + this.getFormat() + " into database");
        fw.close();
    }

    @Override
    public FileWriter loadDatabase() throws IOException {
        return DatabaseLoader.loadDatabase("voices.txt");
    }

    private void start() {
        try {
            AudioFormat format = this.getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            this.line = (TargetDataLine) AudioSystem.getLine(info);
            this.line.open(format);
            this.line.start();   // start capturing

            System.out.println("Start capturing...");
            AudioInputStream ais = new AudioInputStream(line);
            System.out.println("Start recording...");

            // start recording
            AudioSystem.write(ais, this.fileType, this.wavFile);

        } catch (LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
    }


}
