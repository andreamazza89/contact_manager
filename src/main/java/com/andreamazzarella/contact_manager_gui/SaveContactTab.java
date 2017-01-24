package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class SaveContactTab extends Tab {
    @FXML private TextField newFirstName;
    @FXML private TextField newLastName;
    @FXML private TextField newStreetAddress;
    @FXML private TextField newPostalCode;
    @FXML private TextField newTelephoneNumber;
    @FXML private TextField newAge;

    @FXML private TextField searchTerm;

    @FXML private Text contactSavingAlerts;

    private ContactsRepository repository;

    SaveContactTab(ContactsRepository repository) throws IOException {
        this.repository = repository;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SaveContactsTab.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    @FXML
    protected void saveContact(ActionEvent actionEvent) {
        if (userInputIsValid()) {
            Contact newContact = createContactFromFields();

            AddContact addContact = new AddContact(repository, newContact);
            AddContact.Result result = addContact.execute();

            updateUIWithOperationResult(result);
        } else {
           showInvalidUserInput();
        }
    }

    private void showInvalidUserInput() {
        contactSavingAlerts.setText("Invalid Age, please try again.");
    }

    private boolean userInputIsValid() {
        String userInput = newAge.getText();
        return (userInput.matches("\\d+") && Age.canBeCreatedWith(Integer.parseInt(userInput)));
    }

    private void updateUIWithOperationResult(AddContact.Result result) {
        switch (result) {
            case SUCCESS:
                contactSavingAlerts.setText("Contact saved!");
                break;
            case UNDER_MINIMUM_AGE:
                contactSavingAlerts.setText("Contact not saved: under minimum toInt!");
                break;
        }
    }

    private Contact createContactFromFields() {
        Age age = new Age(Integer.parseInt(newAge.getText()));
        String firstName = newFirstName.getText();
        String lastName = newLastName.getText();
        String streetAddress = newStreetAddress.getText();
        String postalCode = newPostalCode.getText();
        TelephoneNumber telephoneNumber = new TelephoneNumber(newTelephoneNumber.getText());

        return new Contact(firstName, lastName, streetAddress, postalCode, telephoneNumber, age);
    }
}