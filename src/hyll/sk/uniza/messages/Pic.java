package hyll.sk.uniza.messages;

import hyll.sk.uniza.helpers.DatabaseLoader;
import hyll.sk.uniza.helpers.MessageType;
import hyll.sk.uniza.helpers.State;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Picture message object
 * PrintScreen is a representation of the image message. PrtScrn mechanism is inspired by:
 * https://www.technicalkeeda.com/java-tutorials/screen-capture-using-java
 *
 * @author patri
 */
public class Pic implements IMessage, IImage {

    private final String name;
    private final long timeStamp;
    private float size;
    private float dimension;
    private final int orderNumber;

    public Pic(String content, long timeStamp) {
        this.name = content;
        this.timeStamp = timeStamp;
        this.size = 0;
        this.dimension = 0;
        this.orderNumber = DatabaseLoader.getPosition(MessageType.PICTURE);
    }

    @Override
    public String getFormat() {
        return "Picture";
    }

    @Override
    public long getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * Create a print screen, save this printscreen into the files and create a record in the image database
     * @param state of the message
     * @param nickName name of the user
     * @param senderName name of the receiver
     * @throws IOException err
     */
    @Override
    public void constructMessage(State state, String nickName, String senderName) throws IOException {
        try {
            Robot robot = new Robot();

            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
            File file = DatabaseLoader.pictureFile();
            //boolean status = ImageIO.write(bufferedImage, "png", file);
            //System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());

            this.size = file.length();
            this.dimension = bufferedImage.getHeight();
            System.out.println("Image captured");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }

        //Create record in the database
        FileWriter fw = loadDatabase();
        fw.write(   "from: " + senderName + " to: " + nickName + " name: " + this.name + " position: " + (this.orderNumber-1) + " size: " + this.size + " at: " + this.timeStamp + "\r\n");
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
