package com.dal.cs.backend.Event.Controller;
import com.dal.cs.backend.Club.Controller.ClubController;
import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.Event.ServiceLayer.IEventServiceLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
public class EventController {
    private static final Logger logger = LogManager.getLogger(ClubController.class);
    @Autowired
    IEventServiceLayer iEventServiceLayer;

    /**
     * This receives request to retrieve a list of all events in DalClubs
     *
     * @return list of events
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAllEvents")
    public List<Event> getAllEvents() {
        logger.info("Controller Entered: Received request to get all events.");
        logger.info("getAllEvents- Calling getAllEvents() of ServiceLayer");
        List<Event> listOfAllEvents = iEventServiceLayer.getAllEvents();
        logger.info("Exiting Controller: Returning list of events to Frontend via GET /getAllEvents");
        return listOfAllEvents;
    }

    /**
     * This method accepts the event details for creating a new event.
     * @param event It is the entity which has all the event information entered by the user.
     * @return result response about the success of creating event. true if event created successfully, else false.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/createEvent")
    public Map<String, Boolean> createEvent(@RequestBody Event event) {
        logger.info("Controller Entered: Received request for creating a new event");
        logger.info("createEvent- Calling Service layer createEvent");
        Map<String, Boolean> resultResponse = new HashMap<>();
        boolean result = iEventServiceLayer.createEvent(event);
        resultResponse.put("status", result);
        logger.info("Exiting Controller: Returning response status to Frontend via POST /createEvent");
        return resultResponse;
    }
}