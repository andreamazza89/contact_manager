package com.andreamazzarella.contact_manager;

import static com.andreamazzarella.contact_manager.EditContact.Result.SUCCESS;
import static com.andreamazzarella.contact_manager.EditContact.Result.UNDER_MINIMUM_AGE;

public class EditContact {
    // DUPLICATION: the constant below exists in EditContact and SaveContact. A validating class will probably emerge,
    // but right now it feels to small so I prefer the duplication
    private static final Age MINIMUM_AGE = new Age(18);

    private final ContactsRepository testRepo;
    private final Contact contact;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String postCode;
    private TelephoneNumber telephoneNumber;
    private Age age;

    public enum Result {
        UNDER_MINIMUM_AGE, SUCCESS
    }

    EditContact(ContactsRepository testRepo, Contact testContact) {
        this.testRepo = testRepo;

        this.contact = testContact;
        this.firstName = testContact.getFirstName();
        this.lastName = testContact.getLastName();
        this.streetAddress = testContact.getStreetAddress();
        this.postCode = testContact.getPostalCode();
        this.telephoneNumber = testContact.getTelephoneNumber();
        this.age = testContact.getAge();
    }

    EditContact changeFirstNameTo(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EditContact changeAgeTo(Age newAge) {
        this.age = newAge;
        return this;
    }

    Result execute() {

        // DUPLICATION: the logic below exists in EditContact and SaveContact. A validating class will probably emerge,
        // but right now it feels to small so I prefer the duplication
        if (age.isEqualOrOlderThan(MINIMUM_AGE)) {
            Contact editedContact = new Contact(firstName, lastName, streetAddress, postCode, telephoneNumber, age);
            testRepo.updateContact(contact, editedContact);
            return SUCCESS;
        } else {
            return UNDER_MINIMUM_AGE;
        }
    }
}
