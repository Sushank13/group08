package com.dal.cs.backend.Event.ServiceLayer;
import com.dal.cs.backend.Club.ServiceLayer.ClubServiceLayer;
import com.dal.cs.backend.Event.DataLayer.EventDataLayer;
import com.dal.cs.backend.Event.DataLayer.IEventDataLayer;
import com.dal.cs.backend.Event.EventObject.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EventServiceLayer implements  IEventServiceLayer{
    private static final Logger logger= LogManager.getLogger(ClubServiceLayer.class);
    IEventDataLayer iEventDataLayer;

    public EventServiceLayer() {
        iEventDataLayer = new EventDataLayer();
    }
    /**
     * This method fetches all the events
     * @return list of all events
     */
    @Override
    public List<Event> getAllEvents()
    {
        logger.info("Service Layer Entered: Entered getAllEvents()- Calling Data layer getAllEvents()");
        try
        {
            List<Event> listOfAllEvents=iEventDataLayer.getAllEvents();
            return listOfAllEvents;
        }
        catch(SQLException e)
        {
            logger.error("getAllEvents()- SQL exception occurred while getting response from Data Layer"+e.getMessage());
        }
        logger.info("ServiceLayer: getAllEvents() returned null to Controller");
        return null;
    }

    /**
     * This method generates a new event ID, for inserting a new event record into event table.
     * @param event is the real world event object which has all the information about a event.
     * @return boolean status result if the event was created successfully by inserting the record into the table, else false.
     */
    @Override
    public boolean createEvent(Event event) {
        logger.info("Service Layer Entered: Entered createEvent- Calling Data layer createEvent");
        String errorMessage = null;
        logger.info("createEvent- Calling generateEventId()");
        String EventId=generateEventId();
        event.setEventID(EventId);
        logger.info("createEvent- retrieved new latest eventID = "+EventId);
        try {
            logger.info("createEvent- Calling Data layer createEvent");
            boolean createEventStatus = iEventDataLayer.createEvent(event);
            if (createEventStatus) {
                logger.info("Exiting Service Layer: Returning boolean result status=true to Controller");
                return true;
            }
            else {
                errorMessage = "Unable to create event by inserting in database table.";
                logger.warn("Exiting Service Layer: Returning boolean result status=false to Controller");
                return false;
            }
        } catch (SQLException e) {
            errorMessage = "Unable to create event by inserting in database table. Error message: "+e.getMessage();
            logger.error("Exception occured in 'updateClubDetails': " + errorMessage);
            logger.info("Exiting Service Layer: Returning error message to Controller");
            return false;
        }
    }

    /**
     * This method retrieves the new incremented event id when a new event creation is to be executed.
     * @return the new latest eventid String
     */
    private String generateEventId() {
        logger.info("generateEventId- Entered generateEventId- Calling Data layer getLatestEventId");
        String newEventId = iEventDataLayer.getLatestEventId();
        if(newEventId != null)
        {
            logger.info("Exiting Service Layer: Returning latest eventID.");
            return newEventId;
        }
        String firstEventId = "EVNT_1";
        logger.info("Exiting Service Layer: Returning the first eventID, as no events present.");
        return firstEventId;
    }
}
