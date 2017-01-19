package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.AddContact;
import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.ContactBuilder;
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
    @FXML private TextField searchTerm;
    @FXML private TextField newAge;

    @FXML private Text contactSavingAlerts;

    @FXML private TableView<Contact> searchResults;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn numberColumn;
    @FXML private TableColumn ageColumn;

    @FXML private InMemoryRepository repository;

    public Controller(InMemoryRepository repository) {
        this.repository = repository;
    }

    @FXML
    protected void saveContact(ActionEvent actionEvent) {
        Contact newContact = new ContactBuilder().setAge(Integer.parseInt(newAge.getText())).build();
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

        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getFirstName());
            }
        });

        numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
            public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                return new ReadOnlyObjectWrapper<>(contact.getValue().getNumber());
            }
        });

        ageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Contact, String>, ReadOnlyObjectWrapper>() {
                public ReadOnlyObjectWrapper call(TableColumn.CellDataFeatures<Contact, String> contact) {
                    return new ReadOnlyObjectWrapper<>(contact.getValue().getAge());
                }
        });
    }

}
