package com.ideas2it.employeeManagementSystem.util;

import com.ideas2it.employeeManagementSystem.Exception.EmsException;
import com.ideas2it.employeeManagementSystem.constants.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidationUtil {

    /**
     * Checks and return true or false based on the given user Input matches
     * to the given format or not.
     *
     * @param date - for check the given date is valid or not.
     * @return the parsed date once the given date is valid.
     * @throws EmsException - throws a String message.
     */
    public static LocalDate dateValid(String date) throws EmsException {
        LocalDate parsedDate;
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(Constants.DATE_FORMAT);
        try {
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new EmsException(Constants
                    .ASKING_VALID_INPUT + "date(YYYY-MM-DD)");
        }
        return parsedDate;
    }
}
