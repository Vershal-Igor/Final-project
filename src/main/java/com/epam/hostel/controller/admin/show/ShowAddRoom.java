package com.epam.hostel.controller.admin.show;

import com.epam.hostel.model.room.Room;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class provides view of adding new Room.
 */
@Controller
public class ShowAddRoom {

    private static final String ADD_ROOM_PAGE = "admin/addNewRoom";

    /**
     * Method set new Room page view.
     *
     * @param room
     */
    @RequestMapping(value = "/show-add-room")
    public ModelAndView ShowAddRoom(@ModelAttribute("roomJSP") Room room) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_ROOM_PAGE);
        modelAndView.addObject("roomJSP", room);

        return modelAndView;
    }
}
