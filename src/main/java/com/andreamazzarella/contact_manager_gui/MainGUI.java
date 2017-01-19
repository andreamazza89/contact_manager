package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.ContactBuilder;
import com.andreamazzarella.contact_manager.InMemoryRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        InMemoryRepository myRepo = new InMemoryRepository();
        initialiseRepository(myRepo);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContactManager.fxml"));

        loader.setController(new Controller(myRepo));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);

        Controller controller = loader.getController();
        controller.setRepo(myRepo);

        primaryStage.setTitle("Contact Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void initialiseRepository(InMemoryRepository myRepo) {
        Contact andrea = new ContactBuilder().setFirstName("Andrea").build();
        Contact andino = new ContactBuilder().setFirstName("Andino").build();
        Contact giorgio = new ContactBuilder().setFirstName("Giorgio").build();

        myRepo.addContact(andino);
        myRepo.addContact(andrea);
        myRepo.addContact(giorgio);
    }
}
