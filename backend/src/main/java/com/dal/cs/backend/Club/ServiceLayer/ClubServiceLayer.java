package com.dal.cs.backend.Club.ServiceLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubSecondDataLayer;
import com.dal.cs.backend.Club.Enum.RequestStatus;
import com.dal.cs.backend.Club.Enum.RequestType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ClubServiceLayer implements  IClubServiceLayer
{
    private static final Logger logger= LogManager.getLogger(ClubServiceLayer.class);

    IClubDataLayer iClubDataLayer;
    IClubSecondDataLayer iClubSecondDataLayer;

    public ClubServiceLayer() {
        iClubDataLayer = new ClubDataLayer();
        iClubSecondDataLayer = new ClubDataLayer();
    }

    /**
     * This method generates and sets the club id of a new club. It also generates a new request id(ticket)
     * for the new club
     * @param club represents the real world club object that has all the information of the club
     * @return message that request generated successfully or not
     */
    @Override
    public String createNewClubRequest(Club club)
    {
        logger.info("Entered ServiceLayer: createNewClubRequest() entered ");
        logger.info("Calling generateRequestId()");
        String requestId=generateRequestId();
        logger.info("Calling generateClubId()");
        String clubId=generateClubId();
        club.setClubID(clubId);
        String requestType= String.valueOf(RequestType.NEW_REQUEST);
        String requestStatus=String.valueOf(RequestStatus.PENDING);
        try
        {
            logger.info("Calling createNewClubRequest() of DataLayer");
            boolean createNewClubRequestStatus = iClubDataLayer.createNewClubRequest(requestId, club, requestType, requestStatus);
            if (createNewClubRequestStatus)
            {
                String message = "Your request for new club creation has been submitted to the Admin with request id: " + requestId;
                logger.info("new club request created successfully");
                logger.info("Exiting ServiceLayer");
                return message;
            }
        }
        catch(SQLException e)
        {
            logger.error(e.getMessage());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        String errorMessage = "There was a problem submitting your request. Please raise a new request.";
        logger.info("Exiting ServiceLayer");
        return errorMessage;
    }

    /**
     * This method generates a new incremented request id when a new request for club creation is submitted.
     * @return the incremented request id
     */
    private String generateRequestId()
    {
        logger.info("inside generateRequestId() in ClubServiceLayer ");
        try
        {
            final int one=1;
            String latestRequestId = iClubSecondDataLayer.getLatestRequestId();
            if(latestRequestId != null)
            {
                List<String> splitLatestRequestId = List.of(latestRequestId.split("_"));
                int requestNumber= Integer.parseInt(splitLatestRequestId.get(1));
                int newRequestNumber=requestNumber+one;
                String newRequestId=splitLatestRequestId.get(0).concat("_").concat(String.valueOf(newRequestNumber));
                return newRequestId;
            }
            String firstRequestId = "REQ_1";
            return firstRequestId;
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        return "";
    }

    /**
     * This method generates a new incremented club id when a new request for club creation is submitted.
     * @return the incremented club id
     */
    private String generateClubId()
    {
        logger.info("inside generateClubId() in ClubServiceLayer ");
        try
        {
            final int one=1;
            String latestClubId = iClubSecondDataLayer.getLatestClubId();
            if(latestClubId != null)
            {
                List<String> splitLatestClubId = List.of(latestClubId.split("_"));
                int clubNUmber=Integer.parseInt(splitLatestClubId.get(1));
                int newClubNumber=clubNUmber+one;
                String newClubId=splitLatestClubId.get(0).concat("_").concat(String.valueOf(newClubNumber));
                return newClubId;
            }
            String firstClubId = "CLB_1";
            return firstClubId;
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        return "";
    }

    /**
     * Retrieves all club categories by invoking the corresponding data layer function.
     *
     * @return A list of maps containing category names and corresponding category IDs.
     */
    @Override
    public ArrayList<HashMap<String, String>>  getAllClubCategories() {
        try {
            logger.info("Service Layer Entered: Entered getAllClubCategories- Calling Data layer getAllClubCategories");
            ArrayList<HashMap<String, String>> allClubCategories = iClubDataLayer.getAllClubCategories();
            logger.info("Exiting Service Layer: Returning category collection to Controller");
            return allClubCategories;
        } catch (SQLException e) {
            logger.error("getAllClubCategories- SQL Exception occurred while getting response from Data layer"+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * This method fetches all the clubs
     * @return list of all clubs
     */
    @Override
    public List<Club> getAllClubs()
    {
        logger.info("Service Layer Entered: Entered getAllClubs()- Calling Data layer getAllClubs()");
        try
        {
            List<Club> listOfAllClubs=iClubDataLayer.getAllClubs();
            return listOfAllClubs;
        }
        catch(SQLException e)
        {
            logger.error("getAllClubs()- SQL exception occurred while getting response from Data Layer"+e.getMessage());
        }
        logger.info("ServiceLayer: getAllClubs() returned null to Controller");
        return null;
    }

    /**
     * Inserts the updated club details into the request table by invoking the corresponding data layer function.
     *
     * @param club The club object containing the new details.
     * @return The request ID if the data layer operation is successful, else an error message.
     */
    @Override
    public String updateClubDetails(Club club)
    {
        logger.info("Service Layer Entered: Entered updateClubDetails- Calling Data layer insertUpdatedClubDetails");
        String errorMessage = null;
        String requestId=generateRequestId();
        String requestType= String.valueOf(RequestType.UPDATE_REQUEST);
        String requestStatus=String.valueOf(RequestStatus.PENDING);
        try
        {
            boolean resultStatus = iClubDataLayer.insertUpdatedClubDetails(requestId, club, requestType, requestStatus);
            if (resultStatus) {
                logger.info("Exiting Service Layer: Returning requestId to Controller");
                return requestId;
            }
            else {
                errorMessage = "Unable to insert updated club detail values.";
            }
        }
        catch(SQLException e)
        {
            errorMessage = e.getMessage();
            logger.error("Exception occured in 'updateClubDetails': "+errorMessage);
        }
        catch (Exception e)
        {
            errorMessage = e.getMessage();
            logger.error("Exception occured in 'updateClubDetails': "+errorMessage);
        }
        logger.info("Exiting Service Layer: Returning error message to Controller");
        return errorMessage;
    }

    @Override
    public boolean deleteClub(String clubID) {
        boolean resultStatus;
        String errorMessage = null;
        try {
            resultStatus = iClubDataLayer.deleteClub(clubID);
            return resultStatus;
        }
        catch (SQLException e) {
            resultStatus = false;
            errorMessage = e.getMessage();
        }
        catch (Exception e)
        {
            errorMessage = e.getMessage();
        }
        return false;
    }
}