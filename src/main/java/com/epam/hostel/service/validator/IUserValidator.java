package com.epam.hostel.service.validator;


import com.epam.hostel.model.user.User;

/**
 * Interface {@code IUserValidator} extends from {@code Validator}
 * interface and added methods for validating {@code User} objects.
 *
 * @author Vershal
 * @version 1.0
 */

public interface IUserValidator extends Validator<User> {

    /**
     * Method validate login and password of {@code User} objects.
     */
    boolean validateLogPass(User user);

    /**
     * Method validate name, surname and login of {@code User} objects.
     */
    boolean validateNameSurnameLogin(User user);
}
