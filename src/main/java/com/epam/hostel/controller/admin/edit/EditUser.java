package com.epam.hostel.controller.admin.edit;

import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.dao.impl.UserDAOImpl;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class provides User edit.
 */
@Controller
public class EditUser {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserDAOImpl userDAO;

    /**
     * The method edit some User's attributes such as:
     * name, surname and login.
     *
     * @param userId
     * @throws ServiceException
     * @throws DAOException
     */
    @RequestMapping("/edit-user/{userId}")
    public String editUser(@PathVariable("userid") Long userId)
            throws ServiceException, DAOException {

        userService.editNameSurnameLogin(userId);

        return "redirect:/admin-users";
    }

}

