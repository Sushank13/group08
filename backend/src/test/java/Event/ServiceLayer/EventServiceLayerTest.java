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
    public void createEventTest() {
        Event demoEvent = new Event();
        demoEvent.setEventID("EVNT_00_2");
        demoEvent.setClubID("CLB_2");
        demoEvent.setOrganizerEmailID("swit@dal.ca");
        demoEvent.setEventName("sample event name 2");
        demoEvent.setDescription("sample event description 2");
        demoEvent.setVenue("sample event venue 2");
        demoEvent.setImage("sample_image2.jpg");
        demoEvent.setStartDate("2023-01-01");
        demoEvent.setEndDate("2023-01-03");
        demoEvent.setStartTime("11:00:00");
        demoEvent.setEndTime("12:00:00");
        demoEvent.setEventTopic("sample topic");
        boolean result = iEventServiceLayer.createEvent(demoEvent);
        System.out.println("result = " + result);
    }
}
