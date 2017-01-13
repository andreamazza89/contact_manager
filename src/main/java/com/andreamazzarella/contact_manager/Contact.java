package com.andreamazzarella.contact_manager;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private HashMap<String, String> contactInfo;

    public Contact(HashMap<String, String> contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return this.contactInfo.get("name");
    }

    public boolean nameContains(String searchTerm) {
        Pattern caseInsensitiveSearchTerm = Pattern.compile(searchTerm + ".*", Pattern.CASE_INSENSITIVE);
        Matcher searchMatch = caseInsensitiveSearchTerm.matcher(getName());
        return searchMatch.matches();
    }
}
