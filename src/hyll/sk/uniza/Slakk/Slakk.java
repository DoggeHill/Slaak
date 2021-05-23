package hyll.sk.uniza.Slakk;

import hyll.sk.uniza.controllers.*;
import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.State;
import hyll.sk.uniza.messages.*;
import hyll.sk.uniza.users.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Slakk {
    private final Parser parser;
    private Date date;
    HashMap<String, User> users;

    public Slakk(Parser parser) {
        this.parser = parser;
        this.users = new HashMap<>();
        this.date = new Date();
    }

    /**
     * Hlavna metoda hry.
     * Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void runSlakk() {

        //Demoted messages-> lower in the hierarchy
        //Elevated messages-> classic ones

        //Create demoted message

        /*
        IMessage welcomeMessage = new WelcomeMessage("Welcome to Slaak");
        ((WelcomeMessage) welcomeMessage).printString();

        try {
            welcomeMessage.constructMessage(State.SENDER, "", "");
        } catch (IOException | DemotedMessageException e) {
            e.printStackTrace();
        }

        */


        /*
        IMessage napovedaMessage = new NapovedaMessage();
        try {
            napovedaMessage.constructMessage(State.SENDER, "", "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DemotedMessageException e) {
            e.printStackTrace();
        }
        */

        /*

        User Jano = new BasicUser("Jano");
        User Fero = new BasicUser("Fero");


        Fero.createMessage(new TextMessage("content", date.getTime()));
        Fero.sendMessages(Jano);



        IMessage voiceMessage = null;
        try {
            voiceMessage = new VoiceMessage(5000, 6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Fero.createMessage(voiceMessage);

        */


        boolean jeKoniec;

        do {
            Command command = this.parser.nacitajPrikaz();
            jeKoniec = this.vykonajPrikaz(command);
        } while (!jeKoniec);

        System.out.println("Give us 5 stars rating on UnizaPlayStore");


    }


    /**
     * Prevezne prikaz a vykona ho.
     *
     * @param command prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    private boolean vykonajPrikaz(Command command) {
        if (command.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        System.out.println(command.getNazov());
        System.out.println(command.getParameter());
        System.out.println(command.getParameter2());
        System.out.println(command.getParameter3());

        String nazovPrikazu = command.getNazov();

        switch (nazovPrikazu) {
            case "help":
                this.helpMessage();
                return false;
            case "create":
                this.createUser(command);
                return false;
            case "showUsers":
                this.showUsers();
                return false;
            case "sendText":
                this.sendText(command);
                return false;
            case "sendVoice":
                this.sendVoice(command);
                return false;
            case "sendAllTexts":
                this.sendTextAll(command);
                return false;
            case "createText":
                this.createText(command);
                return false;
            case "debug":
                this.debug(command);
                return false;
            default:
                return false;
        }
    }

    private void createUser(Command command) {
        String name = command.getParameter();
        this.users.put(name, new BasicUser(name));
    }

    private void showUsers() {
        for (String value : users.keySet()) {
            System.out.println(value);
        }
    }

    private void helpMessage() {
        IMessage helpMessage = new NapovedaMessage();
        ((NapovedaMessage) helpMessage).printString();
    }

    private void sendText(Command command) {
        if (!this.users.containsKey(command.getParameter())) {
            System.out.println(command.getParameter());
            System.out.println(command.getParameter2());
            System.out.println("User one does not exits..");
            return;
        }
        if (!this.users.containsKey(command.getParameter2())) {
            System.out.println("User two does not exits..");
            return;
        }

        User user1 = this.users.get(command.getParameter());
        User user2 = this.users.get(command.getParameter2());
        String content = command.getParameter3();

        user1.createMessage(new TextMessage(content, date.getTime()));
        user1.sendMessages(user2);

    }
    private void sendVoice(Command command) {
        if (!this.users.containsKey(command.getParameter())) {
            System.out.println(command.getParameter());
            System.out.println(command.getParameter2());
            System.out.println("User one does not exits..");
            return;
        }
        if (!this.users.containsKey(command.getParameter2())) {
            System.out.println("User two does not exits..");
            return;
        }

        User user1 = this.users.get(command.getParameter());
        User user2 = this.users.get(command.getParameter2());
        String content = command.getParameter3();

        try {
            user1.createMessage(new VoiceMessage(4, date.getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        user1.sendMessages(user2);

    }


    private void sendTextAll(Command command) {
        if (!this.users.containsKey(command.getParameter())) {
            System.out.println(command.getParameter());
            System.out.println(command.getParameter2());
            System.out.println("User one does not exits..");
            return;
        }
        if (!this.users.containsKey(command.getParameter2())) {
            System.out.println("User two does not exits..");
            return;
        }

        User user1 = this.users.get(command.getParameter());
        User user2 = this.users.get(command.getParameter2());
        String content = command.getParameter3();

        user1.sendMessages(user2);

    }

    private void createText(Command command) {
        if (!this.users.containsKey(command.getParameter())) {
            System.out.println(command.getParameter());
            System.out.println(command.getParameter2());
            System.out.println("User one does not exits..");
            return;
        }
        String content = command.getParameter2();
        User user1 = this.users.get(command.getParameter());
        user1.createMessage(new TextMessage(content, date.getTime()));
        String output = (user1.getUsersMessageBuffer());
        System.out.println(output);
        this.debug(command);

    }

    private void debug(Command command) {
        User user1 = this.users.get(command.getParameter());
        String output = (user1.getUsersMessageBuffer());
        System.out.println(output);
    }

}
