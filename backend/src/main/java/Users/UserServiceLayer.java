package Users;

import java.time.LocalDate;
import java.util.Map;

public class UserServiceLayer implements  IUserServiceLayer
{

    public boolean signUp(String email, String password, String firstName, String secondName, UserType userType, String program, String term, String mobile, LocalDate dob)
    {
        return false;
    }
   public  String joinClubRequest(String email, int clubId,String joiningReason,String requestStatus)
    {
        return null;
    }
    public Map<String,UserJoinRequest> getAllUserJoinRequests()
    {
        return null;
    }
    public String updateUserJoinRequest(String status)
    {
        return null;
    }

}
