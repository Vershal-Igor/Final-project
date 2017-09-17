package com.epam.hostel.service.exception;

import org.springframework.dao.DuplicateKeyException;

/**
 * {@code NoEntityException} throws when user
 * try to sign in by a non-existent login and password.
 *
 * @author Vershal
 * @version 1.0
 */

public class NoEntityException extends DuplicateKeyException {

    public NoEntityException(String message) {
        super(message);
    }
}
