package hyll.sk.uniza;

import hyll.sk.uniza.Slakk.Slakk;
import hyll.sk.uniza.controllers.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
/*
Postava ako vlastna trieda NPC,
Postavy sú v miestnosti - pridavanie do miestnosti, výpis NPC v miestnosti
príkaz "rozpravaj / oslov" "osolov MenoPostavy"
ma metodu zacniRozhovor

Kucharka - je v bufete
oslov kucharka

Ahoj, ja som Kuchárka.

1) Kupit jedlo
2) Ist preč

> 1

Na predaj mám:

1) Keksy
2) Bageta
3) Kava
4) Navleky
5) Ist preč

> 2

Do inventára ti bola pridaná bageta.

Chceš este nieco?

1) Ano
2) Nie (ist prec)

> 2

Cau






 */


/*
  Predmety (item)
    - Budu v miestnostiach
    - Budu sa dať použiť
    - Zobrať
    - Inventár
  Ulohy / questy
    -
  Teleport
  Vytah
  Postavy /NPC/
    - Daju ulohy
    - Boj

  Multiplayer
 */


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
    public static void main(String[] args) throws FileNotFoundException {
        Parser parser = new Parser();

        Slakk hra = new Slakk(parser);
        hra.runSlakk();
    }
}
