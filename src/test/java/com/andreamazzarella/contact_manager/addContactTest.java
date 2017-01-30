package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AddContactTest {

    @Test
    public void successfullyStoresAContact() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").build();
        AddContact addContact = new AddContact(testRepo, testContact);

        addContact.execute();

        List<Contact> contactsAdded = testRepo.allContacts();
        assertEquals(Collections.singletonList(testContact), contactsAdded);
    }

    @Test
    public void hasSuccessResultAfterStoringContact() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").build();
        AddContact addContact = new AddContact(testRepo, testContact);

        AddContact.Result result = addContact.execute();

        assertEquals(AddContact.Result.SUCCESS, result);
    }

    @Test
    public void willNotSaveIfContactIsUnderAgeMinimum() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").setAge(16).build();
        AddContact addContact = new AddContact(testRepo, testContact);

        addContact.execute();

        List<Contact> contactsAdded = testRepo.allContacts();
        assertEquals(new ArrayList<>(), contactsAdded);
    }

    @Test
    public void hasFailureResultIfContactIsUnderAgeMinimum() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").setAge(16).build();
        AddContact addContact = new AddContact(testRepo, testContact);

        AddContact.Result result = addContact.execute();

        assertEquals(AddContact.Result.UNDER_MINIMUM_AGE, result);
    }

}
