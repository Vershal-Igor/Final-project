package com.epam.hostel.controller.common;


import com.epam.hostel.filter.AuthenticationFilter;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.user.Role;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
/**
 * This class provides start page.
 */
@Controller

public class StartPage {

    private static final String START_PAGE = "startPage";

    /**
     * Method contains the attributes required
     * to display and work with the start page.
     *
     * @param user
     * @param authResult
     * @param request
     * @return ModelAndView
     * @throws ServiceException
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage(@ModelAttribute("userJSP") User user, 
    		@RequestParam(required=false, value="authResult") Boolean authResult,
    		HttpServletRequest request) throws ServiceException {
    	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(START_PAGE);
        if (authResult!= null && !authResult) {
    		modelAndView.addObject("noSuchUser", user);
    	} else if (authResult!= null && authResult) {
    		User authUser = (User)request.getAttribute(AuthenticationFilter.AUTH_USER_REQ_ATTR);
    		if (authUser.getRole() == Role.ADMIN) {
    			modelAndView.setViewName("redirect:/admin-users");
    		}else {
                modelAndView.setViewName("redirect:/client");
            }
    	}
        modelAndView.addObject("userJSP", new User());
        modelAndView.addObject("Order", new Order());
        return modelAndView;
    }

}




