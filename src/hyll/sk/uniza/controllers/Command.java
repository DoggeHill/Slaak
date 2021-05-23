package hyll.sk.uniza.controllers;

/**
 * Trieda prikaz implemntuje casti prikazu, ktore moze hrac zadat.
 * V tejto verzii prikaz tvoria dve slova: nazov prikazu a druhe slovo.
 * Napriklad prikaz "chod juh" tvoria dva retazce "chod" ako nazov prikazu a
 * "juh" ako oznacenie smeru.
 * 
 * Predpoklada sa, ze prikaz je skontrolovany, t.j., ze nazov prikazu je znamy.
 * Pre neznamy prikaz je jeho nazov nastaveny na hodnotu <null>.
 *
 * Ak prikaz nema parameter, potom ma druhe slovo hodnotu <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 *
 * @author patri made a few changes
 */

public class Command {
    private final String nazovPrikazu;
    private final String parameter;
    private final String parameter2;
    private final String parameter3;

    /**
     * Inicializuje slova prikazu dvomi zadanymi parametrami. Jeden alebo oba
     * parametre mozu mat hodnotu <null>.
     * Overridden to support multiple cases
     */


    public Command(String nazovPrikazu, String parameter) {
        this.nazovPrikazu = nazovPrikazu;
        this.parameter = parameter;
        this.parameter2 = null;
        this.parameter3 = null;
    }
    public Command(String nazovPrikazu, String parameter, String parameter2) {
        this.nazovPrikazu = nazovPrikazu;
        this.parameter = parameter;
        this.parameter2 = parameter2;
        this.parameter3 = null;

    }
    public Command(String nazovPrikazu, String parameter, String parameter2, String parameter3) {
        this.nazovPrikazu = nazovPrikazu;
        this.parameter = parameter;
        this.parameter2 = parameter2;
        this.parameter3 = parameter3;

    }
    /**
     * @return prve slovo - nazov prikazu.
     */
    public String getNazov() {
        return this.nazovPrikazu;
    }

    /**
     * @return druhe slovo - parameter prikazu.
     */
    public String getParameter() {
        return this.parameter;
    }

    /**
     * @return tretie slovo - parameter2 prikazu.
     */
    public String getParameter2() {
        return this.parameter2;
    }

    /**
     * @return stvrte slovo - parameter3 prikazu.
     */
    public String getParameter3() {
        return this.parameter3;
    }

    /**
     * @return true, ak je prikaz neznamy.
     */
    public boolean jeNeznamy() {
        return this.nazovPrikazu == null;
    }

    /**
     * @return true, ak prikaz ma parameter.
     */
    public boolean maParameter() {
        return this.parameter != null;
    }
    public boolean maParameter2() {
        return this.parameter2 != null;
    }
    public boolean maParameter3() {
        return this.parameter3 != null;
    }
}
