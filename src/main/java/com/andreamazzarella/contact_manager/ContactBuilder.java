package com.andreamazzarella.contact_manager;

public class ContactBuilder {

    private String firstName = "";
    private String lastName = "";
    private String streetAddress = "";
    private String postalCode = "";
    private TelephoneNumber telephoneNumber = new TelephoneNumber("0");
    private Age age;


    public ContactBuilder() {
        try {
            age = new Age("18");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ContactBuilder setFirstName(String firstName) {
       this.firstName = firstName;
       return this;
    }

    public Contact build() {
        return new Contact(firstName, lastName, streetAddress, postalCode, telephoneNumber, age);
    }

    public ContactBuilder setAge(int age) {
        try {
            this.age = new Age(Integer.toString(age));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
