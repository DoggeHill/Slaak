package hyll.sk.uniza.controllers;
/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */

public class CommandNames {
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "create", "showUsers", "pomoc", "sendText", "debug", "createText", "sendTextAll", "oslov"
    };

    /**
     * @return true ak je parameter platny prikaz,
     * false inak.
     */
    public boolean jePrikaz(String nazov) {
        for (int i = 0; i < CommandNames.PLATNE_PRIKAZY.length; i++) {
            if (CommandNames.PLATNE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        return false;
    }
}
