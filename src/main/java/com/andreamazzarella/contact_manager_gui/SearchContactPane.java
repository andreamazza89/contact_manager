package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.InMemoryRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class SearchContactPane extends GridPane {
    @FXML private TextField searchTerm;

    @FXML private TableView<Contact> searchResultsTable;
    @FXML private TableColumn<Contact, String> firstNameColumn;
    @FXML private TableColumn<Contact, String> lastNameColumn;
    @FXML private TableColumn<Contact, String> streetAddressColumn;
    @FXML private TableColumn<Contact, String> postalCodeColumn;
    @FXML private TableColumn<Contact, String> telephoneNumberColumn;
    @FXML private TableColumn<Contact, String> ageColumn;
    @FXML private TableColumn<Contact, String> viewContactColumn;

    private InMemoryRepository repository;
    private ViewRouter viewRouter;

    public SearchContactPane(InMemoryRepository repository, ViewRouter viewRouter) throws IOException {
        this.repository = repository;
        this.viewRouter = viewRouter;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchContactsPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        setCellValueFactories();
        loadAllContacts();
    }

    private void loadAllContacts() {
        List<Contact> allContacts = repository.allContacts();
        updateContactsInSearchTable(makeContactListObservable(allContacts));
    }

    @FXML
    protected void updateSearchResults(ActionEvent actionEvent) {
        String searchFor = searchTerm.getText();
        List<Contact> matches = repository.findContact(searchFor);

        updateContactsInSearchTable(makeContactListObservable(matches));
    }

    @FXML
    protected void viewContactLink() throws IOException {
        Contact selectedContact = searchResultsTable.getSelectionModel().getSelectedItem();
        viewRouter.showViewContactPane(selectedContact);
    }

    private ObservableList<Contact> makeContactListObservable(List<Contact> matches) {
        return FXCollections.observableArrayList(matches);
    }

    private void updateContactsInSearchTable(ObservableList<Contact> contacts) {
        searchResultsTable.setItems(contacts);
    }

    private void setCellValueFactories() {
        firstNameColumn.setCellValueFactory(contact -> new ReadOnlyObjectWrapper<>(contact.getValue().getFirstName()));

        lastNameColumn.setCellValueFactory(contact -> new ReadOnlyObjectWrapper<>(contact.getValue().getLastName()));

        streetAddressColumn.setCellValueFactory(contact -> new ReadOnlyObjectWrapper<>(contact.getValue().getStreetAddress()));

        postalCodeColumn.setCellValueFactory(contact -> new ReadOnlyObjectWrapper<>(contact.getValue().getPostalCode()));

        telephoneNumberColumn.setCellValueFactory(contact -> new ReadOnlyObjectWrapper<>(String.valueOf(contact.getValue().getTelephoneNumber())));

        ageColumn.setCellValueFactory(contact -> new ReadOnlyObjectWrapper<>(String.valueOf(contact.getValue().getAge())));
    }

}