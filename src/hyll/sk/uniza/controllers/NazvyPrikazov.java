package hyll.sk.uniza.controllers;
/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */

public class NazvyPrikazov {
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "ukonci", "pomoc", "zober", "poloz", "inventar", "pouzi", "oslov"
    };

    /**
     * @return true ak je parameter platny prikaz,
     * false inak.
     */
    public boolean jePrikaz(String nazov) {
        for (int i = 0; i < NazvyPrikazov.PLATNE_PRIKAZY.length; i++) {
            if (NazvyPrikazov.PLATNE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        return false;
    }
}
