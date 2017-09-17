package com.epam.hostel.controller.admin.ban;


import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class provides ban manipulations.
 */
@Controller
public class Ban {
    @Autowired
    private UserServiceImpl userService;

    /**
     * Method set ban User by id.
     *
     * @param userId
     * @param redirectAttributes
     * @throws ServiceException
     */
    @RequestMapping("/set-ban/{userId}")
    public String setBan(@PathVariable("userId") Long userId, RedirectAttributes redirectAttributes)
            throws ServiceException {
        userService.setBan(userId);
        redirectAttributes.addFlashAttribute("message", "Successfully banned");
        return "redirect:/admin-users";
    }

    /**
     * Method set unban User by id.
     *
     * @param userId
     * @param redirectAttributes
     * @throws ServiceException
     */
    @RequestMapping("/set-unban/{userId}")
    public String setUnban(@PathVariable("userId") Long userId, RedirectAttributes redirectAttributes)
            throws ServiceException {
        userService.setUnban(userId);
        redirectAttributes.addFlashAttribute("message", "Successfully unbanned");
        return "redirect:/admin-users";
    }
}
