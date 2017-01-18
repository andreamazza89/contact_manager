package com.andreamazzarella.contact_manager;

import java.util.ArrayList;

public class InMemoryRepository {
    private ArrayList<Contact> contacts;

    public InMemoryRepository() {
        this.contacts = new ArrayList<>();
    }

    public ArrayList allContacts() {
        return this.contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public ArrayList findContact(final String searchTerm) {
        ArrayList<Contact> searchResults = new ArrayList<>();
//        contacts.stream().filter(contact -> contact.nameContains(searchTerm)).collect(Collectors.toList());
        for (Contact contact : this.contacts) {
            if (contact.nameContains(searchTerm)) searchResults.add(contact);
        }
        return searchResults;
    }
}

