package com.andreamazzarella.contact_manager_gui;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactInputValidatorTest {

    @Test
    public void doesNotAcceptInvalidAge() {
        ContactInputValidator validator = new ContactInputValidator("my age is 33", "055444");
        
        assertEquals(false, validator.userInputIsValid());
        assertEquals("Invalid Age, please try again.\n", validator.errorMessages());
    }

    @Test
    public void doesNotAcceptNegativeAge() {
        ContactInputValidator validator = new ContactInputValidator("-33", "055444");

        assertEquals(false, validator.userInputIsValid());
        assertEquals("Invalid Age, please try again.\n", validator.errorMessages());
    }

    @Test
    public void doesNotAcceptInvalidTelephoneNumber() {
        ContactInputValidator validator = new ContactInputValidator("44", "number 444");

        assertEquals(false, validator.userInputIsValid());
        assertEquals("Invalid telephone number, please try again.\n", validator.errorMessages());
    }

    @Test
    public void acceptsValidInput() {
        ContactInputValidator validator = new ContactInputValidator("44", "4 4 4");

        assertEquals(true, validator.userInputIsValid());
    }

}
