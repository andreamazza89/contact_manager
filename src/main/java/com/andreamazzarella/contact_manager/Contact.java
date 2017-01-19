package com.andreamazzarella.contact_manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private final String firstName;
    private final String streetAddress;
    private final String postalCode;
    private final int telephoneNumber;
    private final int age;
    private final String lastName;

    public Contact(String firstName, String lastName, String streetAddress, String postalCode, int telephoneNumber, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.telephoneNumber = telephoneNumber;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public int getNumber() {
        return telephoneNumber;
    }

    public int getAge() {
        return age;
    }

    // this will likely end up in a separate class but I am waiting for more information to emerge before moving it away
    public boolean nameContains(String searchTerm) {
        Pattern caseInsensitiveSearchTerm = Pattern.compile(searchTerm + ".*", Pattern.CASE_INSENSITIVE);
        Matcher searchMatch = caseInsensitiveSearchTerm.matcher(getFirstName());
        return searchMatch.matches();
    }

}
