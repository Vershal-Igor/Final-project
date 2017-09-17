package com.epam.hostel.controller.admin.show;

import com.epam.hostel.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class provides view of adding new User.
 */
@Controller
public class ShowAddUser {
    private static final String ADD_USER_PAGE = "admin/addNewUser";

    /**
     * Method set new User page view.
     *
     * @param user
     */
    @RequestMapping(value = "/show-add-user")
    public ModelAndView ShowAddUser(@ModelAttribute("userJSP") User user) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_USER_PAGE);
        modelAndView.addObject("userJSP", user);

        return modelAndView;
    }
}
