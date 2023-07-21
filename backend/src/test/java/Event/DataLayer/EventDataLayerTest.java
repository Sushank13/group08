package Event.DataLayer;

import com.dal.cs.backend.Event.DataLayer.EventDataLayer;
import com.dal.cs.backend.Event.DataLayer.IEventDataLayer;
import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class EventDataLayerTest {
    private IEventDataLayer iEventDataLayer;

    @BeforeEach
    public void beforeTestRun() {
        IDatabaseConnection iDatabaseConnection = DatabaseConnection.getInstance();
        iEventDataLayer = EventDataLayer.getInstance(iDatabaseConnection);
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
    public void createEventTest() {
//        try {
//            Event demoEvent = new Event();
//            demoEvent.setEventID("EVNT_00_1");
//            demoEvent.setClubID("CLB_2");
//            demoEvent.setOrganizerEmailID("swit@dal.ca");
//            demoEvent.setEventName("sample event name");
//            demoEvent.setDescription("sample event description");
//            demoEvent.setVenue("sample event venue");
//            demoEvent.setImage("sample_image.jpg");
//            demoEvent.setStartDate("2023-01-01");
//            demoEvent.setEndDate("2023-01-03");
//            demoEvent.setStartTime("11:00:00");
//            demoEvent.setEndTime("12:00:00");
//            demoEvent.setEventTopic("sample topic");
//            boolean result = iEventDataLayer.createEvent(demoEvent);
//            System.out.println("result = " + result);
//        } catch (SQLException e) {
//            fail("Test failed: Exception occurred- " + e.getMessage());
//        }
    }

    @Test
    public void getLatestEventIdTest() {
        String result = iEventDataLayer.getLatestEventId();
        System.out.println("result = "+result);
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

    @Test
    public void getEventDetails() {
        try {
            List<Event> eventDetails = iEventDataLayer.getEventDetails("GALA");
            System.out.println("Event Details: \n" + eventDetails);
            int i;
            for (i = 0; i < eventDetails.size(); i++) {
                Event event = eventDetails.get(i);
                System.out.println(event.getEventName());
                System.out.println(event.getEventTopic());
                System.out.println(event.getDescription());
                System.out.println(event.getStartDate());
                System.out.println(event.getEndDate());
                System.out.println(event.getStartTime());
                System.out.println(event.getEndTime());
                System.out.println(event.getVenue());
                System.out.println(event.getOrganizerEmailID());
            }
        }
        catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
    }

    @Test
    public void updateEventDetailsTest () {
        Event mockEvent = new Event();
        mockEvent.setEventID("EVNT_3");
        mockEvent.setClubID("CLB_3");
        mockEvent.setEventName("Dalhousie outdoor society Spring AGM");
        mockEvent.setEventTopic("Outdoor recreational activity");
        try {
            boolean eventStatus = iEventDataLayer.updateEventDetails(mockEvent);
            System.out.println("eventStatus = " + eventStatus);
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }

    }

    @Test
    public void deleteEventTest() {
//        try {
//            String eventID = "EVNT_00_1";
//            boolean result = iEventDataLayer.deleteEvent(eventID);
//            System.out.println("result = " + result);
//        } catch (SQLException e) {
//            fail("Test failed: Exception occurred- " + e.getMessage());
//        }
    }
}
