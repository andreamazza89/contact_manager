package com.andreamazzarella.contact_manager;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static com.andreamazzarella.contact_manager.helpers.InMemoryRepositoryHelper.createContactInRepository;

public class SearchContactIntegrationTest {

    @Test
    public void displaysFoundContact() throws IOException {
        InMemoryRepository testRepo = new InMemoryRepository();

        Contact andrea = createContactInRepository(testRepo, new String[]{"name", "Andrea"});

        InputStream consoleIn = new ByteArrayInputStream("3\nAnd\n".getBytes());
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(consoleOut);

        ContactManager.run(consoleIn, printStream, testRepo);

        assertEquals("Pick an option\nEnter search term\nAndrea\n", consoleOut.toString());

    }
}
