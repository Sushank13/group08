package com.dal.cs.backend.Event.ServiceLayer;
import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Event.DataLayer.EventDataLayer;
import com.dal.cs.backend.Event.DataLayer.IEventDataLayer;
import com.dal.cs.backend.Event.EventObject.Event;

import java.sql.SQLException;
import java.util.List;

public class EventServiceLayer implements  IEventServiceLayer{
    IEventDataLayer iEventDataLayer;

    public EventServiceLayer() {
        iEventDataLayer = new EventDataLayer();
    }
    @Override
    public List<Event> getAllEvents()
    {
        try
        {
            List<Event> listOfAllEvents=iEventDataLayer.getAllEvents();
            return listOfAllEvents;
        }
        catch(SQLException e)
        {

        }
        return null;
    }

}
