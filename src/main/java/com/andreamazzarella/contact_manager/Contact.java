package com.andreamazzarella.contact_manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private final String firstName;
    private final String streetAddress;
    private final String postalCode;
    private final TelephoneNumber telephoneNumber;
    private final Age age;
    private final String lastName;

    public Contact(String firstName, String lastName, String streetAddress, String postalCode, TelephoneNumber telephoneNumber, Age age) {
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

    public TelephoneNumber getTelephoneNumber() {
        return telephoneNumber;
    }

    public Age getAge() {
        return age;
    }

    // this will likely end up in a separate class but I am waiting for more information to emerge before moving it away
    public boolean nameContains(String searchTerm) {
        Pattern caseInsensitiveSearchTerm = Pattern.compile(searchTerm + ".*", Pattern.CASE_INSENSITIVE);
        Matcher searchMatch = caseInsensitiveSearchTerm.matcher(getFirstName());
        return searchMatch.matches();
    }

}
