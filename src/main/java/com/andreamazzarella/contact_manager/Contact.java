package com.andreamazzarella.contact_manager;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private final Map<String, String> contactInfo;

    public Contact(Map<String, String> contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return contactInfo.get("name");
    }

    public boolean nameContains(String searchTerm) {
        Pattern caseInsensitiveSearchTerm = Pattern.compile(searchTerm + ".*", Pattern.CASE_INSENSITIVE);
        Matcher searchMatch = caseInsensitiveSearchTerm.matcher(getName());
        return searchMatch.matches();
    }

    public String getNumber() {
        return contactInfo.get("number");
    }
}
