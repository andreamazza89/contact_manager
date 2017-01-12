package com.andreamazzarella.contact_manager;

import java.util.HashMap;

public class Contact {
    private HashMap<String, String> contactInfo;

    public Contact(HashMap<String, String> contactInfo) {
        this.contactInfo = contactInfo;
    }


    public String getName() {
        return this.contactInfo.get("name");
    }

    public boolean nameContains(String searchTerm) {
        return this.getName().contains(searchTerm);
    }
}
