package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.ContactsRepository;
import com.andreamazzarella.contact_manager.InMemoryContactsRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ContactsRepository myRepo = new InMemoryContactsRepository();
        initialiseRepository(myRepo);

        ViewRouter viewRouter = new ViewRouter(myRepo);

        TabPane root = viewRouter.generateRoot();
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Contact Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void initialiseRepository(ContactsRepository myRepo) {
        Contact andrea = new Contact("Andrea", "Mazzarella", "Viale Trieste", "GG55", 770012390, 27);
        Contact mario = new Contact("Mario", "Luigi", "Viale Trieste", "GG55", 668899, 111);

        myRepo.addContact(andrea);
        myRepo.addContact(mario);
    }
}
