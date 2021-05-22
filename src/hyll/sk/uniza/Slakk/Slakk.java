package hyll.sk.uniza.Slakk;

import hyll.sk.uniza.controllers.*;
import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.State;
import hyll.sk.uniza.messages.*;
import hyll.sk.uniza.users.*;


import java.io.IOException;
import java.util.Date;

public class Slakk {
    private final Parser parser;

    public Slakk(Parser parser) {
        this.parser = parser;
    }

    /**
     * Hlavna metoda hry.
     * Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void runSlakk() {

        //Demoted messages-> lower in the hierarchy
        //Elevated messages-> classic ones

        //Create demoted message
        IMessage welcomeMessage = new WelcomeMessage("Welcome to Slaak");
        ((WelcomeMessage) welcomeMessage).printString();

        try {
            welcomeMessage.constructMessage(State.SENDER, "", "");
        } catch (IOException | DemotedMessageException e) {
            e.printStackTrace();
        }


        IMessage napovedaMessage = new NapovedaMessage();
        try {
            napovedaMessage.constructMessage(State.SENDER, "", "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DemotedMessageException e) {
            e.printStackTrace();
        }



        Date date = new Date();

        IUser Jano = new User("Jano");
        IUser Fero = new User("Fero");


        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.sendMessages(Jano);



        IMessage voiceMessage = null;
        try {
            voiceMessage = new VoiceMessage(5000, 6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Fero.createMessage(voiceMessage);



        /*
        boolean jeKoniec;

        do {
            Prikaz prikaz = this.parser.nacitajPrikaz();
            jeKoniec = this.vykonajPrikaz(prikaz);
        } while (!jeKoniec);

        System.out.println("Give us 5 stars rating on UnizaPlayStore");

        */
    }


    /**
     * Prevezne prikaz a vykona ho.
     *
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    private boolean vykonajPrikaz(Prikaz prikaz) {
        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();

        switch (nazovPrikazu) {
            case "pomoc":
                System.out.println("pomoc");
                return false;
            case "chod":
                System.out.println("chod");
                return false;
            case "ukonci":
                System.out.println("stop");
            default:
                return false;
        }
    }

}
