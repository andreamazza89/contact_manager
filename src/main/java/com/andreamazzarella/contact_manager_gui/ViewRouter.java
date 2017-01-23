package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Contact;
import com.andreamazzarella.contact_manager.InMemoryRepository;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class ViewRouter {

    private final Tab searchContactTab;
    private final Tab saveContactTab;
    private final InMemoryRepository myRepo;

    public ViewRouter(InMemoryRepository myRepo) {
        this.searchContactTab = new Tab("Search Contacts");
        this.saveContactTab = new Tab("Add Contacts");
        this.myRepo = myRepo;
    }

    public TabPane generateRoot() throws IOException {
        SearchContactPane searchPane = new SearchContactPane(myRepo, this);
        SaveContactTab saveContactTab = new SaveContactTab(myRepo);

        searchContactTab.setContent(searchPane);

        TabPane root = new TabPane();
        root.getTabs().add(saveContactTab);
        root.getTabs().add(searchContactTab);

        return root;
    }

    public void showViewContactPane(Contact selectedContact) throws IOException {

        //? I should not reinitialise this view every time we switch to it

        searchContactTab.setContent(new ViewContactPane(selectedContact, this, myRepo));
    }

    public void showSearchContactPane() throws IOException {

        //? I should not reinitialise this view every time we switch to it

        searchContactTab.setContent(new SearchContactPane(myRepo, this));
    }
}
