package com.andreamazzarella.contact_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryRepository {
    private final ArrayList<Contact> contacts;

    public InMemoryRepository() {
        this.contacts = new ArrayList<>();
    }

    public List<Contact> allContacts() {
        return this.contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public List<Contact> findContact(final String searchTerm) {
        return contacts.stream()
                       .filter(contact -> contact.nameContains(searchTerm))
                       .collect(Collectors.toList());
    }

    public void delete(Contact contact) {
        contacts.remove(contact);
    }
}

