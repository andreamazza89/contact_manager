package com.andreamazzarella.contact_manager;

public class AddContact {
    private static Age MINIMUM_AGE;

    static {
        try {
            MINIMUM_AGE = new Age("18");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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