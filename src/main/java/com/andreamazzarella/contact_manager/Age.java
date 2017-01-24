package com.andreamazzarella.contact_manager;

import java.util.regex.Pattern;

public class Age {
    private final String age;

    public Age(String age) throws InvalidAgeException {
        age = removeAllWhiteSpace(age);

        if (isDigitsOnly(age)) {
            this.age = age;
        } else {
            throw new InvalidAgeException("Invalid age");
        }
    }

    boolean isEqualOrOlderThan(Age other_age) {
       return Integer.parseInt(age) >= Integer.parseInt(other_age.getValue());
    }

    public String getValue() {
        return age;
    }

    private boolean isDigitsOnly(String age) {
        Pattern validNumber = Pattern.compile("\\d+");
        return validNumber.matcher(age).matches();
    }

    private String removeAllWhiteSpace(String age) {
        age = age.replaceAll("\\s", "");
        return age;
    }

    public class InvalidAgeException extends Exception {

        InvalidAgeException(String errorMessage) {
            super(errorMessage);
        }

    }
}
