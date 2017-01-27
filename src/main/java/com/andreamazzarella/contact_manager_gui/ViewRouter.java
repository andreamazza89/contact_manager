package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.ContactsRepository;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

class ViewRouter {

    private final Tab searchContactTab;
    private final ContactsRepository myRepo;

    ViewRouter(ContactsRepository myRepo) {
        this.searchContactTab = new Tab("Search Contacts");
        this.myRepo = myRepo;
    }

    TabPane generateRoot() throws IOException {
        SearchContactPane searchPane = new SearchContactPane(myRepo, this);
        SaveContactTab saveContactTab = new SaveContactTab(myRepo);

        searchContactTab.setContent(searchPane);

        TabPane root = new TabPane();
        root.getTabs().add(saveContactTab);
        root.getTabs().add(searchContactTab);

        return root;
    }

    void showViewContactPane(Contact selectedContact) throws IOException {
        searchContactTab.setContent(new ViewContactPane(selectedContact, this, myRepo));
    }

    void showSearchContactPane() throws IOException {
        searchContactTab.setContent(new SearchContactPane(myRepo, this));
    }

    void showEditContactPane(Contact selectedContact) throws IOException {
        searchContactTab.setContent(new EditContactPane(selectedContact, this, myRepo));
    }
}