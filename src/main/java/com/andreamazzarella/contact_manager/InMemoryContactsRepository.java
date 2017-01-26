package com.andreamazzarella.contact_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryContactsRepository implements ContactsRepository {
    private final ArrayList<Contact> contacts;

    public InMemoryContactsRepository() {
        this.contacts = new ArrayList<>();
    }

    @Override
    public List<Contact> allContacts() {
        return this.contacts;
    }

    @Override
    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    @Override
    public List<Contact> findContact(String searchTerm) {
        return contacts.stream()
                       .filter(contact -> contact.nameContains(searchTerm))
                       .collect(Collectors.toList());
    }

    @Override
    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public void updateContact(Contact existingContact, Contact updatedContact) {
        this.deleteContact(existingContact);
        this.addContact(updatedContact);
    }

}
