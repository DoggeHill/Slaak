package hyll.sk.uniza;

import hyll.sk.uniza.Slakk.Slakk;
import hyll.sk.uniza.controllers.Parser;

/**
 * Main class, runner
 * @author patri
 */
public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Slakk slakk = new Slakk(parser);
        slakk.runSlakk();
    }
}
