package com.dal.cs.backend.Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.ClassObject.JoinClubRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IClubDataLayer
{
   public boolean createNewClubRequest(String requestId, Club club, String requestType, String requestStatus) throws SQLException;
   public ArrayList<HashMap<String, String>> getAllClubCategories() throws SQLException;
   public List<Club> getAllClubs() throws SQLException;
   public List<Club> getClubsByName(String name) throws SQLException;
   public List<Club> getClubsByCategory(String category) throws SQLException;
   public boolean insertUpdatedClubDetails(String requestId, Club club, String requestType, String requestStatus) throws SQLException;
   public Club getClubDetailsFromClubRequest(String reqId) throws SQLException;
   public boolean createClub(Club club) throws SQLException;
   public boolean updateClubRequestStatusToApproved(String requestId) throws  SQLException;
   public boolean updateClubRequestStatusToRejected(String requestId) throws SQLException;
   public boolean deleteClub(String clubID) throws SQLException;
   public boolean insertJoinClubRequest(JoinClubRequest joinClubRequest) throws  SQLException;
   public List<JoinClubRequest> getAllJoinClubRequests(String clubID, String presidentEmailID) throws SQLException;
   public boolean updateJoinClubRequestStatusToApproved(String reqId) throws SQLException;
   public boolean deleteJoinClubRequest(String reqId) throws SQLException;
}
