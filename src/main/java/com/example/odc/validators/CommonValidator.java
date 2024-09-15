package com.example.odc.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

public class CommonValidator {
    private static final String PHONE_REGEX = "^(76|77|78)\\d{7}$";
    private static final RegexValidator phoneValidator = new RegexValidator(PHONE_REGEX);

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&]).{5,}$";
    private static final RegexValidator passwordValidator = new RegexValidator(PASSWORD_REGEX);


    public static boolean isValidEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public static boolean isValidPassword(String password) {
        return passwordValidator.isValid(password);
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phoneValidator.isValid(phone);
    }

    public static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int parseInteger(String input) {
        if (isValidInteger(input)) {
            return Integer.parseInt(input);
        } else {
            throw new NumberFormatException("Invalid integer input.");
        }
    }

    public static String filterWhitespace(String input) {
        if (input == null) {
            return null;
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    public static String filterSpecialCharacters(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("[^a-zA-Z0-9@#\\s]", "");
    }

    public static String filterField(String input){
        return filterSpecialCharacters(filterWhitespace(input));
    }
    public static boolean isEmptyField(String input) {
        return filterWhitespace(input).isEmpty();
    }
}
