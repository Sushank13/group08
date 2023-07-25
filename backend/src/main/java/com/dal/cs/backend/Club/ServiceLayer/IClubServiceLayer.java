package com.dal.cs.backend.Club.ServiceLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.ClassObject.JoinClubRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IClubServiceLayer
{
  public String createNewClubRequest(Club club);

  public ArrayList<HashMap<String, String>> getAllClubCategories();
  public List<Club> getAllClubs();
  public List<Club> getClubsByName(String name);
  public List<Club> getClubsByCategory(String category);
  public String updateClubDetails(Club club);
  public boolean approveClubRequest(String reqId);
  public boolean rejectClubRequest(String reqId);
  public boolean deleteClub(String clubID);
  public List<JoinClubRequest> getAllJoinClubRequests(String clubID, String presidentEmailID);
}
