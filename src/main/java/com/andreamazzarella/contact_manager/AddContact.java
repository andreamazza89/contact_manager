package com.andreamazzarella.contact_manager;

public class AddContact {

    // DUPLICATION: the constant below exists in EditContact and SaveContact. A validating class will probably emerge,
    // but right now it feels to small so I prefer the duplication
    private static final Age MINIMUM_AGE = new Age(18);

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

        // DUPLICATION: the logic below exists in EditContact and SaveContact. A validating class will probably emerge,
        // but right now it feels to small so I prefer the duplication
        if (contact.getAge().isEqualOrOlderThan(MINIMUM_AGE)) {
            repository.addContact(contact);
            return Result.SUCCESS;
        } else {
            return Result.UNDER_MINIMUM_AGE;
        }
    }
}