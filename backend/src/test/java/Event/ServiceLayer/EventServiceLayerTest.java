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
}
