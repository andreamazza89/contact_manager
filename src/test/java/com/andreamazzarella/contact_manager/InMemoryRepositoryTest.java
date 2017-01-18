package com.andreamazzarella.contact_manager;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.andreamazzarella.contact_manager.helpers.InMemoryRepositoryHelper.createContactInRepository;
import static org.junit.Assert.assertEquals;


public class InMemoryRepositoryTest {
    private InMemoryRepository testRepo = new InMemoryRepository();;
    private Contact andrea = createContactInRepository(testRepo, new String[] {"name", "Andrea"});

    @Test
    public void noContactsExist() {
        InMemoryRepository testRepo = new InMemoryRepository();

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
        Contact giorgio = createContactInRepository(testRepo, new String[]{"name", "Giorgio"});

        List allContacts = testRepo.allContacts();
        assertEquals(Arrays.asList(andrea, giorgio), allContacts);
    }
}
