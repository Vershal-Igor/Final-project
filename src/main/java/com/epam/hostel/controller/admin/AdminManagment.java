package com.epam.hostel.controller.admin;

import com.epam.hostel.dao.exception.DAOException;
import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.room.Room;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.DuplicateEntityException;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.OrderServiceImpl;
import com.epam.hostel.service.impl.RoomServiceImpl;
import com.epam.hostel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * This class provides admin management.
 */
@Controller
public class AdminManagment {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private OrderServiceImpl orderService;

    private static final String ADMIN_ORDERS_PAGE = "admin/adminOrders";
    private static final String ADMIN_USERS_PAGE = "admin/adminUsers";
    private static final String ADMIN_ROOMS_PAGE = "admin/adminRooms";

    @RequestMapping(value = "/admin-orders")
    public ModelAndView listOfOrders() throws ServiceException {
        ModelAndView modelAndView = new ModelAndView(ADMIN_ORDERS_PAGE);
        putOrdersToModel(modelAndView);

        return modelAndView;
    }

    private ModelAndView putOrdersToModel(ModelAndView modelAndView) throws ServiceException {
        List<Order> orders = orderService.findAll();
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @RequestMapping(value = "/admin-orders", method = RequestMethod.POST)
    public ModelAndView editOrder(@RequestParam("orderId") Long oid,
                                  @RequestParam("discount") BigDecimal discount) throws ServiceException {

        ModelAndView modelAndView = new ModelAndView(ADMIN_ORDERS_PAGE);
        Order order = orderService.findById(oid);
        order.setDiscount(discount);
        // save order
        orderService.update(order);

        putOrdersToModel(modelAndView);
        return modelAndView;
    }


    @RequestMapping(value = "/admin-users")
    public ModelAndView listOfUsers() throws ServiceException, DAOException {
        ModelAndView modelAndView = new ModelAndView(ADMIN_USERS_PAGE);
        putUsersToModel(modelAndView);
        return modelAndView;
    }

    private ModelAndView putUsersToModel(ModelAndView modelAndView) throws ServiceException, DAOException {
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = "/admin-users", method = RequestMethod.POST)
    public ModelAndView editUser(@RequestParam("userId") long uid,
                                 @RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("login") String login) throws ServiceException, DAOException {

        ModelAndView modelAndView = new ModelAndView(ADMIN_USERS_PAGE);
        try {
            User user = userService.findById(uid);
            user.setName(name);
            user.setSurname(surname);
            user.setLogin(login);
            userService.update(user);
        } catch (DuplicateEntityException e) {
            modelAndView.addObject("error");
            putUsersToModel(modelAndView);
            return modelAndView;
        }
        putUsersToModel(modelAndView);
        return modelAndView;
    }


    @RequestMapping(value = "/admin-rooms")
    public ModelAndView listOfRooms() throws ServiceException {
        ModelAndView modelAndView = new ModelAndView(ADMIN_ROOMS_PAGE);
        putRoomsToModel(modelAndView);
        return modelAndView;
    }

    private ModelAndView putRoomsToModel(ModelAndView modelAndView) throws ServiceException {
        List<Room> rooms = roomService.findAll();
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @RequestMapping(value = "/admin-rooms", method = RequestMethod.POST)
    public ModelAndView editRoom(@RequestParam("roomId") long rid,
                                 @RequestParam("price") BigDecimal price) throws ServiceException, DAOException {

        ModelAndView modelAndView = new ModelAndView(ADMIN_ROOMS_PAGE);
        Room room = roomService.findById(rid);
        room.setPrice(price);
        // save room
        roomService.update(room);

        putRoomsToModel(modelAndView);
        return modelAndView;

    }
}
