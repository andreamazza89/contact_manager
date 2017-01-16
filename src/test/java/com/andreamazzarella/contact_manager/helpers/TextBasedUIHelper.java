package com.andreamazzarella.contact_manager.helpers;

import com.andreamazzarella.contact_manager.TextBasedUI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TextBasedUIHelper {
    public static TextBasedUI createUIWithInput(String input, ByteArrayOutputStream outputStream) {
        InputStream consoleIn = new ByteArrayInputStream(input.getBytes());
        PrintStream printStream = new PrintStream(outputStream);
        return new TextBasedUI(consoleIn, printStream);
    }
}
