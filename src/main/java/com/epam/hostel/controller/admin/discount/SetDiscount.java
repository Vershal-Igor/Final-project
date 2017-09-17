package com.epam.hostel.controller.admin.discount;

import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class provides set discount to User.
 */
@Controller
public class SetDiscount {
    @Autowired
    private OrderServiceImpl orderService;

    /**
     * The method set discount to User by User's id.
     *
     * @param orderId
     * @throws ServiceException
     */
    @RequestMapping("/set-discount/{orderId}")
    public String setDiscount(@PathVariable("orderId") Long orderId) throws ServiceException {
        orderService.setDiscount(orderId);

        return "redirect:/admin-orders";
    }
}
