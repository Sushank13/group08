package Event;

import java.util.List;

public class EventDataLayer implements  IEventDataLayer
{
    public List<Event> getAllEvents()
    {
        return null;
    }
    public  List<Event> getEventByUser(String email)
    {
        return  null;
    }
    public  List<Event> getEventDetails(String eventID)
    {
        return null;
    }
    public boolean createEvent(Event event)
    {
        return false;
    }
    public String getLatestEventID()
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
}
