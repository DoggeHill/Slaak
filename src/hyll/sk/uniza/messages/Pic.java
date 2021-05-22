package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.helpers.State;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pic implements IMessage, IImage {

    private final String content;
    private final long timeStamp;
    private float size;
    private float dimension;

    public Pic(String content, long timeStamp) {
        this.content = content;
        this.timeStamp = timeStamp;
        this.size = 0;
        this.dimension = 0;
    }

    @Override
    public String getFormat() {
        return "Picture";
    }

    @Override
    public long getTimeStamp() {
        return this.timeStamp;
    }

    @Override
    public void constructMessage(State state, String nickName, String senderName) throws IOException {
        try {
            Robot robot = new Robot();

            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
            File file = DatabaseLoader.pictureFile();
            boolean status = ImageIO.write(bufferedImage, "png", file);
            this.size = file.length();
            this.dimension = bufferedImage.getHeight();
            System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());

        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }

        FileWriter fw = loadDatabase();
        fw.write(   "from: " + senderName + " to: " + nickName + " type: img size:" + this.size + " at: " + this.timeStamp + "\r\n");
        System.out.println("Storing message: " + this.getFormat() + " into database");
        fw.close();
    }

    @Override
    public FileWriter loadDatabase() throws IOException {
        return DatabaseLoader.loadDatabase("pics.txt");
    }


    @Override
    public float getDimension() {
        return this.dimension;
    }

    @Override
    public float getSize() {
        return this.size;
    }

}
