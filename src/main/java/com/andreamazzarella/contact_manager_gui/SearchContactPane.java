package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.ContactsRepository;
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

    private ContactsRepository repository;
    private ViewRouter viewRouter;

    SearchContactPane(ContactsRepository repository, ViewRouter viewRouter) throws IOException {
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
        //? not sure I can remove the apparent repetition here

        //? I could remove Demeter violation with a Contact presenter? would make the lambda much less lean though

        firstNameColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getFirstName()));

        lastNameColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getLastName()));

        streetAddressColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getStreetAddress()));

        postalCodeColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getPostalCode()));

        telephoneNumberColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getTelephoneNumber().getValue()));

        ageColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getAge().getValue()));
    }

}