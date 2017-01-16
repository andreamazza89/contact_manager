package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static com.andreamazzarella.contact_manager.helpers.InMemoryRepositoryHelper.createContactInRepository;

public class SearchContactIntegrationTest {

    @Test
    public void displaysFoundContact() throws IOException {
        InMemoryRepository testRepo = new InMemoryRepository();

        createContactInRepository(testRepo, new String[]{"name", "Andrea", "number", "42"});

        InputStream consoleIn = new ByteArrayInputStream("3\nAnd\n5\n".getBytes());
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(consoleOut);

        TextBasedContactManager contactManager = new TextBasedContactManager(consoleIn, printStream, testRepo);
        contactManager.run();

        assertEquals("Pick an option\n" +
                "3 - Search for a contact\n" +
                "5 - Exit\n" +
                "Enter search term\n" +
                "name: Andrea, number: 42\n" +
                "Pick an option\n" +
                "3 - Search for a contact\n" +
                "5 - Exit\n" +
                "Shutting down...\n", consoleOut.toString());

    }
}
