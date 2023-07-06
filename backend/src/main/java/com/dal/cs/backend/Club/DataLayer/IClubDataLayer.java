package com.dal.cs.backend.Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IClubDataLayer
{
   public boolean createNewClubRequest(String requestId, Club club, String requestType, String requestStatus) throws SQLException;

   public ArrayList<HashMap<String, String>> getAllClubCategories() throws SQLException;
}
