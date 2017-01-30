package com.andreamazzarella.contact_manager;

import java.util.Optional;

import static com.andreamazzarella.contact_manager.EditContact.Result.SUCCESS;
import static com.andreamazzarella.contact_manager.EditContact.Result.UNDER_MINIMUM_AGE;

public class EditContact {
    // DUPLICATION 001: the constant below exists in EditContact and SaveContact. A validating class will probably emerge,
    // but right now it feels too small so I prefer the duplication
    private static final Age MINIMUM_AGE = new Age(18);

    private final ContactsRepository repository;
    private final Contact contact;

    private String firstName;
    private String lastName;
    private String streetAddress;
    private Optional<String> postCode;
    private TelephoneNumber telephoneNumber;
    private Age age;

    public enum Result {
        UNDER_MINIMUM_AGE, SUCCESS
    }

    public EditContact(ContactsRepository testRepo, Contact contact) {
        this.repository = testRepo;
        this.contact = contact;

        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.streetAddress = contact.getStreetAddress();
        this.postCode = contact.getPostalCode();
        this.telephoneNumber = contact.getTelephoneNumber();
        this.age = contact.getAge();
    }


    public EditContact changeFirstNameTo(String newFirstName) {
        this.firstName = newFirstName;
        return this;
    }

    public EditContact changeLastNameTo(String newLastName) {
        this.lastName = newLastName;
        return this;
    }

    public EditContact changeStreetAddressTo(String newStreetAddress) {
        this.streetAddress = newStreetAddress;
        return this;
    }

    public EditContact changePostalCodeTo(String newPostCode) {
        this.postCode = Optional.of(newPostCode);
        return this;
    }

    public EditContact changeTelephoneNumberTo(TelephoneNumber newTelephoneNumber) {
        this.telephoneNumber = newTelephoneNumber;
        return this;
    }

    public EditContact changeAgeTo(Age newAge) {
        this.age = newAge;
        return this;
    }

    public Result execute() {

        // DUPLICATION 002: the logic below exists in EditContact and SaveContact. A validating class will probably emerge,
        // but right now it feels too small so I prefer the duplication
        if (age.isEqualOrOlderThan(MINIMUM_AGE)) {
            Contact editedContact = new Contact(firstName, lastName, streetAddress, postCode, telephoneNumber, age);
            repository.updateContact(contact, editedContact);
            return SUCCESS;
        } else {
            return UNDER_MINIMUM_AGE;
        }
    }
}
