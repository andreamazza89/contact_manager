package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RemoveContactTest {

    @Test
    public void successfullyRemovesContact() {
        //make this an interface so you can mock it!
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").build();
        AddContact addContact = new AddContact(testRepo, testContact);
        RemoveContact removeContact = new RemoveContact(testRepo, testContact);

        addContact.execute();
        removeContact.execute();

        List contacts = testRepo.allContacts();
        assertEquals(new ArrayList(), contacts);
    }

    @Test
    public void hasSuccessResultAfterRemovingContact() {
        //make this an interface so you can mock it!
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").build();
        AddContact addContact = new AddContact(testRepo, testContact);
        RemoveContact removeContact = new RemoveContact(testRepo, testContact);

        addContact.execute();
        RemoveContact.Result result = removeContact.execute();

        assertEquals(RemoveContact.Result.SUCCESS, result);
    }

    @Test
    public void hasInexistentContactResultIfContactIsInexistent() {
        //make this an interface so you can mock it!
        InMemoryContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("test").build();
        RemoveContact removeContact = new RemoveContact(testRepo, testContact);

        RemoveContact.Result result = removeContact.execute();

        assertEquals(RemoveContact.Result.CONTACT_DOES_NOT_EXIST, result);
    }
}
