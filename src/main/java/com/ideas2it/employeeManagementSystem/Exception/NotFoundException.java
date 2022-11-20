package com.ideas2it.employeeManagementSystem.Exception;

/**
 * It is a user defined exception to handle the exceptions.
 * Implements exception method.
 *
 *  *@version    1.8.0_281
 *  *@author     Karthick
 */
public class NotFoundException extends RuntimeException {

    /**
     * This method shows message of exception thrown.
     * @param message - show the message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
