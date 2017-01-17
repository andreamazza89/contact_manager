package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void knowsIfNameContains() {
        HashMap<String, String> contactInfo = new HashMap<>();
        contactInfo.put("name", "Andrea");
        Contact testContact = new Contact(contactInfo);

        boolean matchesSearchTerm = testContact.nameContains("And");

        assertEquals(true, matchesSearchTerm);
    }
}
