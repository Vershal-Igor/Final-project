package com.epam.hostel.controller.admin.edit;

import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class provides Room price edit.
 */
@Controller
public class EditRoomPrice {
    @Autowired
    private RoomServiceImpl roomService;

    /**
     * The method edit Room price by Room id.
     *
     * @param roomId
     * @throws ServiceException
     */
    @RequestMapping("/edit-room/{roomId}")
    public String editRoom(@PathVariable("roomId") Long roomId) throws ServiceException {
        roomService.updatePrice(roomId);

        return "redirect:/admin-rooms";
    }
}

