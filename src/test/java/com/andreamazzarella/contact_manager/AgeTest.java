package com.andreamazzarella.contact_manager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class AgeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsExceptionWithInvalidAge() throws Age.InvalidAgeException {
        thrown.expect(Age.InvalidAgeException.class);
        thrown.expectMessage("Invalid age");

        new Age("ciao");
    }

    @Test
    public void retainsAValidNumber() throws Age.InvalidAgeException {
        Age testAge = new Age("00443253");

        assertEquals("00443253", testAge.getValue());
    }

    @Test
    public void ignoresWhiteSpace() throws Age.InvalidAgeException {
        Age testAge = new Age(" 0 0 44");

        assertEquals("0044", testAge.getValue());
    }

    @Test
    public void throwsExceptionWithAnotherInvalidFormat() throws Age.InvalidAgeException {
        thrown.expect(Age.InvalidAgeException.class);
        thrown.expectMessage("Invalid age");

        new Age("4 bla5 9/");
    }

    @Test
    public void knowsWhoIsOlder() throws Age.InvalidAgeException {
        Age testAgeOne = new Age("10");
        Age testAgeTwo = new Age("30");
        Age testAgeThree = new Age("10");

        assertEquals(true, testAgeTwo.isEqualOrOlderThan(testAgeOne));
        assertEquals(false, testAgeOne.isEqualOrOlderThan(testAgeTwo));
        assertEquals(true, testAgeOne.isEqualOrOlderThan(testAgeThree));
    }
}
