package com.epam.hostel.service.exception;

/**
 * {@code InvalidDataException} throws when user
 * handed to application an invalid date.
 *
 * @author Vershal
 * @version 1.0
 */

public class InvalidDataException extends ServiceException {

    public InvalidDataException(String message) {
        super(message);
    }
}
