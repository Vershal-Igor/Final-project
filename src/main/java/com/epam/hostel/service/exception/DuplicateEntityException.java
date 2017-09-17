package com.epam.hostel.service.exception;

/**
 * {@code DuplicateEntityException} throws when some
 * functionality try to create (or some thing like that)
 * already existing record.
 *
 * @author Vershal
 * @version 1.0
 */

public class DuplicateEntityException extends ServiceException {

    public DuplicateEntityException(String message) {
        super(message);
    }
}
