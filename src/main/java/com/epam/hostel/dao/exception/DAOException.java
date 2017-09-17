package com.epam.hostel.dao.exception;

/**
 * {@code DAOException} throws when some exception
 * occur while DAO working.
 *
 * @author Vershal
 * @version 1.0
 */

public class DAOException extends Exception {
    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
