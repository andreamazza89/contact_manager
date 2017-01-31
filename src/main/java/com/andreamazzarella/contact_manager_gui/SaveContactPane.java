package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class SaveContactPane extends GridPane {
    @FXML private TextField newFirstName;
    @FXML private TextField newLastName;
    @FXML private TextField newStreetAddress;
    @FXML private TextField newPostalCode;
    @FXML private TextField newTelephoneNumber;
    @FXML private TextField newAge;

    @FXML private Text contactSavingAlerts;

    private final ViewRouter viewRouter;
    private ContactsRepository repository;

    SaveContactPane(ViewRouter viewRouter, ContactsRepository repository) throws IOException {
        this.viewRouter = viewRouter;
        this.repository = repository;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SaveContactsPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    @FXML protected void saveContact() {
        ContactInputValidator validator = new ContactInputValidator(newAge.getText(), newTelephoneNumber.getText());

        if (validator.userInputIsValid()) {
            Contact newContact = createContactFromFields();

            AddContact addContact = new AddContact(repository, newContact);
            AddContact.Result result = addContact.execute();

            updateUIWithOperationResult(result);
        } else {
            String errorMessages = validator.errorMessages();
            contactSavingAlerts.setText(errorMessages);
        }
    }

    @FXML protected void backToSearch() throws IOException {
        viewRouter.showSearchContactPane();
    }

    private Contact createContactFromFields() {
        Age age = new Age(Integer.parseInt(newAge.getText()));
        String firstName = newFirstName.getText();
        String lastName = newLastName.getText();
        String streetAddress = newStreetAddress.getText();
        Optional<String> postalCode = newPostalCode.getText() == "" ? Optional.empty() : Optional.of(newPostalCode.getText());
        TelephoneNumber telephoneNumber = new TelephoneNumber(newTelephoneNumber.getText());

        return new Contact(firstName, lastName, streetAddress, postalCode, telephoneNumber, age);
    }

    private void updateUIWithOperationResult(AddContact.Result result) {
        switch (result) {
            case SUCCESS:
                contactSavingAlerts.setText("Contact saved!");
                break;
            case UNDER_MINIMUM_AGE:
                contactSavingAlerts.setText("Contact not saved: under minimum age!");
                break;
        }
    }
}