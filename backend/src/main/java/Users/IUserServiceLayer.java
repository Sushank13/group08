package Users;

import java.time.LocalDate;
import java.util.Map;

public interface IUserServiceLayer
{
    public boolean signUp(String email, String password, String firstName, String secondName, UserType userType, String program, String term, String mobile, LocalDate dob);
    public String joinClubRequest(String email, int clubId,String joiningReason,String requestStatus);
    public Map<String,UserJoinRequest> getAllUserJoinRequests();
    public String updateUserJoinRequest(String status);
}
