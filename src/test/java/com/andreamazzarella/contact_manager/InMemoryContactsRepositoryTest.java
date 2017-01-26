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
        ContactsRepository testRepo = new InMemoryContactsRepository();

        List allContacts = testRepo.allContacts();

        assertEquals(allContacts, new ArrayList<Contact>());
    }

    @Test
    public void searchForInexistentContact() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        List searchResults = testRepo.findContact("Giorgio");

        assertEquals(new ArrayList<Contact>(), searchResults);
    }

    @Test
    public void searchForExistingContactByFullName() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        List searchResults = testRepo.findContact("Andrea");

        //? This is flakey as it uses object identity, so it implicitly knows that the implementation I am testing
        //? is the inMemory one. It would be better if the test did not know this detail, so I  could reuse it for other
        //? implementations
        assertEquals(Collections.singletonList(andrea), searchResults);
    }

    @Test
    public void searchForExistingContactByShortenedName() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        List searchResults = testRepo.findContact("And");

        assertEquals(Collections.singletonList(andrea), searchResults);
    }

    @Test
    public void searchForExistingContactByShortenedNameCaseInsensitive() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        List searchResults = testRepo.findContact("and");

        assertEquals(Collections.singletonList(andrea), searchResults);
    }

    @Test
    public void findsAllContactsAvailable() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        Contact giorgio = new ContactBuilder().setFirstName("Giorgio").build();
        testRepo.addContact(giorgio);

        List allContacts = testRepo.allContacts();
        assertEquals(Arrays.asList(andrea, giorgio), allContacts);
    }

    @Test
    public void removeContact() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        testRepo.deleteContact(andrea);

        List allContacts = testRepo.allContacts();
        assertEquals(allContacts, new ArrayList<Contact>());
    }

    @Test
    public void editContact() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        testRepo.addContact(andrea);

        Contact giacomo = new ContactBuilder().setFirstName("Giacomo").build();
        testRepo.updateContact(andrea, giacomo);

        Contact updatedContact = testRepo.allContacts().get(0);

        assertEquals("Giacomo", updatedContact.getFirstName());
        assertEquals(1, testRepo.allContacts().size());
    }
}
