package Event;

import java.util.List;

public class EventServiceLayer implements  IEventServiceLayer
{
    public List<Event> getAllEvents()
    {
        return null;
    }
    public  List<Event> getEventByUser(String email)
    {
        return null;
    }
    public  List<Event> getEventDetails(String eventID)
    {
        return null;
    }
    public String createEvent(Event event)
    {
        return null;
    }
    public boolean deleteEvent(String eventID)
    {
        return false;
    }
    public boolean updateEventDetails(Event event)
    {
        return false;
    }
    public  String generateEventID()
    {
        return null;
    }
}
