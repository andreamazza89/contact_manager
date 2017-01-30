package com.andreamazzarella.contact_manager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AgeTest {
    Age tenYears = new Age(10);
    Age thirtyYears = new Age(30);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void justBeANumber() {
        assertEquals(30, thirtyYears.inYears());
    }

    @Test
    public void knowWhoIsOlder() {
        assertEquals(true, thirtyYears.isEqualOrOlderThan(tenYears));
        assertEquals(false, tenYears.isEqualOrOlderThan(thirtyYears));
    }

    @Test
    public void canBeEqual() {
        assertEquals(true, tenYears.isEqualOrOlderThan(tenYears));
    }

    @Test
    public void notAllowUnborns() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid age: -55");

        new Age(-55);
    }

    @Test
    public void notAllowJustBorns() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid age: 0");

        new Age(0);
    }

    @Test
    public void knowIfGivenAgeIsValid() {
        assertFalse(Age.canBeCreatedWith(-55));
        assertTrue(Age.canBeCreatedWith(20));
    }
}