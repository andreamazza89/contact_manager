package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class InMemoryContactsRepositoryTest {
    @Test
    public void noContactsFoundIfNoContactsExist() {
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();

        List allContacts = testRepo.allContacts();

        assertEquals(allContacts, new ArrayList<Contact>());
    }

    @Test
    public void searchForInexistentContact() {
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        List searchResults = testRepo.findContact("Giorgio");

        assertEquals(new ArrayList<Contact>(), searchResults);
    }

    @Test
    public void searchForExistingContactByFullName() {
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        List searchResults = testRepo.findContact("Andrea");

        assertEquals(Collections.singletonList(andrea), searchResults);
    }

    @Test
    public void searchForExistingContactByShortenedName() {
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        List searchResults = testRepo.findContact("And");

        assertEquals(Collections.singletonList(andrea), searchResults);
    }

    @Test
    public void searchForExistingContactByShortenedNameCaseInsensitive() {
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        List searchResults = testRepo.findContact("and");

        assertEquals(Collections.singletonList(andrea), searchResults);
    }

    @Test
    public void findsAllContactsAvailable() {
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        Contact giorgio = new ContactBuilder().setFirstName("Giorgio").build();
        testRepo.addContact(giorgio);

        List allContacts = testRepo.allContacts();
        assertEquals(Arrays.asList(andrea, giorgio), allContacts);
    }

    @Test
    public void removeContact() {
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        testRepo.deleteContact(andrea);

        List allContacts = testRepo.allContacts();
        assertEquals(allContacts, new ArrayList<Contact>());
    }
}
