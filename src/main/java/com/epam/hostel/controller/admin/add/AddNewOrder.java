package com.epam.hostel.controller.admin.add;


import com.epam.hostel.filter.AuthenticationFilter;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.order.PayType;
import com.epam.hostel.model.user.Role;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.epam.hostel.filter.AuthenticationFilter.AUTH_USER_ID;

/**
 * This class provides add new Order.
 */
@Controller
public class AddNewOrder {
    @Autowired
    private OrderServiceImpl orderService;

    /**
     * Method add new Order using the received parameters such as:
     * arrivalDate, depatureDate, placesAmount, roomId, userId, orderStatus.
     *
     * @param rb2
     * @param session
     * @param request
     * @param redirectAttributes
     * @throws ServiceException
     */
    @RequestMapping(value = "/add-order")
    public String addOrder(@RequestParam("rb2") Integer rb2, HttpSession session,
                           HttpServletRequest request,
                           RedirectAttributes redirectAttributes) throws ServiceException {

        Order order = (Order) session.getAttribute("order");
        Long userId = (Long) session.getAttribute(AUTH_USER_ID);
        order.setUserId(userId);
        order.setPayType(PayType.valueOf(rb2));

        orderService.add(order);

        redirectAttributes.addFlashAttribute("order", order);
        redirectAttributes.addFlashAttribute("message", "Added successfully");

        User authUser = (User) request.getAttribute(AuthenticationFilter.AUTH_USER_REQ_ATTR);
        if (authUser.getRole() == Role.ADMIN) {
            return "redirect:/admin-orders";
        }
        return "redirect:/client";

    }
}
