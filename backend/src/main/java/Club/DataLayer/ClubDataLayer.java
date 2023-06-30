package Club.DataLayer;

import Club.Enum.RequestStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubDataLayer implements IClubDataLayer, IClubSecondDataLayer
{
   private Connection connection=null;
   private ResultSet resultSet;
   private PreparedStatement preparedStatement;
    public String getLatestRequestId() throws SQLException

    {
        preparedStatement=connection.prepareStatement("select requestID from NewAndUpdateClubRequest " +
                "where requestStatus=? DESC limit 1");
        String requestStatus= String.valueOf(RequestStatus.APPROVED);
        preparedStatement.setString(1,requestStatus);
        preparedStatement.execute("use DB");
        resultSet=preparedStatement.executeQuery();
        resultSet.next();
        String latestRequestId=resultSet.getString("requestId");
        return latestRequestId;
    }
    public String getLatestClubId()
    {
        return null;
    }
}
