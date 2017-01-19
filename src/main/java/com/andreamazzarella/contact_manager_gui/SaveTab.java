package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.AddContact;
import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.InMemoryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class SaveTab extends Tab {
    @FXML
    private TextField newFirstName;
    @FXML
    private TextField newLastName;
    @FXML
    private TextField newStreetAddress;
    @FXML
    private TextField newPostalCode;
    @FXML
    private TextField newTelephoneNumber;
    @FXML
    private TextField newAge;

    @FXML
    private TextField searchTerm;

    @FXML
    private Text contactSavingAlerts;

    private InMemoryRepository repository;

    public SaveTab(InMemoryRepository repository) throws IOException {
        this.repository = repository;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SaveContactsTab.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    @FXML
    protected void saveContact(ActionEvent actionEvent) {
        Contact newContact = createContactFromFields();

        AddContact addContact = new AddContact(repository, newContact);
        AddContact.Result result = addContact.execute();

        updateUIWithOperationResult(result);
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

    private Contact createContactFromFields() {
        String firstName = newFirstName.getText();
        String lastName = newLastName.getText();
        String streetAddress = newStreetAddress.getText();
        String postalCode = newPostalCode.getText();
        int telephoneNumber = Integer.parseInt(newTelephoneNumber.getText());
        int age = Integer.parseInt(newAge.getText());

        return new Contact(firstName, lastName, streetAddress, postalCode, telephoneNumber, age);
    }
}
