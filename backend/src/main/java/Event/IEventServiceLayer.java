package Event;

import java.util.List;

public interface IEventServiceLayer
{
    public List<Event> getAllEvents();
    public  List<Event> getEventByUser(String email);
    public  List<Event> getEventDetails(String eventID);
    public String createEvent(Event event);
    public  String generateEventID();
    public boolean deleteEvent(String eventID);
    public boolean updateEventDetails(Event event);

}
