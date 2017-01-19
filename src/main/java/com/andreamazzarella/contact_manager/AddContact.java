package com.andreamazzarella.contact_manager;


public class AddContact {

    private final InMemoryRepository repository;
    private final Contact contact;
    private String message = "";

    public AddContact(InMemoryRepository repository, Contact contact) {
        this.repository = repository;
        this.contact = contact;
    }

    public void execute() {
        if (contact.getAge() >= 18) {
            repository.addContact(contact);
            message = "Contact saved";
        } else {
            message = "Contact was not saved: under age minimum";
        }

    }

    public String message() {
        return message;
    }
}
