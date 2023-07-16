package Event.ServiceLayer;

import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.Event.ServiceLayer.EventServiceLayer;
import com.dal.cs.backend.Event.ServiceLayer.IEventServiceLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EventServiceLayerTest {
    private IEventServiceLayer iEventServiceLayer;

    @BeforeEach
    public void beforeTestRun() {
        iEventServiceLayer = new EventServiceLayer() ;
    }

    @Test
    public void getAllEventsTest()
    {
        List<Event> listOfAllEvents=iEventServiceLayer.getAllEvents();
        System.out.println(listOfAllEvents);
    }
    @Test
    public  void getEventsByUserTest(){
        List<Event> listOfAllEvents=iEventServiceLayer.getEventsByUser("swit@dal.ca");
        System.out.println("List of Events by user id: ");
        for (int i = 0; i < listOfAllEvents.size(); i++) {
            System.out.println(listOfAllEvents.get(i).getEventID());
            System.out.println(listOfAllEvents.get(i).getClubID());
            System.out.println(listOfAllEvents.get(i).getOrganizerEmailID());
            System.out.println(listOfAllEvents.get(i).getEventName());
            System.out.println(listOfAllEvents.get(i).getDescription());
            System.out.println(listOfAllEvents.get(i).getVenue());
            System.out.println(listOfAllEvents.get(i).getImage());
            System.out.println(listOfAllEvents.get(i).getStartDate());
            System.out.println(listOfAllEvents.get(i).getEndDate());
            System.out.println(listOfAllEvents.get(i).getStartTime());
            System.out.println(listOfAllEvents.get(i).getEndTime());
            System.out.println(listOfAllEvents.get(i).getEventTopic());
        }
    }
    @Test
    public void getEventDetailsTest()
    {
        List<Event> eventDetails=iEventServiceLayer.getEventDetails("GALA");
        System.out.println(eventDetails);
        System.out.println("Event Details ");
        int i;
        for (i = 0; i < eventDetails.size(); i++) {
            Event event = eventDetails.get(i);
            System.out.println(event.getOrganizerEmailID());
            System.out.println(event.getEventName());
            System.out.println(event.getDescription());
            System.out.println(event.getVenue());
            System.out.println(event.getStartDate());
            System.out.println(event.getEndDate());
            System.out.println(event.getStartTime());
            System.out.println(event.getEndTime());
            System.out.println(event.getEventTopic());

        }
    }
}
