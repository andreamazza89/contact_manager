package com.andreamazzarella.contact_manager;

public class AddContact {
    private static final Age MINIMUM_AGE = new Age("18");

    public enum Result {
        SUCCESS, UNDER_MINIMUM_AGE;
    }

    private final ContactsRepository repository;
    private final Contact contact;

    public AddContact(ContactsRepository repository, Contact contact) {
        this.repository = repository;
        this.contact = contact;
    }

    public Result execute() {
        if (contact.getAge().isEqualOrOlderThan(MINIMUM_AGE)) {
            repository.addContact(contact);
            return Result.SUCCESS;
        } else {
            return Result.UNDER_MINIMUM_AGE;
        }
    }
}