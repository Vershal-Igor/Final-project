package com.epam.hostel.controller.admin.delete;


import com.epam.hostel.service.exception.ServiceException;
import com.epam.hostel.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class provides Room delete.
 */
@Controller
public class DeleteRoom {
    @Autowired
    private RoomServiceImpl roomService;

    /**
     * The method delete Room by Room id.
     * Only admin can delete Room.
     *
     * @param roomId
     * @param redirectAttributes
     * @throws ServiceException
     */
    @RequestMapping("/delete-room/{roomId}")
    public String deleteRoom(@PathVariable("roomId") Long roomId, RedirectAttributes redirectAttributes) throws ServiceException {
        roomService.delete(roomId);
        roomService.findAll();

        redirectAttributes.addFlashAttribute("message", "Delete successfully");
        return "redirect:/admin-rooms";
    }

}
