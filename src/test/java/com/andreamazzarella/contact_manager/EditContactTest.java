package com.andreamazzarella.contact_manager;

import org.junit.Test;
import static org.junit.Assert.*;

public class EditContactTest {

    @Test
    public void editsFirstNameOnAPreviouslyStoredContact() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("Samuel").build();
        testRepo.addContact(testContact);

        EditContact editContact = new EditContact(testRepo, testContact);
        editContact.changeFirstNameTo("Jackson");
        EditContact.Result transactionResult = editContact.execute();

        Contact updatedContact = testRepo.allContacts().get(0);
        assertEquals("Jackson", updatedContact.getFirstName());
        assertEquals(1, testRepo.allContacts().size());
        assertEquals(EditContact.Result.SUCCESS, transactionResult);
    }

    @Test
    public void doesNotEditContactUnderMinimumAge() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("Samuel").build();
        testRepo.addContact(testContact);

        EditContact editContact = new EditContact(testRepo, testContact);
        editContact.changeFirstNameTo("Jackson").changeAgeTo(new Age(10));
        EditContact.Result transactionResult = editContact.execute();

        Contact updatedContact = testRepo.allContacts().get(0);
        assertEquals("Samuel", updatedContact.getFirstName());
        assertEquals(1, testRepo.allContacts().size());
        assertEquals(EditContact.Result.UNDER_MINIMUM_AGE, transactionResult);
    }

    @Test
    public void editsAllFieldsOnAPreviouslyStoredContact() {
        ContactsRepository testRepo = new InMemoryContactsRepository();
        Contact testContact = new ContactBuilder().setFirstName("Samuel")
                                                  .setLastName("Jackson")
                                                  .setStreetAddress("Leafy Rd")
                                                  .setPostalCode("SE6")
                                                  .setTelephoneNumber("073367")
                                                  .setAge(44)
                                                  .build();
        testRepo.addContact(testContact);

        EditContact editContact = new EditContact(testRepo, testContact);
        editContact.changeFirstNameTo("Danny")
                .changeLastNameTo("Elfman")
                .changeStreetAddressTo("Rugged Place")
                .changePostalCodeTo("DC21")
                .changeTelephoneNumberTo(new TelephoneNumber("0514949"))
                .changeAgeTo(new Age(44));
        EditContact.Result transactionResult = editContact.execute();

        Contact updatedContact = testRepo.allContacts().get(0);
        assertEquals("Danny", updatedContact.getFirstName());
        assertEquals("Elfman", updatedContact.getLastName());
        assertEquals("Rugged Place", updatedContact.getStreetAddress());
        assertEquals("DC21", updatedContact.getPostalCode());
        assertEquals("0514949", updatedContact.getTelephoneNumber().toString());
        assertEquals(44, updatedContact.getAge().toInt());
        assertEquals(1, testRepo.allContacts().size());
        assertEquals(EditContact.Result.SUCCESS, transactionResult);
    }
}
