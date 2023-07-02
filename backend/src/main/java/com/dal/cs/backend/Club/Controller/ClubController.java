package com.dal.cs.backend.Club.Controller;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.ServiceLayer.IClubServiceLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class ClubController
{
   private static final Logger logger= LogManager.getLogger(ClubController.class);
   @Autowired
   IClubServiceLayer iClubServiceLayer;

   /**
    * This method accepts the club details  for a new club request.
    * @param club is the entity to which all the club details submitted by the user are mapped.
    * @return a message to the user with the request id in case request is submitted or an error message
    * if the request is not submitted
    */
   @RequestMapping(method = RequestMethod.POST, value="/registerClub")
   public String createClubRequest(@RequestBody Club club)
   {
      String message=iClubServiceLayer.createNewClubRequest(club);
      return message;
   }

   @RequestMapping(method = RequestMethod.GET, value="/getAllClubCategory")
   public ArrayList<HashMap<String, String>> getAllClubCategories() {
      logger.info("Received request for getting all club categories");
      logger.info("ClubControllerLayer-getAllClubCategories: Calling Service layer getAllClubCategories");
      ArrayList<HashMap<String, String>> allClubCategories = iClubServiceLayer.getAllClubCategories();
      logger.info("ClubControllerLayer-getAllClubCategories: Returning categories collection");
      return allClubCategories;
   }
}
