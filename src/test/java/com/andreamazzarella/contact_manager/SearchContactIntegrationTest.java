package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SearchContactIntegrationTest {

    @Test
    public void displaysFoundContact() throws IOException {
        InMemoryRepository testRepo = new InMemoryRepository();

        HashMap<String, String> andreasInfo = new HashMap<String, String>();
        andreasInfo.put("name", "Andrea");
        Contact andrea = new Contact(andreasInfo);

        testRepo.addContact(andrea);

        InputStream consoleIn = new ByteArrayInputStream("3\nAnd\n".getBytes());
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(consoleOut);

        ContactManager.run(consoleIn, printStream, testRepo);

        assertEquals("Pick an option\nEnter search term\nAndrea\n", consoleOut.toString());

    }
}
