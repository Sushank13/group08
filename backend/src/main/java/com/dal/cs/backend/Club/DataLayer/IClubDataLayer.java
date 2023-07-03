package com.dal.cs.backend.Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
public interface IClubDataLayer
{
   public boolean createNewClubRequest(String requestId, Club club, String requestType, String requestStatus)throws SQLException;
}
