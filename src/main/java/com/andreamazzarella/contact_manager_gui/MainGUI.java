package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
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
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Contact Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void initialiseRepository(InMemoryRepository myRepo) {
        Contact andrea = new Contact("Andrea", "Mazzarella", "Viale Trieste", "GG55", 770012390, 27);

        myRepo.addContact(andrea);
    }
}
