package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.ContactsRepository;
import com.andreamazzarella.contact_manager.RemoveContact;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class ViewContactPane extends GridPane {
    @FXML private Text viewContactFirstName;
    @FXML private Text viewContactLastName;
    @FXML private Text viewContactStreetAddress;
    @FXML private Text viewContactPostalCode;
    @FXML private Text viewContactTelephoneNumber;
    @FXML private Text viewContactAge;

    @FXML private Text contactAlterationAlerts;

    private ViewRouter viewRouter;

    ViewContactPane(Contact contact, ViewRouter viewRouter, ContactsRepository repository) throws IOException {
        this.viewRouter = viewRouter;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ViewContactPane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        viewContactFirstName.setText(contact.getFirstName());
        viewContactLastName.setText(contact.getLastName());
        viewContactStreetAddress.setText(contact.getStreetAddress());
        viewContactPostalCode.setText(contact.getPostalCode().orElse(""));
        viewContactTelephoneNumber.setText(contact.getTelephoneNumber().toString());
        viewContactAge.setText(String.valueOf(contact.getAge().inYears()));
    }

    @FXML protected void backToSearch() throws IOException {
        viewRouter.showSearchContactPane();
    }
}
