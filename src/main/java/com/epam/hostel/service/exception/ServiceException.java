package com.epam.hostel.service.exception;

/**
 * {@code ServiceException} throws when some exception
 * occur while service working.
 *
 * @author Vershal
 * @version 1.0
 */

public class ServiceException extends Exception {

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
