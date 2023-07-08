package com.dal.cs.backend.Club.ServiceLayer;

import com.dal.cs.backend.Club.ClassObject.Club;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IClubServiceLayer
{
  public String createNewClubRequest(Club club);

  public ArrayList<HashMap<String, String>> getAllClubCategories();
  public List<Club> getAllClubs();

}
