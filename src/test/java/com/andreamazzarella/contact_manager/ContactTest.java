package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void knowsIfNameContains() {
        Map<String, String> contactInfo = new HashMap<>();
        contactInfo.put("name", "Andrea");
        Contact testContact = new Contact(contactInfo);

        boolean matchesSearchTerm = testContact.nameContains("And");

        assertEquals(true, matchesSearchTerm);
    }
}
