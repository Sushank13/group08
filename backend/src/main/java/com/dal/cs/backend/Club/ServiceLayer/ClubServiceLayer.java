package com.dal.cs.backend.Club.ServiceLayer;

import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubSecondDataLayer;
import com.dal.cs.backend.Club.Enum.RequestStatus;
import com.dal.cs.backend.Club.Enum.RequestType;
import com.dal.cs.backend.Club.ClassObject.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ClubServiceLayer implements  IClubServiceLayer
{
    ClubDataLayer iClubDataLayer = new ClubDataLayer();
    IClubSecondDataLayer iClubSecondDataLayer;
    public String createNewClubRequest(Club club)
    {
        String requestId=generateRequestId();
        String clubId=generateClubId();
        club.setClubID(clubId);
        String requestType= String.valueOf(RequestType.NEW_REQUEST);
        String requestStatus=String.valueOf(RequestStatus.PENDING);
        try
        {
            boolean createNewClubRequestStatus = iClubDataLayer.createNewClubRequest(requestId, club, requestType, requestStatus);
            if (createNewClubRequestStatus) {
                String message = "Your request for new club creation has been submitted to the Admin with request id:" + requestId;
                return message;
            }
        }
        catch(SQLException e)
        {
            e.getMessage();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        String errorMessage = "There was a problem submitting your request";
        return errorMessage;
    }
    private String generateRequestId()
    {
        try
        {
            final int one=1;
            String latestRequestId = iClubSecondDataLayer.getLatestRequestId();
            List<String> splitLatestRequestId = List.of(latestRequestId.split("_"));
            int requestNumber= Integer.parseInt(splitLatestRequestId.get(1));
            int newRequestNumber=requestNumber+one;
            String newRequestId=splitLatestRequestId.get(0).concat("_").concat(String.valueOf(newRequestNumber));
            return newRequestId;
        }
        catch (SQLException e)
        {
            e.getMessage();
        }
        return "";
    }
    private String generateClubId()
    {
        try
        {
            final int one=1;
            String latestClubId = iClubSecondDataLayer.getLatestClubId();
            List<String> splitLatestClubId = List.of(latestClubId.split("_"));
            int clubNUmber=Integer.parseInt(splitLatestClubId.get(1));
            int newClubNumber=clubNUmber+one;
            String newClubId=splitLatestClubId.get(0).concat("_").concat(String.valueOf(newClubNumber));
            return newClubId;
        }
        catch (SQLException e)
        {
            e.getMessage();
        }
        return "";
    }

    @Override
    public ArrayList<HashMap<String, String>>  getAllClubCategories() {
        try {
            ArrayList<HashMap<String, String>> allClubCategories = iClubDataLayer.getAllClubCategories();
            return allClubCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
