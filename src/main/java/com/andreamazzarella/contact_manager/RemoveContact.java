package com.andreamazzarella.contact_manager;

public class RemoveContact {

    public enum Result {
        SUCCESS, CONTACT_DOES_NOT_EXIST
    }

    private final ContactsRepository repository;
    private final Contact contact;

    public RemoveContact(ContactsRepository repository, Contact testContact) {
        this.repository = repository;
        this.contact = testContact;
    }

    public Result execute() {
        if (repository.allContacts().contains(contact)) {
            repository.deleteContact(contact);
            return Result.SUCCESS;
        } else {
            return Result.CONTACT_DOES_NOT_EXIST;
        }
    }

}
