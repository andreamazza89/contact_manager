package com.andreamazzarella.contact_manager;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void knowsIfNameContains() {
        Contact testContact = new ContactBuilder().setFirstName("Andrea").build();

        boolean matchesSearchTerm = testContact.nameContains("And");

        assertEquals(true, matchesSearchTerm);
    }
}
