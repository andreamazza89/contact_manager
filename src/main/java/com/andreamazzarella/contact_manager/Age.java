package com.andreamazzarella.contact_manager;

public class Age {

    private final int age;

    public Age(int age) {
        checkValidityOfAge(age);
        this.age = age;
    }

    private void checkValidityOfAge(int age) {
        if(!isValid(age)) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
    }

    boolean isEqualOrOlderThan(Age other_age) {
       return age >= other_age.inYears();
    }

    public int inYears() {
        return age;
    }

    public static boolean canBeCreatedWith(int age) {
        return Age.isValid(age);
    }

    private static boolean isValid(int age) {
        return age > 0;
    }
}
