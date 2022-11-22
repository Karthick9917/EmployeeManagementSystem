package com.ideas2it.employeeManagementSystem.Exception;

/**
 * It is a user defined exception to handle the exceptions.
 * Implements exception method.
 * <p>
 * *@version    1.8.0_281
 * *@author     Karthick
 */
public class EmsException extends RuntimeException {

    /**
     * This method shows message of exception thrown.
     *
     * @param message - show the message
     */
    public EmsException(String message) {
        super(message);
    }
}
