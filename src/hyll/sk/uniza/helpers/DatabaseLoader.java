package hyll.sk.uniza.helpers;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseLoader {
    private static final String ROOT_PATH = "C:\\Users\\patri.DESKTOP-FFNU79O\\dev\\java\\Slaak\\src\\hyll\\sk\\uniza\\almostDatabaze\\";
    private static final String AUDIO_PATH = ROOT_PATH + "voices";
    private static final String PIC_PATH = ROOT_PATH + "pics";
    private static final String VIDEO_PATH = ROOT_PATH + "videos";

    public static FileWriter loadDatabase(String path) throws IOException {
        File f = new File(ROOT_PATH + path);
        FileWriter fw;
        if (f.exists()) {
            fw = new FileWriter(f, true);
        } else {
            throw new IOException("Database not connected");
        }
        System.out.println("Writing");
        return fw;
    }

    public static int getPosition(MessageType e) {
        String actualPath = "";
        switch (e) {
            case AUDIO:
                actualPath = AUDIO_PATH;
                break;
            case PCTURE:
                actualPath = PIC_PATH;
                break;
            case VIDEO:
                actualPath = VIDEO_PATH;
                break;
        }
        File file = new File(actualPath);
        String lastName = "";
        for (File f : file.listFiles()) {
            lastName = f.getName();
        }
        System.out.println(lastName);
        String nameWithEx[] = lastName.split("\\.");
        int numberInRow = Integer.parseInt(nameWithEx[0]);
        numberInRow++;
        System.out.println(numberInRow);
        return numberInRow;
    }

    public static File audioFile() throws IOException {
        File file;
        file = new File(AUDIO_PATH + "\\" + getPosition(MessageType.AUDIO) + ".wav");
        file.createNewFile();
        return file;
    }

    public static File pictureFile() throws IOException {
        File file;
        file = new File(PIC_PATH + "\\" + getPosition(MessageType.PCTURE) + ".png");
        return file;
    }

    /*

    SCAN FILE
         File actual = new File(".");
        for( File f : actual.listFiles()){
            System.out.println( f.getName() );
        }
        return actual;


     */
}
