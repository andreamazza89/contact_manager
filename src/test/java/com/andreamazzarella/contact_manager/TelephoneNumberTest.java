package com.andreamazzarella.contact_manager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TelephoneNumberTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void doesNotAllowInvalidFormat() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Sorry, but the telephone number you entered is not valid.");

         new TelephoneNumber("ciao");
    }

    @Test
    public void retainsAValidNumber() {
        TelephoneNumber testNumber = new TelephoneNumber("00443253");

        assertEquals("00443253", testNumber.toString());
    }

    @Test
    public void ignoresWhiteSpace() {
        TelephoneNumber testNumber = new TelephoneNumber(" 0 0 44");

        assertEquals("0044", testNumber.toString());
    }

    @Test
    public void knowsIfGivenNumberIsValid() {
        assertFalse(TelephoneNumber.canBeCreatedWith("ciao"));
        assertTrue(TelephoneNumber.canBeCreatedWith("00 77 44"));
    }

}
