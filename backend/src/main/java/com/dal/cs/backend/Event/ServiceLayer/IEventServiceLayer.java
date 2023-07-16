package com.dal.cs.backend.Event.ServiceLayer;

import com.dal.cs.backend.Event.EventObject.Event;

import java.util.List;

public interface IEventServiceLayer {
    public List<Event> getAllEvents();
    public List<Event> getEventsByUser(String userEmailId);

    boolean registerEvents(String eventID, String emailID);
}
