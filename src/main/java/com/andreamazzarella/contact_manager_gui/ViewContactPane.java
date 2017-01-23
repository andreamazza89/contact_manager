package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.ContactsRepository;
import com.andreamazzarella.contact_manager.RemoveContact;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class ViewContactPane extends GridPane {
    @FXML private TextField viewContactFirstName;
    @FXML private TextField viewContactLastName;
    @FXML private TextField viewContactStreetAddress;
    @FXML private TextField viewContactPostalCode;
    @FXML private TextField viewContactTelephoneNumber;
    @FXML private TextField viewContactAge;

    @FXML private Text contactAlterationAlerts;

    private ViewRouter viewRouter;

    private Contact contact;
    private final ContactsRepository repository;

    ViewContactPane(Contact contact, ViewRouter viewRouter, ContactsRepository repository) throws IOException {
        this.viewRouter = viewRouter;
        this.contact = contact;
        this.repository = repository;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewContactPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        viewContactFirstName.setText(contact.getFirstName());
        viewContactLastName.setText(contact.getLastName());
        viewContactStreetAddress.setText(contact.getStreetAddress());
        viewContactPostalCode.setText(contact.getPostalCode());
        viewContactTelephoneNumber.setText(String.valueOf(contact.getTelephoneNumber()));
        viewContactAge.setText(String.valueOf(contact.getAge()));
    }

    @FXML protected void backToSearch() throws IOException {
        viewRouter.showSearchContactPane();
    }

    @FXML protected void removeContact() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete user");
        alert.setContentText("Are you sure you want to deleteContact this user?");

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
            RemoveContact removeContact = new RemoveContact(repository, contact);
            RemoveContact.Result result = removeContact.execute();

            updateUIWithRemoveOperationResult(result);
        }

    }

    private void updateUIWithRemoveOperationResult(RemoveContact.Result result){
        switch (result) {
            case SUCCESS:
                contactAlterationAlerts.setText("Contact deleted");
                clearAllInputFields();
                break;
            case CONTACT_DOES_NOT_EXIST:
                contactAlterationAlerts.setText("Contact does not exist.");
                break;
        }
    }

    private void clearAllInputFields() {
        viewContactFirstName.setText("");
        viewContactLastName.setText("");
        viewContactStreetAddress.setText("");
        viewContactPostalCode.setText("");
        viewContactTelephoneNumber.setText("");
        viewContactAge.setText(String.valueOf(""));
    }
}
