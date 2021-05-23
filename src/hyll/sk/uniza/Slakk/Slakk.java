package hyll.sk.uniza.Slakk;

import hyll.sk.uniza.controllers.*;
import hyll.sk.uniza.helpers.DemotedMessageException;
import hyll.sk.uniza.helpers.ElasticSearch;
import hyll.sk.uniza.helpers.MessageType;
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

        IMessage welcomeMessage = new WelcomeMessage("Welcome to Slaak");
        ((WelcomeMessage) welcomeMessage).printString();


        /*
        try {
            welcomeMessage.constructMessage(State.SENDER, "", "");
        } catch (IOException | DemotedMessageException e) {
            e.printStackTrace();
        }
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
            case "sendMessage":
                this.sendMessage(command);
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
            case "search":
                this.search(command);
                return false;
            case "searchDate":
                this.searchDate(command);
                return false;
            case "exit":
                return true;
            default:
                return false;
        }
    }

    private void search(Command command) {
        if (command.maParameter() && command.maParameter2() && command.maParameter3()) {
            MessageType type = MessageType.VIDEO;
            switch (command.getParameter3().charAt(0)) {
                case 't', 'T':
                    System.out.println("Cannot open text use another search query...");
                    return;
                case 'v', 'V':
                    type = MessageType.AUDIO;
                    break;
                case 'p', 'P':
                    type = MessageType.PICTURE;
            }
            User user1 = this.users.get(command.getParameter());
            try{
            ElasticSearch.openMessage(user1, command.getParameter2(), type);
            } catch(NullPointerException e){
                System.out.println("Something went wrong....");
            }
        } else if(command.maParameter() && command.maParameter2()){
            User user1 = this.users.get(command.getParameter());
            try{
                ElasticSearch.findAllMessagesByContent(user1, command.getParameter2());
            } catch(NullPointerException e){
                System.out.println("Something went wrong....");
            }

        } else if(command.maParameter()){
            User user1 = this.users.get(command.getParameter());
            try{
                ElasticSearch.findAllMessages(user1);
            } catch(NullPointerException e){
                System.out.println("Something went wrong....");
            }
        } else{
            System.out.println("Not very well written, returning");
        }

    }

    private void searchDate(Command command){
        User user1 = this.users.get(command.getParameter());
        if(!(command.getParameter2().equals("today") || command.getParameter2().equals("yesterday"))){
            System.out.println("Bad time see help");
            return;
        }
        ElasticSearch.searchByDate(user1, command.getParameter2());
    }

    private void createUser(Command command) {
        String name = command.getParameter();
        char type = name.charAt(0);
        name = name.substring(1);
        switch (type) {
            case 'b', 'B' -> this.users.put(name, new BasicUser(name));
            case 'p', 'P' -> this.users.put(name, new PremiumUser(name));
            case 'L', 'l' -> this.users.put(name, new LimitedUser(name));
            default -> System.out.println("You have selected an incorrect type of user... type help for a help");
        }
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

    private void sendMessage(Command command) {
        if (!this.users.containsKey(command.getParameter())) {
            System.out.println("User one does not exits..");
            return;
        }
        if (!this.users.containsKey(command.getParameter2())) {
            return;
        }

        User user1 = this.users.get(command.getParameter());
        User user2 = this.users.get(command.getParameter2());

        String content = command.getParameter3();
        char argument = content.charAt(0);
        content = content.substring(1);


        switch (argument) {
            case 't', 'T':
                this.sendText(command);
            case 'v', 'V':
                try {
                    user1.createMessage(new VoiceMessage(5, date.getTime()));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                try {

                    user1.sendMessages(user2);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                break;
            case 'p', 'P':

                user1.createMessage(new Pic(content, date.getTime()));

                try {
                    user1.sendMessage(user2);

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return;
                }
                break;
            default:
                System.out.println("You have selected an incorrect type of message... type help for a help");
        }
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
           /* System.out.println(command.getParameter());
            System.out.println(command.getParameter2());*/
            System.out.println("User one does not exits..");
            return;
        }
        String content = command.getParameter2();
        User user1 = this.users.get(command.getParameter());
        user1.createMessage(new TextMessage(content, date.getTime()));
        String output = (user1.getUsersMessageBuffer());
       // System.out.println(output);
        this.debug(command);

    }

    private void debug(Command command) {
        User user1 = this.users.get(command.getParameter());
        String output = (user1.getUsersMessageBuffer());
        System.out.println(output);
    }

}
