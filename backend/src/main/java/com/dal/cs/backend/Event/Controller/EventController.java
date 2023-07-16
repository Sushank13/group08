package com.dal.cs.backend.Event.Controller;
import com.dal.cs.backend.Club.Controller.ClubController;
import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.Event.ServiceLayer.EventServiceLayer;
import com.dal.cs.backend.Event.ServiceLayer.IEventServiceLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * This method returns a list of events that user has registered in
     * @param userEmailId is the email id of the user using which they signed up to DalClubs
     * @return list of events to the frontend
     */
    @RequestMapping(method = RequestMethod.GET, value="/getAllEvents/{userEmailId}")
    public List<Event> getEventsByUser(@PathVariable("userEmailId") String userEmailId)
    {
        logger.info("Controller Entered: Received request to get all events by userEmailID");
        logger.info("getAllEvents- Calling getEventsByUser() of ServiceLayer");
        List<Event> listOfAllEvents=iEventServiceLayer.getEventsByUser(userEmailId);
        logger.info("Exiting Controller: Returning list of events to Frontend via GET /getEventsByUser");
        return listOfAllEvents;
    }

    /**
     * This method register the events
     * @param eventID this will be the id of event
     * @param emailID  is the email id of the user using which they signed up to DalClubs
     * @return true if registered successfully
     */
    @RequestMapping(method = RequestMethod.POST, value = "/registerEvents/eventID/{eventID}/emailID/{emailID}")
    public boolean registerEvents(@PathVariable String eventID, @PathVariable String emailID)
    {
        logger.info("Controller Entered: Received request for register events");
        logger.info("registerEvents- Calling registerEvents() of ServiceLayer");
        boolean resultStatus= iEventServiceLayer.registerEvents(eventID, emailID);
        logger.info("Exiting Controller: Returning status for insert data via POST /registerEvents");
        return resultStatus;
    }

    /**
     *This method returns the details of event
     * @param nameOfEvent is the name of event user is searching detail for
     * @return list of event details user searched for
     */

    @RequestMapping(method = RequestMethod.GET, value = "/getEventDetails/{nameOfEvent}")
    public List<Event> getEventDetails(@PathVariable("nameOfEvent") String nameOfEvent)
    {
        logger.info("Controller Entered: Received request for get event details");
        logger.info("registerEvents- Calling getEventDetails() of ServiceLayer");
        List<Event> eventDetails=iEventServiceLayer.getEventDetails(nameOfEvent);
        logger.info("Exiting Controller: Returning status for get data via GET /getEventDetails");
        return eventDetails;
    }
}
