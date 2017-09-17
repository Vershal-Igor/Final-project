package com.epam.hostel.controller.admin.status;


import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class provides status manipulations.
 */
@Controller
public class Status {
    @Autowired
    private OrderServiceImpl orderService;

    /**
     * Method confirms the User's order.
     *
     * @param orderId
     * @throws ServiceException
     */
    @RequestMapping("/set-confirm/{orderId}")
    public String setConfirm(@PathVariable("orderId") Long orderId) throws ServiceException {
        orderService.setConfirm(orderId);

        return "redirect:/admin-orders";
    }

    /**
     * Method rejects the User's order.
     *
     * @param orderId
     * @throws ServiceException
     */
    @RequestMapping("/set-reject/{orderId}")
    public String setReject(@PathVariable("orderId") Long orderId) throws ServiceException {
        orderService.setReject(orderId);

        return "redirect:/admin-orders";
    }

    /**
     * Method archives the User's order.
     *
     * @param orderId
     * @throws ServiceException
     */
    @RequestMapping("/set-archive/{orderId}")
    public String setArchive(@PathVariable("orderId") Long orderId) throws ServiceException {
        orderService.setArchive(orderId);

        return "redirect:/admin-orders";
    }
}
