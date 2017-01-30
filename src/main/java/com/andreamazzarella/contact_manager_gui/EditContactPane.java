package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class EditContactPane extends GridPane {
    @FXML private TextField newFirstName;
    @FXML private TextField newLastName;
    @FXML private TextField newStreetAddress;
    @FXML private TextField newPostalCode;
    @FXML private TextField newTelephoneNumber;
    @FXML private TextField newAge;

    @FXML private Text contactEditingAlerts;

    private ContactsRepository repository;
    private final Contact contact;
    private final ViewRouter viewRouter;

    EditContactPane(Contact contact, ViewRouter viewRouter, ContactsRepository repository) throws IOException {
        this.repository = repository;
        this.contact = contact;
        this.viewRouter = viewRouter;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditContactPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        newFirstName.setText(contact.getFirstName());
        newLastName.setText(contact.getLastName());
        newStreetAddress.setText(contact.getStreetAddress());
        newPostalCode.setText(contact.getPostalCode().orElse(""));
        newTelephoneNumber.setText(contact.getTelephoneNumber().toString());
        newAge.setText(String.valueOf(contact.getAge().toInt()));
    }

    @FXML protected void editContact() {
        ContactInputValidator validator = new ContactInputValidator(newAge.getText(), newTelephoneNumber.getText());

        if (validator.userInputIsValid()) {
            EditContact editContact = new EditContact(repository, contact);

            editContact.changeFirstNameTo(newFirstName.getText());
            editContact.changeLastNameTo(newLastName.getText());
            editContact.changeStreetAddressTo(newStreetAddress.getText());
            editContact.changePostalCodeTo(newPostalCode.getText());
            editContact.changeTelephoneNumberTo(new TelephoneNumber(newTelephoneNumber.getText()));
            editContact.changeAgeTo(new Age(Integer.parseInt(newAge.getText())));

            EditContact.Result result = editContact.execute();

            updateUIWithOperationResult(result);
        } else {
            String errorMessages = validator.errorMessages();
            contactEditingAlerts.setText(errorMessages);
        }
    }

    @FXML protected void backToSearch() throws IOException {
        viewRouter.showSearchContactPane();
    }

    private void updateUIWithOperationResult(EditContact.Result result) {
        switch (result) {
            case SUCCESS:
                contactEditingAlerts.setText("Contact updated!");
                break;
            case UNDER_MINIMUM_AGE:
                contactEditingAlerts.setText("Contact not updated: under minimum age!");
                break;
        }
    }
}
