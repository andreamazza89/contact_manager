package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class addContactTest {

    @Test
    public void successfullyStoresAContact() {
        //make this an interface so you can mock it!
        InMemoryRepository testRepo = new InMemoryRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").build();
        AddContact addContact = new AddContact(testRepo, testContact);

        addContact.execute();

        List<Contact> contactsAdded = testRepo.allContacts();
        assertEquals(Arrays.asList(testContact), contactsAdded);
    }

    @Test
    public void hasNoMessageBeforeExecutingCommand() {
        //make this an interface so you can mock it!
        InMemoryRepository testRepo = new InMemoryRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").build();
        AddContact addContact = new AddContact(testRepo, testContact);

        assertEquals("", addContact.message());
    }

    @Test
    public void hasSuccessMessageAfterStoringContact() {
        //make this an interface so you can mock it!
        InMemoryRepository testRepo = new InMemoryRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").build();
        AddContact addContact = new AddContact(testRepo, testContact);

        addContact.execute();

        assertEquals("Contact saved", addContact.message());
    }

    @Test
    public void willNotSaveIfContactIsUnderAgeMinimum() {
        //make this an interface so you can mock it!
        InMemoryRepository testRepo = new InMemoryRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").setAge(16).build();
        AddContact addContact = new AddContact(testRepo, testContact);

        addContact.execute();

        List<Contact> contactsAdded = testRepo.allContacts();
        assertEquals(new ArrayList<>(), contactsAdded);
    }

    @Test
    public void hasFailureMessageIfContactIsUnderAgeMinimum() {
        //make this an interface so you can mock it!
        InMemoryRepository testRepo = new InMemoryRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").setAge(16).build();
        AddContact addContact = new AddContact(testRepo, testContact);

        addContact.execute();

        assertEquals("Contact was not saved: under age minimum", addContact.message());
    }

}
