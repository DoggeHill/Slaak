package hyll.sk.uniza.messages;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VoiceMessage implements IMessage {
    private final long recordTime;
    private final long timeStamp;

    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    //TODO: create dynamically
    // path of the wav file
    private File wavFile;


    // the line from which audio data is captured
    TargetDataLine line;


    public VoiceMessage(long length, long timeStamp) throws IOException {
        this.recordTime = length;
        this.timeStamp = timeStamp;
        this.wavFile = DatabaseLoader.audioFile();
    }


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


    @Override
    public String getFormat() {
        return String.valueOf(this.recordTime);
    }

    @Override
    public long getTimeStamp() {
        return this.timeStamp;
    }

    @Override
    public void constructMessage(State state, String nickName) throws IOException {
        // creates a new thread that waits for a specified
        // of time before stopping
        // start recording

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
        System.out.println("here");
        stopper.start();
        this.start();



        // start recording

        FileWriter fw = loadDatabase();
        fw.write(nickName + "   voice  " + this.timeStamp + "\r\n");
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
            /*
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }*/

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
