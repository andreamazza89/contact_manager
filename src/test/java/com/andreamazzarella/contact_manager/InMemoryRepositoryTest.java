package com.andreamazzarella.contact_manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;
import static org.junit.Assert.*;


public class InMemoryRepositoryTest {

    @Test
    public void noContactsExist() {
        InMemoryRepository testRepo = new InMemoryRepository();
        ArrayList allContacts = testRepo.allContacts();
        assertEquals(allContacts, new ArrayList<Contact>());
    }

    @Test
    public void findsAllContactsAvailable() {
        InMemoryRepository testRepo = new InMemoryRepository();

        HashMap<String, String> andreasInfo = new HashMap<String, String>();
        andreasInfo.put("name", "Andrea");
        Contact andrea = new Contact(andreasInfo);

        HashMap<String, String> giorgiosInfo = new HashMap<String, String>();
        giorgiosInfo.put("name", "Giorgio");
        Contact giorgio = new Contact(giorgiosInfo);

        testRepo.addContact(andrea);
        testRepo.addContact(giorgio);

        ArrayList allContacts = testRepo.allContacts();
        assertEquals(Arrays.asList(andrea, giorgio), allContacts);
    }

    @Test
    public void searchForInexistentContact() {
        InMemoryRepository testRepo = new InMemoryRepository();

        HashMap<String, String> andreasInfo = new HashMap<String, String>();
        andreasInfo.put("name", "Andrea");
        Contact andrea = new Contact(andreasInfo);

        testRepo.addContact(andrea);

        ArrayList searchResults = testRepo.findContact("Giorgio");
        assertEquals(new ArrayList<Contact>(), searchResults);
    }

    @Test
    public void searchForExistingContactOne() {
        InMemoryRepository testRepo = new InMemoryRepository();

        HashMap<String, String> andreasInfo = new HashMap<String, String>();
        andreasInfo.put("name", "Andrea");
        Contact andrea = new Contact(andreasInfo);

        testRepo.addContact(andrea);

        ArrayList searchResults = testRepo.findContact("Andrea");
        assertEquals(Arrays.asList(andrea), searchResults);
    }

    @Test
    public void searchForExistingContactTwo() {
        InMemoryRepository testRepo = new InMemoryRepository();

        HashMap<String, String> andreasInfo = new HashMap<String, String>();
        andreasInfo.put("name", "Andrea");
        Contact andrea = new Contact(andreasInfo);

        testRepo.addContact(andrea);

        ArrayList searchResults = testRepo.findContact("And");
        assertEquals(Arrays.asList(andrea), searchResults);
    }

}
