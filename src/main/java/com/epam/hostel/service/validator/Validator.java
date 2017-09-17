package com.epam.hostel.service.validator;

/**
 * Interface define method(s) for validating objects.
 *
 * @param <E> type of checked object
 * @author Vershal
 * @version 1.0
 */

public interface Validator<E> {

    /**
     * Method checks transferred object.
     *
     * @param entity checked object
     */
    boolean validate(E entity);
}
