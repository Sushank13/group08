package com.dal.cs.backend.Event.ServiceLayer;
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
    private static final Logger logger= LogManager.getLogger(EventServiceLayer.class);
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
     * This method returns a list of events that user has registered in
     * @param userEmailId  is the email id of the user using which they signed up to DalClubs
     * @return  list of events to the controller
     */
    @Override
    public List<Event> getEventsByUser(String userEmailId)
    {
        logger.info("Service Layer Entered: Entered getEventsByUser()- Calling Data layer getEventsByUser()");
        try
        {
            List<Event> listOfAllEvents=iEventDataLayer.getEventsByUser(userEmailId);
            return listOfAllEvents;
        }
        catch(SQLException e)
        {
            logger.error("getEventsByUser()- SQL exception occurred while getting response from Data Layer"+e.getMessage());
        }
        logger.info("ServiceLayer: getEventsByUser() returned null to Controller");
        return null;
    }

    @Override
    public boolean registerEvents(String eventID, String emailID){
        logger.info("Service Layer Entered: Entered registerEvents()- Calling Data layer registerEvents()");
        try
        {
            boolean resultStatus= iEventDataLayer.registerEvents(eventID,emailID);
            return resultStatus;
        }
        catch (SQLException e)
        {
            logger.error("getEventsByUser()- SQL exception occurred while getting response from Data Layer"+e.getMessage());
        }
        logger.info("ServiceLayer: registerEvents() returned false to Controller");
        return false;
    }

    @Override
    public List<Event> getEventDetails(String nameOfEvent) {
        try
        {
            List<Event> eventDetails=iEventDataLayer.getEventDetails(nameOfEvent);
            return eventDetails;
        }
        catch(SQLException e)
        {

        }
        return null;
    }


}
