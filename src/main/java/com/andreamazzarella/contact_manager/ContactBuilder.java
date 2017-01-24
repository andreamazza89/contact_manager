package com.andreamazzarella.contact_manager;

public class ContactBuilder {

    private String firstName = "";
    private String lastName = "";
    private String streetAddress = "";
    private String postalCode = "";
    private TelephoneNumber telephoneNumber = new TelephoneNumber("0");
    private Age age = new Age("18");


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
        this.age = new Age(Integer.toString(age));
        return this;
    }
}
