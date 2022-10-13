package com.ideas2it.employeeManagementSystem.util;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.EmployeeConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ValidationUtil {

    /**
     * Checks and return true or false based on the given user Input matches
     * to the given format or not.
     *
     * @param userInput - user input to be validated.
     * @param formatter - check the user input based on the formatter.
     * @return true or false based on whether the user input is valid or not.
     */
    public static boolean isInputValid(String formatter, String userInput) {
        return Pattern.matches(formatter, userInput);
    }

    /**
     * Checks and return true or false based on the given user Input matches
     * to the given format or not.
     *
     * @param date - for check the given date is valid or not.
     * @return the parsed date once the given date is valid.
     * @throws EmsException
     */
    public static LocalDate dateValid(String date) throws EmsException {
        LocalDate parsedDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern
                (EmployeeConstants.DATE_FORMAT);
        try {
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new EmsException(EmployeeConstants.ASKING_VALID_INPUT + "date(YYYY-MM-DD)");
        }
        return parsedDate;
    }
}
