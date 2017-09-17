package com.epam.hostel.controller.client;


import com.epam.hostel.filter.AuthenticationFilter;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.metadata.IIOMetadataNode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.hostel.filter.AuthenticationFilter.AUTH_USER_ID;

/**
 * This class provides client page.
 */
@Controller
public class ClientPage {
    @Autowired
    private OrderServiceImpl orderService;

    private static final String CLIENT_PAGE = "client/client";

    /**
     * The method shows all the orders
     * of a particular user by his id.
     *
     * @param session
     * @return ModelAndView
     * @throws ServiceException
     */
    @RequestMapping(value = "/client")
    public ModelAndView client(HttpSession session) throws ServiceException {
        Long userId = (Long) session.getAttribute(AUTH_USER_ID);
        ModelAndView modelAndView = new ModelAndView(CLIENT_PAGE);
        List<Order> order = orderService.findUserOrder(userId);
        modelAndView.addObject("historyOrders", order);
        return modelAndView;
    }


    @ModelAttribute("Order")
    public Order initOrder() {
        return new Order();
    }

}
