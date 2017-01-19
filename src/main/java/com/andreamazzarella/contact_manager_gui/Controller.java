package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.AddContact;
import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.InMemoryRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.List;

public class Controller {
    @FXML private TableView<Contact> searchResults;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn streetAddressColumn;
    @FXML private TableColumn postalCodeColumn;
    @FXML private TableColumn telephoneNumberColumn;
    @FXML private TableColumn ageColumn;

    @FXML private TextField newFirstName;
    @FXML private TextField newLastName;
    @FXML private TextField newStreetAddress;
    @FXML private TextField newPostalCode;
    @FXML private TextField newTelephoneNumber;
    @FXML private TextField newAge;

    @FXML private TextField searchTerm;

    @FXML private Text contactSavingAlerts;

    @FXML private InMemoryRepository repository;

    public Controller(InMemoryRepository repository) {
        this.repository = repository;
    }

    @FXML
    protected void saveContact(ActionEvent actionEvent) {
        String firstName = newFirstName.getText();
        String lastName = newLastName.getText();
        String streetAddress = newStreetAddress.getText();
        String postalCode = newPostalCode.getText();
        int telephoneNumber = Integer.parseInt(newTelephoneNumber.getText());
        int age = Integer.parseInt(newAge.getText());

        Contact newContact = new Contact(firstName, lastName, streetAddress, postalCode, telephoneNumber, age);

        AddContact addContact = new AddContact(repository, newContact);
        AddContact.Result result = addContact.execute();

        switch (result) {
            case SUCCESS:
                contactSavingAlerts.setText("Contact saved!");
                break;
            case UNDER_MINIMUM_AGE:
                contactSavingAlerts.setText("Contact not saved: under minimum age!");
                break;
        }
    }

    @FXML
    protected void updateSearchResults(ActionEvent actionEvent) {
        String searchFor = searchTerm.getText();
        List<Contact> matches = repository.findContact(searchFor);
        ObservableList contactsFound = FXCollections.observableArrayList(matches);

        updateContactsInTable(contactsFound);
    }

    @FXML
    private void updateContactsInTable(ObservableList<Contact> contactsFound) {
        searchResults.setItems(contactsFound);

        firstNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getFirstName());
            }
        });

        lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getLastName());
            }
        });

        streetAddressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getStreetAddress());
            }
        });

        postalCodeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getPostalCode());
            }
        });

        telephoneNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getTelephoneNumber());
            }
        });

        ageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
                public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                    return new ReadOnlyObjectWrapper<>(contact.getValue().getAge());
                }
        });
    }

}
