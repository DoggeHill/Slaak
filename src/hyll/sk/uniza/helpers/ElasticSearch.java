package hyll.sk.uniza.helpers;

import hyll.sk.uniza.users.User;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;


/**
 * Elastic seaches search elastically everything
 * I learned how to play an audio file from here: https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
 * I learned how to display a picture file from here: https://stackoverflow.com/questions/14353302/displaying-image-in-java
 * @author patri
 */
public class ElasticSearch {
    static Date date;

    static {
        date = new Date();
    }


    /**
     * Find all messages sent by one user
     *
     * @param user that user
     */
    public static void findAllMessages(User user) {
        String[] paths = DatabaseLoader.allPaths();
        for (String path : paths) {
            Scanner scan = null;
            try {
                scan = new Scanner(new File(path + ".txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scan.hasNext()) {
                String line = scan.nextLine().toLowerCase().toString();
                String[] lines = line.split(" ");
                //System.out.println(user.getName());
                //System.out.println(lines[1]);
                if (lines[1].toUpperCase().equals(user.getName().toUpperCase())) {
                    System.out.println(lines[4]);
                }
            }

        }
    }

    /**
     * Find all messages by content
     *
     * @param user    that user
     * @param content content
     */
    public static void findAllMessagesByContent(User user, String content) {
        String[] paths = DatabaseLoader.allPaths();
        for (String path : paths) {
            Scanner scan = null;
            try {
                scan = new Scanner(new File(path + ".txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scan.hasNext()) {
                String line = scan.nextLine().toLowerCase().toString();
                String[] lines = line.split(" ");
                //System.out.println(user.getName());
                //System.out.println(lines[1]);
                if (lines[1].toUpperCase().equals(user.getName().toUpperCase())) {
                    if (lines[4].toUpperCase().equals(content.toUpperCase())) {
                        System.out.println(lines[4] + " at " + new Date(Long.parseLong(lines[6].trim())));

                    }
                }
            }

        }
    }

    /**
     * Tries to open a message
     *
     * @param user
     * @param content
     * @param type
     */
    public static void openMessage(User user, String content, MessageType type) {
        if (user == null) {
            System.out.println("User is null");
            return;
        }
        switch (type) {
            case AUDIO:
                //since we cannot name voice messages only the last one from the user will be played
                String pathA = DatabaseLoader.allPaths()[1];
                Scanner scanA = null;
                try {
                    scanA = new Scanner(new File(pathA + ".txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String last = "";
                while (scanA.hasNext()) {
                    String line = scanA.nextLine().toLowerCase().toString();
                    // System.out.println(line);
                    String[] lines = line.split(" ");

                    if (lines[1].toUpperCase().equals(user.getName().toUpperCase())) {
                        last = lines[8];
                    }
                }
                if (last.isEmpty()) {
                    System.out.println("Not found 😥");
                    return;
                } else {
                    System.out.println(pathA + "\\" + last + ".wav");
                    File f = new File(pathA + "\\" + last + ".wav");

                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(f));
                        // ...
                        clip.start();
                        while (!clip.isRunning())
                            Thread.sleep(10);
                        while (clip.isRunning())
                            Thread.sleep(10);
                        clip.close();
                    } catch (Exception exc) {
                        exc.printStackTrace(System.out);
                    }
                }
                break;

            case PICTURE:
                String path = DatabaseLoader.allPaths()[2];
                Scanner scan = null;
                try {
                    scan = new Scanner(new File(path + ".txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (scan.hasNext()) {
                    String line = scan.nextLine().toLowerCase().toString();
                    String[] lines = line.split(" ");

                    if (lines[1].toUpperCase().equals(user.getName().toUpperCase())) {
                        if (lines[5].toUpperCase().equals(content.toUpperCase())) {
                            System.out.println(lines[5]);
                            String image = lines[7] + ".png";
                            System.out.println(path + "\\" + image);

                            try {
                                Image picture = ImageIO.read(new File(path + "\\" + image));
                                JFrame editorFrame = new JFrame("Image message");
                                editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                ImageIcon imageIcon = new ImageIcon(picture);
                                JLabel jLabel = new JLabel();
                                jLabel.setIcon(imageIcon);
                                editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

                                editorFrame.pack();
                                editorFrame.setLocationRelativeTo(null);
                                editorFrame.setVisible(true);
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                        } else {
                            System.out.println("Not found 😥");
                        }
                    } else {
                        System.out.println("Not found 😥");
                    }
                }
                break;
            default:
                findAllMessagesByContent(user, content);
        }

    }

    /**
     * Search through all user's message and compare date of the message to the param
     * @param user
     * @param day
     */
    public static void searchByDate(User user, String day) {
        long offset = 0;
        if (day.equals("today")) {
            offset = 86400000L;
        }
        if (day.equals("yesterday")) {
            offset = (2 * 86400000L);
        }

        String[] paths = DatabaseLoader.allPaths();
        for (String path : paths) {
            Scanner scan = null;
            try {
                scan = new Scanner(new File(path + ".txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scan.hasNext()) {

                String line = scan.nextLine().toLowerCase().toString();



                String[] lines = line.split(" ");
                long date2 = Long.parseLong(lines[lines.length - 1]);

                if (date.getTime() - date2 < offset) {
                    if(!(line.contains(user.getName().toLowerCase()))){
                        continue;
                    }
                    System.out.println(lines[4]);
                }
                //System.out.println(user.getName());
                //System.out.println(lines[1]);

            }

        }

    }

   /*
    public static void main(String[] args) {
        User Fero = new BasicUser("Fero");
        ElasticSearch.findAllMessages(Fero);
        ElasticSearch.findAllMessagesByContent(Fero, "ahoj");
        ElasticSearch.openMessage(Fero, "", AUDIO);
    }
    */

}
