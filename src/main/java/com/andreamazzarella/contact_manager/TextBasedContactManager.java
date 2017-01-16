package com.andreamazzarella.contact_manager;

import java.io.*;
import java.util.ArrayList;

public class TextBasedContactManager {

    private final InMemoryRepository repository;
    private final TextBasedUI textBasedUI;
    private boolean continueRunningProgram = true;

    public TextBasedContactManager(InputStream consoleIn, PrintStream printStream, InMemoryRepository repository) {
        this.textBasedUI = new TextBasedUI(consoleIn, printStream);
        this.repository = repository;
    }


    public void run() throws IOException {
        while (continueRunningProgram) {
            String chosenOption = textBasedUI.chooseOption();
            executeAction(chosenOption);
        }
    }

    private void executeAction(String chosenOption) throws IOException {
        switch (chosenOption) {
            case "3":
                String searchTerm = textBasedUI.searchContact();
                ArrayList<Contact> searchResults = repository.findContact(searchTerm);
                textBasedUI.displayContacts(searchResults);
                break;
            case "5":
                textBasedUI.exit();
                continueRunningProgram = false;
                break;
        }
    }

}
