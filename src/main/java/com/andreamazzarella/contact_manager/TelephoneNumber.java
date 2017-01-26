package com.andreamazzarella.contact_manager;

import java.util.regex.Pattern;

public class TelephoneNumber {

    private final String number;

    public TelephoneNumber(String number) {
        checkValidityOfNumber(number);

        this.number = removeAllWhiteSpace(number);
    }

    @Override
    public String toString() {
        return number;
    }

    private void checkValidityOfNumber(String number) {
        if (!isValidNumber(number)) throw new IllegalArgumentException("Sorry, but the telephone number you entered is not valid.");
    }

    private static boolean isValidNumber(String number) {
        number = removeAllWhiteSpace(number);
        Pattern validNumber = Pattern.compile("\\d+");
        return validNumber.matcher(number).matches();
    }

    private static String removeAllWhiteSpace(String number) {
        number = number.replaceAll("\\s", "");
        return number;
    }

    public static boolean canBeCreatedWith(String inputNumber) {
        return isValidNumber(inputNumber);
    }
}
