package Club.DataLayer;

import Club.ClassObject.Club;

import java.sql.*;

public class ClubDataLayer implements IClubDataLayer, IClubSecondDataLayer
{
   private Connection connection=null;
   private String callProcedure;
   CallableStatement callableStatement;
    public String getLatestRequestId() throws SQLException
    {
        callProcedure="{CALL getLatestRequestId()}";
        callableStatement=connection.prepareCall(callProcedure);
        callableStatement.registerOutParameter(1,Types.VARCHAR);
        callableStatement.execute();
        String latestRequestId=callableStatement.getString(1);
        if(latestRequestId.equals(null))
        {
            String defaultRequestId = "REQ_0";
            return defaultRequestId;
        }
        return latestRequestId;
    }
    public String getLatestClubId() throws SQLException
    {
        callProcedure="{CALL getLatestClubId()}";
        callableStatement=connection.prepareCall(callProcedure);
        callableStatement.registerOutParameter(1,Types.VARCHAR);
        callableStatement.execute();
        String latestClubId=callableStatement.getString(1);
        if(latestClubId.equals(null))
        {
            String defaultClubId = "CLUB_0";
            return defaultClubId;
        }
        return latestClubId;
    }
    public boolean createNewClubRequest(String requestId, Club club, String requestType, String requestStatus)throws SQLException
    {
        callProcedure="{CALL insertIntoNewAndUpdateClubRequest(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        callableStatement=connection.prepareCall(callProcedure);
        callableStatement.setString(1,requestId);
        callableStatement.setString(2,club.getClubID());
        callableStatement.setString(3,club.getPresidentEmailID());
        callableStatement.setString(4,club.getCategoryID());
        callableStatement.setString(5,club.getClubName());
        callableStatement.setString(6,club.getDescription());
        callableStatement.setString(7,club.getFacebookLink());
        callableStatement.setString(8,club.getInstagramLink());
        callableStatement.setString(9,club.getLocation());
        callableStatement.setString(10,club.getMeetingTime());
        callableStatement.setString(111,club.getClubImage());
        callableStatement.setString(12,club.getRules());
        callableStatement.setString(13,requestType);
        callableStatement.setString(14,requestStatus);
        callableStatement.execute();
        return true;

    }
}
