package com.andreamazzarella.contact_manager_gui;

import com.andreamazzarella.contact_manager.Age;
import com.andreamazzarella.contact_manager.TelephoneNumber;

class ContactInputValidator {

    private final String inputAge;
    private final String inputTelephoneNumber;

    ContactInputValidator(String inputAge, String inputTelephoneNumber) {
        this.inputAge = inputAge;
        this.inputTelephoneNumber = inputTelephoneNumber;
    }

    boolean userInputIsValid() {
        return isValidAge() && isValidTelephoneNumber();
    }

    private boolean isValidAge() {
        return inputAge.matches("\\d+") && Age.canBeCreatedWith(Integer.parseInt(inputAge));
    }

    private boolean isValidTelephoneNumber() {
        return inputTelephoneNumber.matches("[\\d\\s]+") && TelephoneNumber.canBeCreatedWith(inputTelephoneNumber);
    }

    String errorMessages() {
        String errorMessage = "";

        if (!isValidTelephoneNumber()) errorMessage += "Invalid telephone number, please try again.\n";
        if (!isValidAge()) errorMessage += "Invalid Age, please try again.\n";

        return errorMessage;
    }
}
