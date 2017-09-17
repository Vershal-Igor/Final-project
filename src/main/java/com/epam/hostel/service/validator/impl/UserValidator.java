package com.epam.hostel.service.validator.impl;


import com.epam.hostel.model.user.User;
import com.epam.hostel.service.validator.IUserValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class implements {@code IUserValidator} interface and realize
 * its methods by validating {@code User} objects.
 *
 * @author Vershal
 * @version 1.0
 */

public class UserValidator implements IUserValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String LETTER_VALIDATION = "^[a-zA-Zа-яА-Я]{3,25}$";
    private static final String LETTERS_NUMBERS_VALIDATION = "^[a-zA-Z0-9\\d]{5,25}$";
    private static final String LOGIN_VALIDATION = "[\\wa-zA-Zа-яА-Я_0-9]{5,25}";

    private UserValidator() {
    }

    private static class SingletonHolder {
        private static final UserValidator INSTANCE = new UserValidator();
    }

    public static UserValidator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Method use regular expressions to check user data.
     * If {@code String} name and surname contains some thing other then
     * letters of English or Russian alphabet with the length rise than
     * 25 and lower than 3 character method returns {@code false}.
     * Also method check login and password(they should not contain
     * prohibited characters). If login length rise than
     * 25 and lower than 5 character method returns {@code false}.
     * If password will not contain number or password length rise than
     * 25 and lower than 5 character method returns {@code false}.
     *
     * @param user checked bean
     * @return boolean
     */
    @Override
    public boolean validate(User user) {
        return lettersValidation(user.getName()) &&
                lettersValidation(user.getSurname()) &&
                loginValidation(user.getLogin()) &&
                lettersNumbersValidation(user.getPassword());
    }

    /**
     * Method use regular expressions to check user login
     * and password. If they contains prohibited characters
     * or password will not contain number
     * method will return {@code false}.
     *
     * @param user checked bean
     * @return boolean
     */
    @Override
    public boolean validateLogPass(User user) {
        return loginValidation(user.getLogin()) &&
                lettersNumbersValidation(user.getPassword());
    }

    /**
     * Method use regular expressions to check user name, surname
     * and login. If they contains prohibited characters
     * method will return {@code false}.
     *
     * @param user checked bean
     * @return boolean
     */
    @Override
    public boolean validateNameSurnameLogin(User user) {
        return lettersValidation(user.getName()) &&
                lettersValidation(user.getSurname()) &&
                loginValidation(user.getLogin());
    }

    private boolean lettersNumbersValidation(String string) {
        pattern = Pattern.compile(LETTERS_NUMBERS_VALIDATION);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private boolean lettersValidation(String string) {
        pattern = Pattern.compile(LETTER_VALIDATION);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private boolean loginValidation(String login) {
        pattern = Pattern.compile(LOGIN_VALIDATION);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }
}
