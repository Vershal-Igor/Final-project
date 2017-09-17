package com.epam.hostel.controller.common;


import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.filter.AuthenticationFilter;
import com.epam.hostel.model.user.Role;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class provides sign in User
 */
@Controller
public class SignIn {
    @Autowired
    private UserServiceImpl userService;

    /**
     * Method return redirect with authResult=true if sign in successful
     * and authResult=false if sign in unsuccessful.
     *
     * @param login
     * @param password
     * @param redir
     * @param session
     * @param redirectAttributes
     * @return String
     * @throws ServiceException
     * @throws DAOException
     */
    @RequestMapping(value = "/sign-in")
    public String signInSvc(@RequestParam("login") String login, @RequestParam("password") String password,
                            @RequestParam(value = "redir") String redir, HttpSession session,
                            RedirectAttributes redirectAttributes)
            throws ServiceException, DAOException {

        User authUser = authenticate(login, password);
        if (authUser == null) {
            redir += (redir.contains("?") ? "&" : "?") + "authResult=false";
        } else {
            redir += (redir.contains("?") ? "&" : "?") + "authResult=true";
            session.setAttribute(AuthenticationFilter.AUTH_USER_ID, authUser.getId());
        }
        redirectAttributes.addFlashAttribute("controller.message", "Operation performed successfully");
        return "redirect:" + redir;
    }

    /**
     * Method provides sign in User
     * by login and password.
     *
     * @param login
     * @param password
     * @return User
     * @throws ServiceException
     */
    private User authenticate(String login, String password) throws ServiceException {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        return userService.signIn(user);
    }

    /**
     * Method provides sign out User.
     *
     * @param session
     * @return String
     */
    @RequestMapping(value = "/sign-out")
    private String signOut(HttpSession session) {
        session.setAttribute(AuthenticationFilter.AUTH_USER_ID, null);
        return "redirect:/";
    }

    /**
     * Method provides return User in his account
     * from anywhere on the site.
     *
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/return")
    public String returnToAccount(HttpServletRequest request) throws ServiceException {
        User authUser = (User) request.getAttribute(AuthenticationFilter.AUTH_USER_REQ_ATTR);
        if (authUser.getRole() == Role.ADMIN) {
            return "redirect:/admin-users";
        }
        return "redirect:/client";
    }


}
