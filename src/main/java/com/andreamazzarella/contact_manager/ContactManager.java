package com.andreamazzarella.contact_manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class ContactManager {

    public static void run(InputStream consoleIn, PrintStream printStream, InMemoryRepository repository) throws IOException {
        StringBasedUI ui = new StringBasedUI(consoleIn, printStream, repository);
        ui.run();
    }

}
