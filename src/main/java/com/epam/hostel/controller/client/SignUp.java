package com.epam.hostel.controller.client;

import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * This class provides sing up operation.
 */
@Controller
public class SignUp {
    @Autowired
    private UserServiceImpl userService;

    private static final String PAYMENT_PAGE = "payment";

    /**
     * Method provides sign up User by attributes.
     *
     * @param name
     * @param surname
     * @param login
     * @param password
     * @param redir
     * @return String
     * @throws ServiceException
     */
    @RequestMapping(value = "/sign-up")
    public String signUp(@RequestParam("name") String name, @RequestParam("surname") String surname,
                         @RequestParam("login") String login, @RequestParam("password") String password,
                         @RequestParam(required = false, value = "redir") String redir) throws ServiceException {
        User signUpUser = null;
        try {
            signUpUser = new User();

            signUpUser.setName(name);
            signUpUser.setSurname(surname);
            signUpUser.setLogin(login);
            signUpUser.setPassword(password);
            userService.signUp(signUpUser);

        } catch (DuplicateKeyException e) {
            redir += (redir.contains("?") ? "&" : "?") + "authResult=false";
            return "redirect:" + redir;
        }

        redir += (redir.contains("?") ? "&" : "?") + "authResult=true";
        return "redirect:" + redir;

    }

}