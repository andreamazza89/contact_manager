package com.andreamazzarella.contact_manager;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class InMemoryRepositoryTest {
    private InMemoryContactsRepository testRepo = new InMemoryContactsRepository();;
    private Contact andrea = new ContactBuilder().setFirstName("Andrea").build();

    @Before
    public void populateRepository() {
        testRepo.addContact(andrea);
    }

    @Test
    public void noContactsFoundIfNoContactsExist() {
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();

        List allContacts = testRepo.allContacts();

        assertEquals(allContacts, new ArrayList<Contact>());
    }

    @Test
    public void searchForInexistentContact() {
        List searchResults = testRepo.findContact("Giorgio");

        assertEquals(new ArrayList<Contact>(), searchResults);
    }

    @Test
    public void searchForExistingContactByFullName() {
        List searchResults = testRepo.findContact("Andrea");

        assertEquals(Arrays.asList(andrea), searchResults);
    }

    @Test
    public void searchForExistingContactByShortenedName() {
        List searchResults = testRepo.findContact("And");

        assertEquals(Arrays.asList(andrea), searchResults);
    }

    @Test
    public void searchForExistingContactByShortenedNameCaseInsensitive() {
        List searchResults = testRepo.findContact("and");

        assertEquals(Arrays.asList(andrea), searchResults);
    }

    @Test
    public void findsAllContactsAvailable() {
        Contact giorgio = new ContactBuilder().setFirstName("Giorgio").build();
        testRepo.addContact(giorgio);

        List allContacts = testRepo.allContacts();
        assertEquals(Arrays.asList(andrea, giorgio), allContacts);
    }

    @Test
    public void removeContact() {
        testRepo.deleteContact(andrea);

        List allContacts = testRepo.allContacts();
        assertEquals(allContacts, new ArrayList<Contact>());
    }
}
