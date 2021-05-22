package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.helpers.MessageType;
import hyll.sk.uniza.helpers.State;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VoiceMessage implements IMessage {
    private final long recordTime;
    private final long timeStamp;
    private final int orderNumber;
    private final File wavFile;



    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    /**
     * Defines an audio format
     */
    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    // the line from which audio data is captured
    TargetDataLine line;


    public VoiceMessage(long length, long timeStamp) throws IOException {
        this.recordTime = length;
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
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(recordTime);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                line.stop();
                line.close();
                System.out.println("Finished");
            }
        });

        stopper.start();
        this.start();

        FileWriter fw = loadDatabase();
        fw.write("from: " + senderName + " to: " + nickName + " type: voice length:" + this.recordTime + " at: " + this.timeStamp + "\r\n");
        System.out.println("Storing message: " + this.getFormat() + " into database");
        fw.close();
    }

    @Override
    public FileWriter loadDatabase() throws IOException {
        return DatabaseLoader.loadDatabase("voices.txt");
    }

    void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            System.out.println("Start capturing...");
            AudioInputStream ais = new AudioInputStream(line);
            System.out.println("Start recording...");

            // start recording
            AudioSystem.write(ais, fileType, wavFile);

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}
