package com.andreamazzarella.contact_manager;

import java.io.*;
import java.util.ArrayList;

public class TextBasedUI {

    private final BufferedReader consoleIn;
    private final PrintStream printStream;

    public TextBasedUI(InputStream consoleIn, PrintStream printStream) {
        this.consoleIn = new BufferedReader(new InputStreamReader(consoleIn));
        this.printStream = printStream;
    }

    public String chooseOption() throws IOException {
        print("Pick an option");
        print("3 - Search for a contact");
        print("5 - Exit");
        return consoleIn.readLine();
    }

    public String searchContact() throws IOException {
        print("Enter search term");
        return consoleIn.readLine();
    }

    public void displayContacts(ArrayList<Contact> searchResults) {
        for (Contact contact : searchResults) {
            print("name: " + contact.getName() + ", number: " + contact.getNumber());
        }
    }

    public void exit() {
        print("Shutting down...");
    }

    private void print(String message) {
        printStream.println(message);
    }

}
