package com.andreamazzarella.contact_manager;

import java.io.IOException;
import java.util.HashMap;

public class ConsoleRunner {
    public static void main(String[] args) throws IOException {
        InMemoryRepository myRepo = new InMemoryRepository();

        HashMap<String, String> andreasInfo = new HashMap<>();
        andreasInfo.put("name", "Andrea");
        andreasInfo.put("number", "774444");
        Contact andrea = new Contact(andreasInfo);

        HashMap<String, String> andinoInfo = new HashMap<>();
        andinoInfo.put("name", "Andino");
        andinoInfo.put("number", "12366616");
        Contact andino = new Contact(andinoInfo);

        HashMap<String, String> giorgiosInfo = new HashMap<>();
        giorgiosInfo.put("name", "Giorgio");
        andreasInfo.put("number", "9872347");
        Contact giorgio = new Contact(giorgiosInfo);

        myRepo.addContact(andino);
        myRepo.addContact(andrea);
        myRepo.addContact(giorgio);

        ContactManager.run(System.in, System.out, myRepo);
    }
}
