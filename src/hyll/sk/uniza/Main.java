package hyll.sk.uniza;

import hyll.sk.uniza.Slakk.Slakk;
import hyll.sk.uniza.controllers.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;


/**
 * Hlavna trieda hry WoF s metodou main - spustanie v NB
 *
 * @author Lubomir Sadlon
 * @version 21.2.2012
 */
public class Main {

    /**
     * @param args parametre programu
     */
    public static void main(String[] args) {
        Parser parser = new Parser();

        Slakk hra = new Slakk(parser);
        hra.runSlakk();
    }
}
