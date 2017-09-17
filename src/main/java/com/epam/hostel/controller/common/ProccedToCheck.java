package com.epam.hostel.controller.common;

import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class provides proceed to check order
 */
@Controller
public class ProccedToCheck {

    public static Logger logger = Logger.getLogger(ProccedToCheck.class);
    private static final String PROCCED_PAGE = "payment";

    /**
     * After the User filled order data and selected
     * a suitable room, method sends User
     * to the page for continuing the order.
     *
     * @param rb
     * @param session
     * @return ModelAndView
     * @throws ServiceException
     */
    @RequestMapping(value = "/procced-to-check", method = RequestMethod.GET)
    public ModelAndView proccedToCheck(@RequestParam("rb") Long rb, HttpSession session) throws ServiceException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userJSP", new User());
        modelAndView.addObject("rb", rb);
        Order order = (Order) session.getAttribute("order");
        order.setRoomId(rb);
        session.setAttribute("order", order);

        logger.info("Order: " + order);
        modelAndView.setViewName(PROCCED_PAGE);
        return modelAndView;
    }
}
