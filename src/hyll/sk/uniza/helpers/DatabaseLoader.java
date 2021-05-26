package hyll.sk.uniza.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Connect my app to the local txt files representing the database
 *
 * @author patri
 * @version 2021
 */

public class DatabaseLoader {

    //Constants
    private static final String ROOT_PATH = "C:\\Users\\patri.DESKTOP-FFNU79O\\dev\\java\\Slaak\\src\\hyll\\sk\\uniza\\almostDatabaze\\";
    private static final String TEXT_PATH = ROOT_PATH + "texts";
    private static final String AUDIO_PATH = ROOT_PATH + "voices";
    private static final String PIC_PATH = ROOT_PATH + "pics";
    private static final String VIDEO_PATH = ROOT_PATH + "videos";

    /**
     * Returns Filewriter attached to the needed database
     *
     * @param path path to Database
     * @return FileWriter
     * @throws IOException e
     */
    public static FileWriter loadDatabase(String path) throws IOException {
        File f = new File(ROOT_PATH + path);
        FileWriter fw;
        if (f.exists()) {
            fw = new FileWriter(f, true);
        } else {
            throw new IOException("Database not connected");
        }
        return fw;
    }

    /**
     * @return all paths used
     */
    public static String[] allPaths() {
        return new String[]{TEXT_PATH, AUDIO_PATH, PIC_PATH, VIDEO_PATH};
    }

    /**
     * Returns a position in the folder for a file type
     * e.g. if 5.wav was the last wav file and the new one is being created
     * we return 6
     *
     * @param e message type
     * @return position
     */
    public static int getPosition(MessageType e) {
        String actualPath = switch (e) {
            case AUDIO -> AUDIO_PATH;
            case PICTURE -> PIC_PATH;
            case VIDEO -> VIDEO_PATH;
            default -> TEXT_PATH;
        };
        File file = new File(actualPath);

        //Name of the last file
        String lastName = "";

        if (file.listFiles() == null) {
            return 0;
        }

        for (File f : file.listFiles()) {
            lastName = f.getName();
        }

        //Split by dot
        String[] nameWithEx = lastName.split("\\.");

        if (nameWithEx[0].equals("")) {
            return 0;
        }

        int numberInRow = Integer.parseInt(nameWithEx[0]);
        numberInRow++;
        return numberInRow;
    }

    /**
     * @return new Audio file
     * @throws IOException er
     */
    public static File audioFile() throws IOException {
        File file;
        file = new File(AUDIO_PATH + "\\" + getPosition(MessageType.AUDIO) + ".wav");
        file.createNewFile();
        return file;
    }

    /**
     *
     * @throws IOException err
     */
    public static void deleteAudioFile() throws IOException {
        File file;
        file = new File(AUDIO_PATH + "\\" + (getPosition(MessageType.AUDIO)-1) + ".wav");
        file.delete();
    }


    //Same as audio files

    public static File pictureFile() throws IOException {
        File file;
        file = new File(PIC_PATH + "\\" + getPosition(MessageType.PICTURE) + ".png");
        file.createNewFile();
        return file;
    }

    public static void deletepictureFile() throws IOException {
        File file;
        file = new File(PIC_PATH + "\\" + (getPosition(MessageType.PICTURE)-1) + ".png");
        file.delete();
    }


}
