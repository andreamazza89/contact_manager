package com.andreamazzarella.contact_manager;

import org.junit.Test;

import static org.junit.Assert.*;

public class TelephoneNumberTest {

    @Test
    public void defaultsToZeroWithInvalidFormat() {
        TelephoneNumber testNumber = new TelephoneNumber("ciao");

        assertEquals("0", testNumber.getValue());
    }

    @Test
    public void retainsAValidNumber() {
        TelephoneNumber testNumber = new TelephoneNumber("00443253");

        assertEquals("00443253", testNumber.getValue());
    }

    @Test
    public void ignoresWhiteSpace() {
        TelephoneNumber testNumber = new TelephoneNumber(" 0 0 44");

        assertEquals("0044", testNumber.getValue());
    }

    @Test
    public void defaultsToZeroWithAnotherInvalidFormat() {
        TelephoneNumber testNumber = new TelephoneNumber("4bla5 ");

        assertEquals("0", testNumber.getValue());
    }
}
