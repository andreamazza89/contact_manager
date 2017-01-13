package com.andreamazzarella.contact_manager.helpers;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.InMemoryRepository;

import java.util.HashMap;

public class InMemoryRepositoryHelper {
    public static Contact createContactInRepository(InMemoryRepository repo, String[] contactInformation) {
        HashMap<String, String> contactMap = new HashMap<>();
        for (int key = 0; key < contactInformation.length; key += 2) {
            contactMap.put(contactInformation[key], contactInformation[key + 1]);
        }
        System.out.println("contactMap = " + contactMap);
        Contact contact = new Contact(contactMap);
        repo.addContact(contact);
        return contact;
    }

}
