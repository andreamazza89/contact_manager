package com.andreamazzarella.contact_manager;

import java.io.*;
import java.util.ArrayList;

public class TextBasedContactManager {

    private final BufferedReader consoleIn;
    private final PrintStream printStream;
    private final InMemoryRepository repository;
    private boolean continueRunningProgram = true;

    public TextBasedContactManager(InputStream consoleIn, PrintStream printStream, InMemoryRepository repository) {
        this.consoleIn = new BufferedReader(new InputStreamReader(consoleIn));
        this.printStream = printStream;
        this.repository = repository;
    }


    public void run() throws IOException {
        while (continueRunningProgram) {
            print("Pick an option");
            print("3 - Search for a contact");
            print("5 - Exit");
            String chosenOption = consoleIn.readLine();
            executeAction(chosenOption);
        }
    }

    private void executeAction(String chosenOption) throws IOException {
        switch (chosenOption) {
            case "3":
                print("Enter search term");
                String searchTerm = consoleIn.readLine();
                ArrayList<Contact> searchResults = repository.findContact(searchTerm);
                for (Contact contact : searchResults) {
                    print("name: " + contact.getName() + ", number: " + contact.getNumber());
                }
                break;
            case "5":
                print("Shutting down...");
                continueRunningProgram = false;
                break;
        }
    }

    private void print(String message) {
        printStream.println(message);
    }

}
