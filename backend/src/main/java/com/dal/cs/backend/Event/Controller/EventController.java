package com.dal.cs.backend.Event.Controller;
import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.Event.ServiceLayer.EventServiceLayer;
import com.dal.cs.backend.Event.ServiceLayer.IEventServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
public class EventController
{
    @Autowired
    IEventServiceLayer iEventServiceLayer;

    @RequestMapping(method = RequestMethod.GET, value="/getAllEvents")
    public List<Event> getAllEvents()
    {

        List<Event> listOfAllEvents=iEventServiceLayer.getAllEvents();
        return listOfAllEvents;
    }
}
