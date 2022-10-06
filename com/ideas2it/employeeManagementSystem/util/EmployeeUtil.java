package com.ideas2it.employeeManagementSystem.util;

import java.util.regex.Pattern;

public class EmployeeUtil {

    /**
     * Checks and return true or false based on whether the user Input is valid
     * or not.
     *
     * @param userInput - user input to be validated.
     * @param formatter - check the user input based on the formatter.
     * @return true or false based on whether the user input is valid or not.
     */
    public static boolean isInputValid(String formatter, String userInput) {
        return Pattern.matches(formatter, userInput);
    }
}
