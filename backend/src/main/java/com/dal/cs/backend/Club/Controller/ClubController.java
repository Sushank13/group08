package com.dal.cs.backend.Club.Controller;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.ServiceLayer.IClubServiceLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
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
    @RequestMapping(method = RequestMethod.POST,value="/registerClub")
    public String createClubRequest(@RequestBody Club club)
    {
        logger.info("Controller Entered: Received a new request for club creation");
        logger.info(" createClubRequest- calling createClubRequest() of ServiceLayer");
        String message=iClubServiceLayer.createNewClubRequest(club);
        logger.info("Exiting Controller: returning message if create club request generated or not");
        return message;
    }

    /**
     * Retrieves all club categories.
     *
     * @return A list of maps containing category names and corresponding category IDs.
     */
    @RequestMapping(method = RequestMethod.GET, value="/getAllClubCategory")
    public ArrayList<HashMap<String, String>> getAllClubCategories() {
        logger.info("Controller Entered: Received request for getting all club categories.");
        logger.info("getAllClubCategories- Calling Service layer getAllClubCategories");
        ArrayList<HashMap<String, String>> allClubCategories = iClubServiceLayer.getAllClubCategories();
        logger.info("Exiting Controller: Returning categories collection to Frontend via GET /getAllClubCategory");
        return allClubCategories;
    }

    /**
     * This receives request to retrieve a list of all clubs in DalClubs
     * @return list of clubs
     */
    @RequestMapping(method = RequestMethod.GET, value="/getAllClubs")
    public List<Club> getAllClubs()
    {
        logger.info("Controller Entered: Received request to get all clubs.");
        logger.info("getAllClubs- Calling getAllClubs() of ServiceLayer");
        List<Club> listOfAllClubs=iClubServiceLayer.getAllClubs();
        logger.info("Exiting Controller: Returning list of clubs to Frontend via GET /getAllClubs");
        return listOfAllClubs;
    }

    /**
     * Updates the details of a existing club.
     * @param club The club object containing the new details.
     * @return A string response result, upon success returns newly generated request ID and upon failure returns error message.
     */
    @RequestMapping(method = RequestMethod.POST, value="/updateClubDetails")
    public String updateClubDetails(@RequestBody Club club) {
        logger.info("Controller Entered: Received request for updating club details.");
        logger.info("updateClubDetails- Calling Service layer updateClubDetails");
        String responseResult = iClubServiceLayer.updateClubDetails(club);
        logger.info("Exiting Controller: Returning service layer response result to Frontend via POST /updateClubDetails");
        return responseResult;
    }


    /** * This method receives the request to approve the club update or new club request
     * @param reqId is the request id of the club update or new club request
     * @return a message
     */
    @RequestMapping(method = RequestMethod.PUT,value="/approveClubRequest/{reqId}")
     public String approveClubRequest(@PathVariable("reqId") String reqId )
    {
        logger.info("Controller Entered: Received request to approve the new club or update club request.");
        logger.info("approveClubRequest()- Calling approveClubRequest() of ServiceLayer");
        boolean approveRequestStatus=iClubServiceLayer.approveClubRequest(reqId);
        if(approveRequestStatus)
        {
            String message=" Club request with Request ID: "+reqId+ " has been successfully approved";
            logger.info("Exiting Controller: returning club request approval message");
            return  message;
        }
        else
        {
            String message="Your request with Request ID: "+reqId+ " could not be approved";
            logger.info("Exiting Controller: returning club request approval message");
            return  message;
        }
    }
    
    /** * This method receives the request to reject the club update or new club request
     * @param reqId is the request id of the club update or new club request
     * @return a message
     */
    @RequestMapping(method = RequestMethod.PUT,value="/rejectClubRequest/{reqId}")
    public String rejectClubRequest(@PathVariable("reqId") String reqId )
    {
        logger.info("Controller Entered: Received request to reject the new club or update club request.");
        logger.info("rejectClubRequest()- Calling rejectClubRequest() of ServiceLayer");
        boolean rejectRequestStatus = iClubServiceLayer.rejectClubRequest(reqId);
        if (rejectRequestStatus)
        {
            String message = "Club request with Request ID: " + reqId + " has been successfully rejected";
            logger.info("Exiting Controller: returning club request rejection message");
            return message;
        }
        else
        {
            String message = "Club request with Request ID: " + reqId + " could not be rejected";
            logger.info("Exiting Controller: returning club request rejection message");
            return message;
        }
    }


    /**
     * Deletes the club details from the Club table. It also deletes all the Event details corresponding to this club to delete.
     * @param clubID The clubID value for the club record to delete
     * @return A boolean response result, which returns true if record deleted successfully, else returns false
     */
    @RequestMapping(method = RequestMethod.POST, value="/deleteClub")
    public boolean deleteClub(String clubID) {
        logger.info("Controller Entered: Received request for deleting the club based on its ID column.");
        logger.info("deleteClub- Calling Service layer deleteClub");
        boolean responseResult = iClubServiceLayer.deleteClub(clubID);
        logger.info("Exiting Controller: Returning service layer response result to Frontend via POST /deleteClub");
        return responseResult;
    }

}