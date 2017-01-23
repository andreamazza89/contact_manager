package com.andreamazzarella.contact_manager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgeTest {

    @Test
    public void defaultsToZeroWithInvalidFormat() {
        Age testAge = new Age("ciao");

        assertEquals("0", testAge.getValue());
    }

    @Test
    public void retainsAValidNumber() {
        Age testAge = new Age("00443253");

        assertEquals("00443253", testAge.getValue());
    }

    @Test
    public void ignoresWhiteSpace() {
        Age testAge = new Age(" 0 0 44");

        assertEquals("0044", testAge.getValue());
    }

    @Test
    public void defaultsToZeroWithAnotherInvalidFormat() {
        Age testAge = new Age("4bla5 ");

        assertEquals("0", testAge.getValue());
    }
}
