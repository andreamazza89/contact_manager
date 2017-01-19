package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.InMemoryRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class SearchTab extends Tab {
    @FXML private TextField searchTerm;

    @FXML private TableView<Contact> searchResults;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn streetAddressColumn;
    @FXML private TableColumn postalCodeColumn;
    @FXML private TableColumn telephoneNumberColumn;
    @FXML private TableColumn ageColumn;

    private InMemoryRepository repository;

    public SearchTab(InMemoryRepository repository) throws IOException {
        this.repository = repository;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchContactsTab.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    @FXML
    protected void updateSearchResults(ActionEvent actionEvent) {
        String searchFor = searchTerm.getText();
        List<Contact> matches = repository.findContact(searchFor);
        ObservableList contactsFound = FXCollections.observableArrayList(matches);

        updateContactsInSearchTable(contactsFound);
    }

    @FXML
    private void updateContactsInSearchTable(ObservableList<Contact> contactsFound) {
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
