package Event.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Event.DataLayer.EventDatalayer;
import com.dal.cs.backend.Event.DataLayer.IEventDataLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class EventDataLayerTest {
    private IEventDataLayer iEventDataLayer;

    @BeforeEach
    public void beforeTestRun() {

        iEventDataLayer = new EventDatalayer();
    }

    @Test
    public void getAllClubsTest()
    {
        try
        {
            List<Club> listOfAllEvents = iEventDataLayer.getAllEvents();
            System.out.println("List of Clubs: \n"+listOfAllEvents);
        }
        catch (SQLException e)
        {
            fail("Test failed: Exception occurred- "+e.getMessage());
        }

    }
}
