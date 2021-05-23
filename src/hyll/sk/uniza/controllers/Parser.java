package hyll.sk.uniza.controllers;


import java.util.Scanner;

/**
 * Trieda sk.uniza.fri.worldOfFri.prikazy.Parser cita vstup zadany hracom do terminaloveho okna a pokusi sa
 * interpretovat ho ako prikaz hry. Kazda sprava dajPrikaz sposobi, ze parser
 * precita jeden riadok z terminaloveho okna a vyberie z neho prve dve slova.
 * Tie dve slova pouzije ako parametre v sprave new triede sk.uniza.fri.worldOfFri.prikazy.Prikaz.
 *
 * @author Michael Kolling and David J. Barnes
 * @author lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 *
 * @author patri made a few changes
 */
public class Parser {
    private final CommandNames prikazy;  // odkaz na pripustne nazvy prikazov
    private final Scanner citac;         // zdroj vstupov od hraca

    /**
     * Vytvori citac na citanie vstupov z terminaloveho okna.
     */
    public Parser() {
        this.prikazy = new CommandNames();
        this.citac = new Scanner(System.in);
    }

    /**
     * @return prikaz zadany hracom
     */
    public Command nacitajPrikaz() {
        System.out.print("> ");     // vyzva pre hraca na zadanie prikazu

        String vstupnyRiadok = this.citac.nextLine();

        String prikaz = null;
        String parameter = null;
        String parameter2 = null;
        String parameter3 = null;

        // najde prve dve slova v riadku 
        Scanner tokenizer = new Scanner(vstupnyRiadok);
        if (tokenizer.hasNext()) {
            prikaz = tokenizer.next();      // prve slovo
            if (tokenizer.hasNext()) {
                parameter = tokenizer.next();      // druhe slovo
            }
            if (tokenizer.hasNext()) {
                parameter2 = tokenizer.next();      // tretie slovo
            }
            if (tokenizer.hasNext()) {
                parameter3 = tokenizer.next();      // štvrte slovo
            }
        }

        // kontrola platnosti prikazu
        if (this.prikazy.jePrikaz(prikaz)) {
            // vytvori platny prikaz

            if(parameter3 != null){
                return new Command(prikaz, parameter, parameter2, parameter3);
            }
            return parameter2 == null ? new Command(prikaz, parameter, parameter2) : new Command(prikaz, parameter, parameter2);
        } else {
            // vytvori neplatny - "neznamy" prikaz
            return new Command(null, parameter, parameter2);
        }
    }
}
