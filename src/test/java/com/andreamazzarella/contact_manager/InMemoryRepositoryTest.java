package com.andreamazzarella.contact_manager;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;


public class InMemoryRepositoryTest {

    @Test
    public void noContactsExist() {
        ArrayList allContacts = InMemoryRepository.allContacts();
        assertEquals(allContacts, new ArrayList());

    }
}
