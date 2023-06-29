package Users;

import java.time.LocalDate;
import java.util.Map;

public interface IUserDataLayer
{
   public  boolean saveLoginDetails(String email,String password); //to save in the Login table in the db
    public boolean saveUserDetails(String firstName, String secondName, UserType userType, String program, String term, String mobile, LocalDate dob);
   public  boolean saveJoinClubRequest(String email, int clubId,String joiningReason,String requestStatus);
    public Map<String,UserJoinRequest> getAllUserJoinRequests();
    public boolean updateUserJoinRequestStatus(String status);
}
