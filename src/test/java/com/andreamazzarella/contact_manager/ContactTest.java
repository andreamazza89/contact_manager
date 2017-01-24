package com.andreamazzarella.contact_manager;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void knowsIfNameContains() {

        //? This responsibility should be pushed away from the Contact if and when the need for more searching features arises

        Contact testContact = new ContactBuilder().setFirstName("Andrea").build();

        boolean matchesSearchTerm = testContact.nameContains("And");

        assertEquals(true, matchesSearchTerm);
    }

}
