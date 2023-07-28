package Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Category;
import com.dal.cs.backend.Club.ClassObject.Club;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import testUtils.BaseTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClubDataLayerTest extends BaseTest {


    public ClubDataLayerTest() {
        super();
    }

    @AfterAll
    public void cleanUp() {
        cleanUpTest();
    }

    @Test
    void getLatestRequestIdTest() {
        try {
            String result = iClubSecondDataLayer.getLatestRequestId();
            System.out.println("result = \n" + result);
        } catch (Exception e) {
            fail("Exception occured: " + e.getMessage());
        }
    }

    @Test
    void getLatestClubIdTest() {
        try {
            String result = iClubSecondDataLayer.getLatestClubId();
            System.out.println("result = \n" + result);
        } catch (Exception e) {
            fail("Exception occured: " + e.getMessage());
        }
    }

    @Test
    void getAllClubCategoriesTest() {
        try {
            ArrayList<HashMap<String, String>> result = iClubDataLayer.getAllClubCategories();
            System.out.println("result = \n" + result);
        } catch (Exception e) {
            fail("Test failed: Exception occured- " + e.getMessage());
        }
    }

    @Test
    void updateClubDetailsTest() {
//        try {
//            // Request ID
//            String requestId = "REQ_00_"+(int)Math.floor(Math.random()*500);
//            // New Club object with updated details
//            Club club = new Club();
//            club.setClubID("CLB_1");
//            club.setClubName("Dal & Kings Bike Society");
//            club.setDescription("Enthusiastic club organising biking trips.");
//            // request type
//            String requestType = String.valueOf(RequestType.UPDATE_REQUEST);
//            // request status
//            String requestStatus = String.valueOf(RequestStatus.PENDING);
//            // calling data layer function
//            boolean result = iClubDataLayer.insertUpdatedClubDetails(requestId,club,requestType,requestStatus);
//            System.out.println("result = " + result);
//            assertTrue(result);
//        }
//        catch (Exception e) {
//            fail("Test failed: Exception occurred- "+e.getMessage());
//        }
    }

    @Test
    public void getAllClubsTest() {
        try {
            List<Club> listOfAllClubs = iClubDataLayer.getAllClubs();
            System.out.println("List of Clubs: \n" + listOfAllClubs);
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
    }

    @Test
    public void deleteClubTest() {
//        try {
//            boolean result = iClubDataLayer.deleteClub("CLB_3");
//            System.out.println("result = " + result);
//        } catch (SQLException e) {
//            fail("Test failed: Exception occurred- "+e.getMessage());
//        }
    }

    @Test
    public void getClubDetailsFromClubRequestTest() {
        try {
            String reqId = "REQ_1";
            Club club = iClubDataLayer.getClubDetailsFromClubRequest(reqId);
            System.out.println(club.getClubID() + "," + club.getClubName());
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
    }

    @Test
    public void getLatestJoinClubRequestIdTest() {
        try {
            String latestRequestId = iClubSecondDataLayer.getLatestJoinClubRequestId();
            System.out.println(latestRequestId);
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
    }

    @Test
    public void getAllJoinClubRequestsTest() {
        //TODO: Add club random generator for club creation
//        try {
//            Assertions.assertTrue(iClubDataLayer.getAllJoinClubRequests("CLB_2", "user@dal.ca").size() > 0);
//        } catch (SQLException e) {
//            fail("Test failed: Exception occurred- " + e.getMessage());
//        }
    }

    @Test
    public void getAllClubRequestsTest() {
        //TODO: Add club random generator for club creation
//        try {
//            Assertions.assertTrue(iClubDataLayer.getAllClubRequests(RequestType.NEW_REQUEST, RequestStatus.APPROVED).size() > 0);
//        } catch (SQLException e) {
//            fail("Test failed: Exception occurred- " + e.getMessage());
//        }
    }

    @Test
    public void createClubCategoryTest() {
        Category category = createCategory(false);
        try {
            Assertions.assertTrue(iClubDataLayer.createClubCategory(category));
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }

        //Clean up
        addToStack(Category.class, category.getCategoryID());
    }

    @Test
    public void deleteClubCategoryTest() {
        Category category = createCategory(true);
        try {
            Assertions.assertTrue(iClubDataLayer.deleteClubCategory(category.getCategoryID()));
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }

        //Avoid clean up as deleted
        popCleanUpStack();
    }
}
