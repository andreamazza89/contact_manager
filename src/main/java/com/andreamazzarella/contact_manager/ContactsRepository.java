package com.andreamazzarella.contact_manager;

import java.util.List;

public interface ContactsRepository {
    List<Contact> allContacts();
    List<Contact> findContact(String searchTerm);
    void addContact(Contact contact);
    void deleteContact(Contact contact);
}
