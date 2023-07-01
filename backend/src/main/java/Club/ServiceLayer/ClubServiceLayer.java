package Club.ServiceLayer;

import Club.ClassObject.Club;
import Club.DataLayer.IClubDataLayer;
import Club.DataLayer.IClubSecondDataLayer;
import Club.Enum.RequestStatus;
import Club.Enum.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClubServiceLayer implements  IClubServiceLayer
{
    @Autowired
    IClubDataLayer iClubDataLayer;
    @Autowired
    IClubSecondDataLayer iClubSecondDataLayer;
    public String createNewClubRequest(Club club)
    {
        String requestId=generateRequestId();
        String clubId=generateClubId();
        club.setClubID(clubId);
        String requestType= String.valueOf(RequestType.NEW_REQUEST);
        String requestStatus=String.valueOf(RequestStatus.PENDING);
        try
        {
            boolean createNewClubRequestStatus = iClubDataLayer.createNewClubRequest(requestId, club, requestType, requestStatus);
            if (createNewClubRequestStatus) {
                String message = "Your request for new club creation has been submitted to the Admin with request id:" + requestId;
                return message;
            }
        }
        catch(SQLException e)
        {
            e.getMessage();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        String errorMessage = "There was a problem submitting your request. Please raise a new request.";
        return errorMessage;
    }

    /**
     * This method generates a new incremented request id when a new request for club creation is submitted.
     * @return the incremented request id
     */
    private String generateRequestId()
    {
        try
        {
            final int one=1;
            String latestRequestId = iClubSecondDataLayer.getLatestRequestId();
            if(!latestRequestId.equals(null))
            {
                List<String> splitLatestRequestId = List.of(latestRequestId.split("_"));
                int requestNumber= Integer.parseInt(splitLatestRequestId.get(1));
                int newRequestNumber=requestNumber+one;
                String newRequestId=splitLatestRequestId.get(0).concat("_").concat(String.valueOf(newRequestNumber));
                return newRequestId;
            }
            String firstRequestId = "REQ_1";
            return firstRequestId;
        }
        catch (SQLException e)
        {
            e.getMessage();
        }
        return "";
    }

    /**
     * This method generates a new incremented club id when a new request for club creation is submitted.
     * @return the incremented club id
     */
    private String generateClubId()
    {
        try
        {
            final int one=1;
            String latestClubId = iClubSecondDataLayer.getLatestClubId();
            if(!latestClubId.equals(null))
            {
                List<String> splitLatestClubId = List.of(latestClubId.split("_"));
                int clubNUmber=Integer.parseInt(splitLatestClubId.get(1));
                int newClubNumber=clubNUmber+one;
                String newClubId=splitLatestClubId.get(0).concat("_").concat(String.valueOf(newClubNumber));
                return newClubId;
            }
            String firstClubId = "CLUB_1";
            return firstClubId;
        }
        catch (SQLException e)
        {
            e.getMessage();
        }
        return "";
    }
}
