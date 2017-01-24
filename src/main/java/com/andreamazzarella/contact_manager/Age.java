package com.andreamazzarella.contact_manager;

import java.util.regex.Pattern;

public class Age {
    private final String age;

    public Age(String age) {
        age = removeAllWhiteSpace(age);

        if (isValidNumber(age)) {
            this.age = age;
        } else {
            this.age = "0";
        }
    }

    protected boolean isEqualOrOlderThan(Age other_age) {
       return Integer.parseInt(age) >= Integer.parseInt(other_age.getValue());
    }

    public String getValue() {
        return age;
    }

    private boolean isValidNumber(String age) {
        Pattern validNumber = Pattern.compile("\\d+");
        return validNumber.matcher(age).matches();
    }

    private String removeAllWhiteSpace(String age) {
        age = age.replaceAll("\\s", "");
        return age;
    }

}
