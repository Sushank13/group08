package com.dal.cs.backend.Event.Controller;
import com.dal.cs.backend.Club.Controller.ClubController;
import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.Event.ServiceLayer.EventServiceLayer;
import com.dal.cs.backend.Event.ServiceLayer.IEventServiceLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger= LogManager.getLogger(ClubController.class);
    @Autowired
    IEventServiceLayer iEventServiceLayer;
    /**
     * This receives request to retrieve a list of all events in DalClubs
     * @return list of events
     */
    @RequestMapping(method = RequestMethod.GET, value="/getAllEvents")
    public List<Event> getAllEvents()
    {
        logger.info("Controller Entered: Received request to get all events.");
        logger.info("getAllEvents- Calling getAllEvents() of ServiceLayer");
        List<Event> listOfAllEvents=iEventServiceLayer.getAllEvents();
        logger.info("Exiting Controller: Returning list of events to Frontend via GET /getAllEvents");
        return listOfAllEvents;
    }
}
