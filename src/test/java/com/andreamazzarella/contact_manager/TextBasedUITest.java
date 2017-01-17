package com.andreamazzarella.contact_manager;

import com.andreamazzarella.contact_manager.helpers.TextBasedUIHelper;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TextBasedUITest {

    @Test
    public void collectsInputForChooseAction() throws IOException {
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        TextBasedUI testUI = TextBasedUIHelper.createUIWithInput("3\n", consoleOut);

        String chosenAction = testUI.chooseAction();

        assertEquals("Pick an option\n" +
                "3 - Search for a contact\n" +
                "5 - Exit\n", consoleOut.toString());
        assertEquals("3", chosenAction);
    }

    @Test
    public void collectsInputForSearchContact() throws IOException {
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        TextBasedUI testUI = TextBasedUIHelper.createUIWithInput("search\n", consoleOut);

        String chosenAction = testUI.searchContact();

        assertEquals("Enter search term\n", consoleOut.toString());
        assertEquals("search", chosenAction);
    }

    @Test
    public void displaysEmptyListOfContacts() {
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        TextBasedUI testUI = TextBasedUIHelper.createUIWithInput("irrelevant but required\n", consoleOut);

        testUI.displayContacts(new ArrayList<>());

        assertEquals("", consoleOut.toString());
    }

    @Test
    public void displaysListOfContacts() {
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        TextBasedUI testUI = TextBasedUIHelper.createUIWithInput("irrelevant but required\n", consoleOut);

        HashMap<String, String> andreasInfo = new HashMap<>();
        andreasInfo.put("name", "Andrea");
        andreasInfo.put("number", "774444");
        Contact andrea = new Contact(andreasInfo);

        HashMap<String, String> giorgiosInfo = new HashMap<>();
        giorgiosInfo.put("name", "Giorgio");
        giorgiosInfo.put("number", "9872347");
        Contact giorgio = new Contact(giorgiosInfo);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(andrea);
        contacts.add(giorgio);
        testUI.displayContacts(contacts);

        assertEquals("name: Andrea, number: 774444\n" +
                     "name: Giorgio, number: 9872347\n", consoleOut.toString());
    }

    @Test
    public void printsExitMessage() throws IOException {
        ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
        TextBasedUI testUI = TextBasedUIHelper.createUIWithInput("irrelevant but required\n", consoleOut);

        testUI.exit();

        assertEquals("Shutting down...\n", consoleOut.toString());
    }

}
