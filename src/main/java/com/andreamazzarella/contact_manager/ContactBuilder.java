package com.andreamazzarella.contact_manager;

public class ContactBuilder {

    private String firstName = "";
    private String lastName = "";
    private String streetAddress = "";
    private String postalCode = "";
    private TelephoneNumber telephoneNumber = new TelephoneNumber("0");
    private Age age = new Age(18);


    public ContactBuilder() {
    }

    public ContactBuilder setFirstName(String firstName) {
       this.firstName = firstName;
       return this;
    }

    public Contact build() {
        return new Contact(firstName, lastName, streetAddress, postalCode, telephoneNumber, age);
    }

    public ContactBuilder setAge(int age) {
        this.age = new Age(age);
        return this;
    }

    public ContactBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactBuilder setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public ContactBuilder setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public ContactBuilder setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = new TelephoneNumber(telephoneNumber);
        return this;
    }
}
