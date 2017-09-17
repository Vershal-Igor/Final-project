package com.epam.hostel.controller.admin.show;

import com.epam.hostel.model.order.Order;
import com.epam.hostel.model.room.Room;
import com.epam.hostel.model.user.User;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * This class provides list of free Rooms.
 */
@Controller
public class ShowFreeRooms {
    @Autowired
    private RoomServiceImpl roomService;
    private static final String START_PAGE = "startPage";

    /**
     * The method shows the free rooms
     * that satisfy the filled parameters such as:
     * arrivalDate, departureDate and placesAmount.
     *
     * @param start
     * @param end
     * @param place
     * @param order
     * @param session
     * @throws ServiceException
     * @throws ParseException
     */
    @RequestMapping(value = "/free-rooms", method = RequestMethod.POST)
    public ModelAndView ShowFreeRooms(@RequestParam("arrivalDate") String start,
                                      @RequestParam("departureDate") String end,
                                      @RequestParam("placesAmount") Integer place,
                                      @ModelAttribute("Order") Order order, BindingResult result, HttpSession session)
            throws ServiceException, ParseException {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        ModelAndView modelAndView = new ModelAndView();
        order.setArrivalDate(new java.sql.Date(formatter.parse(start).getTime()));
        order.setDepartureDate(new java.sql.Date(formatter.parse(end).getTime()));
        order.setPlacesAmount(place);
        session.setAttribute("order", order);
        List<Room> rooms = roomService.showFreeRooms(order);
        modelAndView.addObject("userJSP", new User());
        modelAndView.addObject("Rooms", rooms);
        modelAndView.setViewName(START_PAGE);

        return modelAndView;
    }

    @ModelAttribute("Order")
    public Order initOrder() {
        return new Order();
    }
}
