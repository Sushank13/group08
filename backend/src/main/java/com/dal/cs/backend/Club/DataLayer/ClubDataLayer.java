package com.dal.cs.backend.Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.database.DatabaseConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ClubDataLayer implements IClubDataLayer, IClubSecondDataLayer
{
    private static final Logger logger= LogManager.getLogger(ClubDataLayer.class);
    DatabaseConnection databaseConnection = new DatabaseConnection();
    private Connection connection=databaseConnection.getDatabaseConnection();
    private String callProcedure;
    private CallableStatement callableStatement;
    public String getLatestRequestId() throws SQLException
    {
        callProcedure="{CALL getLatestRequestId()}";
        callableStatement=connection.prepareCall(callProcedure);
        callableStatement.registerOutParameter(1,Types.VARCHAR);
        callableStatement.execute();
        String latestRequestId=callableStatement.getString(1);
        if(latestRequestId.equals(null))
        {
            String defaultRequestId = "REQ_0";
            return defaultRequestId;
        }
        return latestRequestId;
    }
    public String getLatestClubId() throws SQLException
    {
        callProcedure="{CALL getLatestClubId()}";
        callableStatement=connection.prepareCall(callProcedure);
        callableStatement.registerOutParameter(1,Types.VARCHAR);
        callableStatement.execute();
        String latestClubId=callableStatement.getString(1);
        if(latestClubId.equals(null))
        {
            String defaultClubId = "CLUB_0";
            return defaultClubId;
        }
        return latestClubId;
    }
    public boolean createNewClubRequest(String requestId, Club club, String requestType, String requestStatus)throws SQLException
    {
        callProcedure="{CALL insertIntoNewAndUpdateClubRequest(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        callableStatement=connection.prepareCall(callProcedure);
        callableStatement.setString(1,requestId);
        callableStatement.setString(2,club.getClubID());
        callableStatement.setString(3,club.getPresidentEmailID());
        callableStatement.setString(4,club.getCategoryID());
        callableStatement.setString(5,club.getClubName());
        callableStatement.setString(6,club.getDescription());
        callableStatement.setString(7,club.getFacebookLink());
        callableStatement.setString(8,club.getInstagramLink());
        callableStatement.setString(9,club.getLocation());
        callableStatement.setString(10,club.getMeetingTime());
        callableStatement.setString(111,club.getClubImage());
        callableStatement.setString(12,club.getRules());
        callableStatement.setString(13,requestType);
        callableStatement.setString(14,requestStatus);
        callableStatement.execute();
        return true;

    }
    public ArrayList<HashMap<String, String>> getAllClubCategories() throws SQLException {

        if (connection != null) {

            logger.info("Data Layer Entered: Entered getAllClubCategories()");
            callProcedure = "{CALL selectAllFromCategory()}";
            callableStatement = connection.prepareCall(callProcedure);
            boolean resultStatus = callableStatement.execute();
            logger.info("getAllClubCategories- Procedure execution call successful, resultStatus = " + resultStatus);
            ArrayList<HashMap<String, String>> allClubCategories = null;
            if (resultStatus) {
                ResultSet resultSet = callableStatement.getResultSet();
                allClubCategories = new ArrayList<>();

                while (resultSet.next()) {
                    HashMap<String, String> categoryMap = new HashMap<>();
                    categoryMap.put("categoryID", resultSet.getString("categoryID"));
                    categoryMap.put("categoryName", resultSet.getString("categoryName"));
                    allClubCategories.add(categoryMap);
                }
                logger.info("getAllClubCategories- Category collection created successfully");
            }
            logger.info("Exiting Data Layer: Returning category collection to Service Layer");

            return allClubCategories;
        }
        else {
            logger.error("Exception: Unable to establish connection to Database");
            return null;
        }
    }
}
