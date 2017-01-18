package com.andreamazzarella.contact_manager_gui;

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
import javafx.util.Callback;

public class Controller {
    @FXML private TextField searchTerm;
    @FXML private TableView<Contact> searchResults;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn numberColumn;
    @FXML private InMemoryRepository repository;


    @FXML
    protected void displaySearchResults(ActionEvent actionEvent) {
        String searchFor = searchTerm.getText();
        ObservableList contactsFound = FXCollections.observableArrayList(repository.findContact(searchFor));

        updateContactsInTable(contactsFound);
    }

    @FXML
    private void updateContactsInTable(ObservableList<Contact> contactsFound) {
        searchResults.setItems(contactsFound);

        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getName());
            }
        });

        numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getNumber());
            }
        });
    }

    public void setRepo(InMemoryRepository repository) {
        this.repository = repository;
    }

}
