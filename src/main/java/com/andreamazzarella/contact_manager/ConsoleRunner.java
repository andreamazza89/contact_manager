package com.andreamazzarella.contact_manager;

import java.io.IOException;
import java.util.HashMap;

public class ConsoleRunner {
    public static void main(String[] args) throws IOException {
        InMemoryRepository myRepo = new InMemoryRepository();

        HashMap<String, String> andreasInfo = new HashMap<>();
        andreasInfo.put("name", "Andrea");
        Contact andrea = new Contact(andreasInfo);

        HashMap<String, String> giorgiosInfo = new HashMap<>();
        giorgiosInfo.put("name", "Giorgio");
        Contact giorgio = new Contact(giorgiosInfo);

        myRepo.addContact(andrea);
        myRepo.addContact(giorgio);

        ContactManager.run(System.in, System.out, myRepo);
    }
}
