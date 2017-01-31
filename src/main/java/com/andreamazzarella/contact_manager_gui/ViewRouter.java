package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.ContactsRepository;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

class ViewRouter {

    private final BorderPane mainView;
    private final ContactsRepository myRepo;

    ViewRouter(ContactsRepository myRepo) {
        this.mainView = new BorderPane();
        this.myRepo = myRepo;
    }

    BorderPane generateRoot() throws IOException {
        mainView.setCenter(new SearchContactPane(myRepo, this));
        return mainView;
    }

    void showViewContactPane(Contact selectedContact) throws IOException {
        mainView.setCenter(new ViewContactPane(selectedContact, this, myRepo));
    }

    void showSearchContactPane() throws IOException {
        mainView.setCenter(new SearchContactPane(myRepo, this));
    }

    void showEditContactPane(Contact selectedContact) throws IOException {
        mainView.setCenter(new EditContactPane(selectedContact, this, myRepo));
    }

    void showAddContactPane() throws IOException {
        mainView.setCenter(new SaveContactPane(this, myRepo));
    }
}