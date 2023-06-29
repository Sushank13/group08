package Event;

import java.util.List;

public interface IEventDataLayer
{
    public List<Event> getAllEvents();
    public  List<Event> getEventByUser(String email);
    public  List<Event> getEventDetails(String eventID);
    public boolean createEvent(Event event);
    public String getLatestEventID();
    public boolean deleteEvent(String eventID);
    public boolean updateEventDetails(Event event);
}
