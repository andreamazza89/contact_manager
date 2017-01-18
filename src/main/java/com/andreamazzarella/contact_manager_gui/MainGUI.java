package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.InMemoryRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MainGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        InMemoryRepository myRepo = new InMemoryRepository();
        initialiseRepository(myRepo);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ContactManager.fxml"));
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
        Map<String, String> andreasInfo = new HashMap<>();
        andreasInfo.put("name", "Andrea");
        andreasInfo.put("number", "774444");
        Contact andrea = new Contact(andreasInfo);

        Map<String, String> andinoInfo = new HashMap<>();
        andinoInfo.put("name", "Andino");
        andinoInfo.put("number", "12366616");
        Contact andino = new Contact(andinoInfo);

        Map<String, String> giorgiosInfo = new HashMap<>();
        giorgiosInfo.put("name", "Giorgio");
        giorgiosInfo.put("number", "9872347");
        Contact giorgio = new Contact(giorgiosInfo);

        myRepo.addContact(andino);
        myRepo.addContact(andrea);
        myRepo.addContact(giorgio);
    }
}
