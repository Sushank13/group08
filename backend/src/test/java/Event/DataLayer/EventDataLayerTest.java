package Event.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Event.DataLayer.EventDataLayer;
import com.dal.cs.backend.Event.DataLayer.IEventDataLayer;
import com.dal.cs.backend.Event.EventObject.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class EventDataLayerTest {
    private IEventDataLayer iEventDataLayer;

    @BeforeEach
    public void beforeTestRun() {

        iEventDataLayer = new EventDataLayer();
    }

    @Test
    public void getAllEventsTest() {
        try {
            List<Event> listOfAllEvents = iEventDataLayer.getAllEvents();
            System.out.println("List of Events: \n" + listOfAllEvents);
            int i;
            for (i = 0; i < listOfAllEvents.size(); i++) {
                Event event = listOfAllEvents.get(i);
                System.out.println(event.getOrganizerEmailID());
                System.out.println(event.getEventName());
                System.out.println(event.getDescription());
                System.out.println(event.getVenue());
                System.out.println(event.getImage());
                System.out.println(event.getStartDate());
                System.out.println(event.getEndDate());
                System.out.println(event.getStartTime());
                System.out.println(event.getEndTime());
                System.out.println(event.getEventTopic());

            }
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }

    }
    @Test
    public void getEventsByUser() {
        try {
            List<Event> listOfAllEvents = iEventDataLayer.getEventsByUser("swit@dal.ca");
            System.out.println("List of Events: \n" + listOfAllEvents);
            int i;
            for (i = 0; i < listOfAllEvents.size(); i++) {
                Event event = listOfAllEvents.get(i);
                System.out.println(event.getOrganizerEmailID());
                System.out.println(event.getEventName());
                System.out.println(event.getDescription());
                System.out.println(event.getVenue());
                System.out.println(event.getImage());
                System.out.println(event.getStartDate());
                System.out.println(event.getEndDate());
                System.out.println(event.getStartTime());
                System.out.println(event.getEndTime());
                System.out.println(event.getEventTopic());

            }
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
    }
}
