package com.andreamazzarella.contact_manager;

import java.io.*;
import java.util.ArrayList;

public class StringBasedUI {

    private final BufferedReader consoleIn;
    private final PrintStream printStream;
    private final InMemoryRepository repository;

    public StringBasedUI(InputStream consoleIn, PrintStream printStream, InMemoryRepository repository) {
        this.consoleIn = new BufferedReader(new InputStreamReader(consoleIn));
        this.printStream = printStream;
        this.repository = repository;
    }

    public void run() throws IOException {
        this.printStream.println("Pick an option");
        String chosenOption = this.consoleIn.readLine();
        switch (chosenOption) {
            case "3":
                this.printStream.println("Enter search term");
                String searchTerm = this.consoleIn.readLine();
                ArrayList<Contact> searchResults = this.repository.findContact(searchTerm);
                for (Contact contact : searchResults) {
                    this.printStream.println(contact.getName());
                }
        }
    }
}
