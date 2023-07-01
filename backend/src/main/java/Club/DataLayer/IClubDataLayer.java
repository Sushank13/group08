package Club.DataLayer;

import Club.ClassObject.Club;

import java.sql.SQLException;

public interface IClubDataLayer
{
   public boolean createNewClubRequest(String requestId, Club club,String requestType,String requestStatus)throws SQLException;
}
