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
    @Override
    public boolean createEvent(Event event)
    {
        String errorMessage = null;
        try {
            logger.info("Service Layer Entered: Entered createEvent- Calling Data layer createEvent");
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
}
