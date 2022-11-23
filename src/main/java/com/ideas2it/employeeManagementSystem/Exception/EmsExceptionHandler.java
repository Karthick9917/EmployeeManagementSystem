package com.ideas2it.employeeManagementSystem.Exception;

import com.ideas2it.employeeManagementSystem.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * It is used to handle the exceptions.
 * <p>
 * *@version    1.8.0_281
 * *@author     Karthick
 */
@RestControllerAdvice
public class EmsExceptionHandler {

    /**
     * It is used to receive an exception and return the response
     *
     * @param emsException - pass the object.
     * @return the response
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = {EmsException.class})
    public ErrorDTO badRequestMessage(EmsException emsException) {
        return new ErrorDTO(emsException.getMessage(), 403);
    }

    /**
     * It is used to receive an exception and return the response
     *
     * @param notFoundException - pass the object.
     * @return the response
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundException.class})
    public ErrorDTO notFound(NotFoundException notFoundException) {
        return new ErrorDTO(notFoundException.getMessage(), 404);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ErrorDTO badRequestException(SQLException sqlException) {
        return new ErrorDTO("Internal server error", 500);
    }
}
