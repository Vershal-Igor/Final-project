package com.epam.hostel.service.validator.impl;

import com.epam.hostel.model.user.User;
import com.epam.hostel.service.validator.IUserValidator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:dispatcher-servlet.xml", "classpath*:applicationContext.xml"})
public class UserValidatorTest {
    private static final Logger logger = Logger.getLogger(UserValidatorTest.class);

    private IUserValidator validator = UserValidator.getInstance();
    private static User user;

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login_example";
    private static final String PASSWORD = "password";

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setName(NAME);
        user.setSurname(SURNAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
    }

    @Test
    public void validateTest() throws Exception {
        User userFalse = new User();
        userFalse.setName("n_1");
        userFalse.setSurname("s");
        userFalse.setLogin("123login");
        userFalse.setPassword("12345678901234567890123456789");
        logger.info("\nUser validator test:\n" + "Valid input:\n" + user + "\nInvalid input:\n" + userFalse + "\n");

        Assert.assertTrue("Valid input", validator.validate(user));
        Assert.assertFalse("Invalid input", validator.validate(userFalse));
    }

    @Test
    public void validateLogPassTest() throws Exception {
        user.setLogin("valid_login");
        user.setPassword("__Wrong__pass__");
        logger.info("\nValidate LogPassTest:\n" + "\nInvalid input:\n" + user + "\n");
        Assert.assertFalse("Invalid input", validator.validate(user));
    }

    @Test
    public void validateNameSurnameLoginTest() throws Exception {
        user.setName("4rong_name");
        user.setSurname(" ");
        user.setLogin("valid_login");
        logger.info("\nValidate NameSurnameLoginTest:\n" + "\nInvalid input:\n" + user + "\n");
        Assert.assertFalse("Invalid input", validator.validate(user));
    }

}