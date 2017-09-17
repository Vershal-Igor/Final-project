package com.epam.hostel.controller.admin.delete;

import com.epam.hostel.filter.AuthenticationFilter;
import com.epam.hostel.model.user.Role;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class provides Order delete.
 */
@Controller
public class DeleteOrder {
    @Autowired
    private OrderServiceImpl orderService;

    /**
     * The method delete order and redirect to the User page.
     * Delete the order can both the admin and the clients in their accounts.
     *
     * @param orderId
     * @param redirectAttributes
     * @param session
     * @param request
     * @throws ServiceException
     */
    @RequestMapping("/delete-order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long orderId,
                              RedirectAttributes redirectAttributes,
                              HttpSession session, HttpServletRequest request) throws ServiceException {
        User authUser = (User) request.getAttribute(AuthenticationFilter.AUTH_USER_REQ_ATTR);

        orderService.delete(orderId);
        orderService.findAll();
        redirectAttributes.addFlashAttribute("message", "Delete successfully");
        if (authUser.getRole() == Role.ADMIN) {
            return "redirect:/admin-orders";
        }
        return "redirect:/client";
    }
}
