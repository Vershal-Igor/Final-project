package com.epam.hostel.controller.admin.add;

import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class provides add new User.
 */
@Controller
public class AddNewUser {
    @Autowired
    private UserServiceImpl userService;

    /**
     * Method add new User using the received parameters such as:
     * name, surname, login, password.
     *
     * @param name
     * @param surname
     * @param login
     * @param password
     * @param redirectAttributes
     * @throws ServiceException
     */
    @RequestMapping(value = "/add-user")
    public String addUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
                          @RequestParam("login") String login, @RequestParam("password") String password,
                          RedirectAttributes redirectAttributes) throws ServiceException {
        try {
            User user = new User();

            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            user.setPassword(password);

            userService.add(user);

            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("message", "Added successfully");
        } catch (DuplicateKeyException e) {
            redirectAttributes.addFlashAttribute("message", "Duplicate user");
            return "redirect:/add-user";
        }

        return "redirect:/admin-users";
    }
}
