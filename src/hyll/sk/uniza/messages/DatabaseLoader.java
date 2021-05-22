package hyll.sk.uniza.messages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseLoader {
    private static final String ROOT_PATH = "C:\\Users\\Patrik\\IdeaProjects\\Slakk\\src\\hyll\\sk\\uniza\\almostDatabaze\\";
    private static final String AUDIO_PATH = ROOT_PATH + "voices";

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

    public static File audioFile() throws IOException {
        File file = new File(AUDIO_PATH);
        String lastName = "";
        for( File f : file.listFiles()){
            lastName =  f.getName();
        }
        System.out.println(lastName);
        String nameWithEx[] = lastName.split("\\.");
        int numberInRow = Integer.parseInt(nameWithEx[0]);
        numberInRow++;
        System.out.println(numberInRow);

        file = new File(AUDIO_PATH + "\\" +numberInRow + ".wav");
        file.createNewFile();

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
