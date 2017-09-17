package com.epam.hostel.controller.admin.add;

import com.epam.hostel.model.room.Room;
import com.epam.hostel.service.exception.DuplicateEntityException;
import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

/**
 * This class provides add new Room.
 */
@Controller
public class AddNewRoom {
    @Autowired
    private RoomServiceImpl roomService;

    /**
     * Method add new Room using the received parameters such as:
     * roomNumber, roomPlaces, price.
     *
     * @param roomNumber
     * @param roomPlaces
     * @param price
     * @param redirectAttributes
     * @throws ServiceException
     */
    @RequestMapping(value = "/add-room")
    public String addRoom(@RequestParam("roomNumber") Byte roomNumber, @RequestParam("roomPlaces") Byte roomPlaces,
                          @RequestParam("price") BigDecimal price, RedirectAttributes redirectAttributes)
            throws ServiceException {
        Room room = null;
        try {
            room = new Room();

            room.setRoomNumber(roomNumber);
            room.setRoomPlaces(roomPlaces);
            room.setPrice(price);

            roomService.add(room);

            redirectAttributes.addFlashAttribute("room", room);
            redirectAttributes.addFlashAttribute("message", "Added successfully");
        } catch (DuplicateEntityException e) {
            redirectAttributes.addFlashAttribute("message", "Duplicate number");
            return "redirect:/show-add-room";
        }
        return "redirect:/admin-rooms";
    }
}
