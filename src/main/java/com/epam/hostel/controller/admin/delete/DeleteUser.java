package com.epam.hostel.controller.admin.delete;


import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class provides User delete.
 */
@Controller
public class DeleteUser {
    @Autowired
    private UserServiceImpl userService;

    /**
     * The method delete User by id.
     * Only admin can delete User.
     *
     * @param userId
     * @param redirectAttributes
     * @throws ServiceException
     */
    @RequestMapping(value = "/delete-user/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId, RedirectAttributes redirectAttributes) throws ServiceException {
        userService.delete(userId);
        userService.findAll();

        redirectAttributes.addFlashAttribute("message", "Delete successfully");
        return "redirect:/admin-users";
    }
}
