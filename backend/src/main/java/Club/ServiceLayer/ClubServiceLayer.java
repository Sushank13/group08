package Club.ServiceLayer;

import Club.ClassObject.Club;
import Club.DataLayer.IClubDataLayer;
import Club.DataLayer.IClubSecondDataLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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
        String message="Your request for new club creation has been submitted to the Admin with request id:"+ requestId;
        return message;

    }
    private String generateRequestId()
    {
        try
        {
            final int one=1;
            String latestRequestId = iClubSecondDataLayer.getLatestRequestId();
            List<String> splitLatestRequestId = List.of(latestRequestId.split("_"));
            String newRequestNumber=splitLatestRequestId.get(1)+one;
            String newRequestId=splitLatestRequestId.get(0).concat("_").concat(newRequestNumber);
            return newRequestId;
        }
        catch (SQLException e)
        {
            e.getMessage();
        }
        return null;
    }
    private String generateClubId()
    {
        return null;
    }
}
