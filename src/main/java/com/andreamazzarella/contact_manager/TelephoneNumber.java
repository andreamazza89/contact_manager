package com.andreamazzarella.contact_manager;

import java.util.regex.Pattern;

class TelephoneNumber {

    private final String number;

    TelephoneNumber(String number) {
        number = removeAllWhiteSpace(number);

        if (isValidNumber(number)) {
            this.number = number;
        } else {
            this.number = "0";
        }
    }

    String getNumber() {
        return number;
    }

    private boolean isValidNumber(String number) {
        Pattern validNumber = Pattern.compile("\\d+");
        return validNumber.matcher(number).matches();
    }

    private String removeAllWhiteSpace(String number) {
        number = number.replaceAll("\\s", "");
        return number;
    }

}
