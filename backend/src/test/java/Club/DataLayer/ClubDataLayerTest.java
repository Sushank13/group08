package Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Category;
import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.ClassObject.ClubUpdateRequest;
import com.dal.cs.backend.Club.ClassObject.JoinClubRequest;
import com.dal.cs.backend.Club.Enum.RequestStatus;
import com.dal.cs.backend.Club.Enum.RequestType;
import com.dal.cs.backend.member.Enum.MemberType;
import com.dal.cs.backend.member.MemberObject.Member;
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
    public void getAllClubCategoriesTest() {
        List<Category> categories = new ArrayList<>();
        for (int i=0; i< 10; i++){
            categories.add(createCategory(true));
        }
        try {
            ArrayList<HashMap<String, String>> allCategories = iClubDataLayer.getAllClubCategories();
            for (Category category: categories
                 ) {
                boolean found = false;
                for (HashMap<String, String> receivedCategory: allCategories
                     ) {
                    if (receivedCategory.get("categoryID").equals(category.getCategoryID())) {
                        Assertions.assertTrue(receivedCategory.get("categoryName").equals(category.getCategoryName()));
                        found = true;
                        break;
                    }
                }
                Assertions.assertTrue(found);
            }
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
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

    @Test
    public void createNewClubRequestTest() {
        Member member = createMember(true, MemberType.member);
        Category category = createCategory(true);
        Club club = createClub(false, member.getEmailId(), category);
        ClubUpdateRequest newClubRequest = createNewClubRequest(false, club);
        //Clean up
        addToStack(ClubUpdateRequest.class, newClubRequest.getRequestID());

        try {
            Assertions.assertTrue(iClubDataLayer.createNewClubRequest(newClubRequest.getRequestID(), club, RequestType.NEW_REQUEST.name(), RequestStatus.PENDING.name()));
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
    }

    @Test
    public void deleteNewClubRequestTest() {
        Member member = createMember(true, MemberType.member);
        Category category = createCategory(true);
        Club club = createClub(true, member.getEmailId(), category);
        ClubUpdateRequest newClubRequest = createNewClubRequest(true, club);

        try {
            Assertions.assertTrue(iClubDataLayer.deleteClubRequest(newClubRequest.getRequestID()));
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }

        //Remove from cleanup stack
        popCleanUpStack();
    }

    @Test
    public void updateClubRequestStatusToApprovedTest() {
        Member member = createMember(true, MemberType.member);
        Category category = createCategory(true);
        Club club = createClub(false, member.getEmailId(), category);
        ClubUpdateRequest newClubRequest = createNewClubRequest(true, club);
        //Clean up
        addToStack(ClubUpdateRequest.class, newClubRequest.getRequestID());
        try {
            Assertions.assertTrue(iClubDataLayer.getClubRequest(newClubRequest.getRequestID()).getRequestStatus().equals(RequestStatus.PENDING));
            Assertions.assertTrue(iClubDataLayer.updateClubRequestStatusToApproved(newClubRequest.getRequestID()));
            Assertions.assertTrue(iClubDataLayer.getClubRequest(newClubRequest.getRequestID()).getRequestStatus().equals(RequestStatus.APPROVED));
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
    }

    @Test
    public void updateClubRequestStatusToRejectedTest() {
        Member member = createMember(true, MemberType.member);
        Category category = createCategory(true);
        Club club = createClub(false, member.getEmailId(), category);
        ClubUpdateRequest newClubRequest = createNewClubRequest(true, club);
        //Clean up
        addToStack(ClubUpdateRequest.class, newClubRequest.getRequestID());
        try {
            Assertions.assertTrue(iClubDataLayer.getClubRequest(newClubRequest.getRequestID()).getRequestStatus().equals(RequestStatus.PENDING));
            Assertions.assertTrue(iClubDataLayer.updateClubRequestStatusToRejected(newClubRequest.getRequestID()));
            Assertions.assertTrue(iClubDataLayer.getClubRequest(newClubRequest.getRequestID()).getRequestStatus().equals(RequestStatus.REJECTED));
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }
    }

    @Test
    public void createClubTest() {
        Member president = createMember(true, MemberType.president);
        Category category = createCategory(true);
        Club club = createClub(false, president.getEmailId(), category);
        //Clean up
        addToStack(Club.class, club.getClubID());
        try {
            Assertions.assertTrue(iClubDataLayer.createClub(club));
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }


    }

    @Test
    public void deleteClubTest() {
        Member president = createMember(true, MemberType.president);
        Category category = createCategory(true);
        Club club = createClub(true, president.getEmailId(), category);

        try {
            Assertions.assertTrue(iClubDataLayer.deleteClub(club.getClubID()));
        } catch (SQLException e) {
            fail("Test failed: Exception occurred- " + e.getMessage());
        }

        //Skip club clean up
        popCleanUpStack();
    }

    @Test
    public void getClubsByNameTest() {
        Member president = createMember(true, MemberType.president);
        Category category = createCategory(true);
        Club randomClub = createClub(true, president.getEmailId(), category);
        try {
            List<Club> clubs = iClubDataLayer.getClubsByName(randomClub.getClubName());
            Assertions.assertTrue(clubs.size() != 0);
            for (Club club: clubs
                 ) {
                Assertions.assertTrue(club.getClubName().equals(randomClub.getClubName()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getClubsByCategoryTest() {
        Member president = createMember(true, MemberType.president);
        Category category = createCategory(true);
        Club randomClub = createClub(true, president.getEmailId(), category);
        try {
            List<Club> clubs = iClubDataLayer.getClubsByCategory(randomClub.getCategoryID());
            Assertions.assertTrue(clubs.size() != 0);
            for (Club club: clubs
            ) {
                Assertions.assertTrue(club.getCategoryID().equals(randomClub.getCategoryID()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void insertJoinClubRequestTest() {
        Member member = createMember(true, MemberType.member);
        Member president = createMember(true, MemberType.president);
        Category category = createCategory(true);
        Club club = createClub(true, president.getEmailId(), category);
        JoinClubRequest joinClubRequest = createNewJoinClubRequest(false, member.getEmailId(), club.getClubID());
        //Clean up
        addToStack(JoinClubRequest.class, joinClubRequest.getRequestID());
        try {
            Assertions.assertTrue(iClubDataLayer.insertJoinClubRequest(joinClubRequest));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateJoinClubRequestStatusToApproved() {
        Member member = createMember(true, MemberType.member);
        Member president = createMember(true, MemberType.president);
        Category category = createCategory(true);
        Club club = createClub(true, president.getEmailId(), category);
        JoinClubRequest joinClubRequest = createNewJoinClubRequest(true, member.getEmailId(), club.getClubID());

        try {
            Assertions.assertTrue(iClubDataLayer.getJoinClubRequest(joinClubRequest.getRequestID()).getRequestStatus().equals(RequestStatus.PENDING));
            Assertions.assertTrue(iClubDataLayer.updateJoinClubRequestStatusToApproved(joinClubRequest.getRequestID()));
            Assertions.assertTrue(iClubDataLayer.getJoinClubRequest(joinClubRequest.getRequestID()).getRequestStatus().equals(RequestStatus.APPROVED));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteJoinClubRequestTest() {
        Member member = createMember(true, MemberType.member);
        Member president = createMember(true, MemberType.president);
        Category category = createCategory(true);
        Club club = createClub(true, president.getEmailId(), category);
        JoinClubRequest joinClubRequest = createNewJoinClubRequest(true, member.getEmailId(), club.getClubID());

        try {
            Assertions.assertTrue(iClubDataLayer.deleteJoinClubRequest(joinClubRequest.getRequestID()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Remove from stack
        popCleanUpStack();
    }
}
