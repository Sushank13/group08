package Users;

import java.time.LocalDate;
import java.util.Map;

public class UserDataLayer implements IUserDataLayer
{
    public  boolean saveLoginDetails(String email,String password)
    {
        return false;
    }
    public boolean saveUserDetails(String firstName, String secondName, UserType userType, String program, String term, String mobile, LocalDate dob)
    {
        return false;
    }
    public  boolean saveJoinClubRequest(String email, int clubId,String joiningReason,String requestStatus)
    {
        return false;
    }
    public Map<String,UserJoinRequest> getAllUserJoinRequests()
    {
        return null;
    }
    public boolean updateUserJoinRequestStatus(String status)
    {
        return false;
    }
}
