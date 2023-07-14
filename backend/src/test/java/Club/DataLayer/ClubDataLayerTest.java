package Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubSecondDataLayer;
import com.dal.cs.backend.Club.Enum.RequestStatus;
import com.dal.cs.backend.Club.Enum.RequestType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ClubDataLayerTest {

    private IClubDataLayer iclubDataLayer;
    private IClubSecondDataLayer iclubSecondDataLayer;

    @BeforeEach
    public void beforeTestRun() {

        iclubDataLayer = new ClubDataLayer();
        iclubSecondDataLayer = new ClubDataLayer();
    }

    @Test
    void getLatestRequestIdTest() {
        try {
            String result = iclubSecondDataLayer.getLatestRequestId();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Exception occured: "+e.getMessage());
        }
    }
    @Test
    void getLatestClubIdTest() {
        try {
            String result = iclubSecondDataLayer.getLatestClubId();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Exception occured: "+e.getMessage());
        }
    }
    @Test
    void getAllClubCategoriesTest() {
        try {
            ArrayList<HashMap<String, String>> result = iclubDataLayer.getAllClubCategories();
            System.out.println("result = \n" + result);
        }
        catch (Exception e) {
            fail("Test failed: Exception occured- "+e.getMessage());
        }
    }
    @Test
    void updateClubDetailsTest() {
        try {
            // Request ID
            String requestId = "REQ_00_"+(int)Math.floor(Math.random()*500);
            // New Club object with updated details
            Club club = new Club();
            club.setClubID("CLB_1");
            club.setClubName("Dal & Kings Bike Society");
            club.setDescription("Enthusiastic club organising biking trips.");
            // request type
            String requestType = String.valueOf(RequestType.UPDATE_REQUEST);
            // request status
            String requestStatus = String.valueOf(RequestStatus.PENDING);
            // calling data layer function
            boolean result = iclubDataLayer.insertUpdatedClubDetails(requestId,club,requestType,requestStatus);
            System.out.println("result = " + result);
            assertTrue(result);
        }
        catch (Exception e) {
            fail("Test failed: Exception occurred- "+e.getMessage());
        }
    }
    @Test
    public void getAllClubsTest()
    {
        try
        {
            List<Club> listOfAllClubs = iclubDataLayer.getAllClubs();
            System.out.println("List of Clubs: \n"+listOfAllClubs);
        }
        catch (SQLException e)
        {
            fail("Test failed: Exception occurred- "+e.getMessage());
        }
    }
    @Test
    public void deleteClubTest() {
        try {
            boolean result = iclubDataLayer.deleteClub("CLB_3");
            System.out.println("result = " + result);
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- "+e.getMessage());
        }
    }
    @Test
    public void getClubDetailsFromClubRequestTest()
    {
        try
        {
            String reqId="REQ_1";
            Club club=iclubDataLayer.getClubDetailsFromClubRequest(reqId);
            System.out.println(club.getClubID()+","+club.getClubName());
        }
        catch(SQLException e)
        {
            fail("Test failed: Exception occurred- "+e.getMessage());
        }
    }
}
