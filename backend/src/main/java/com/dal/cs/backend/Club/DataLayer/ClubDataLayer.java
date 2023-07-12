package com.dal.cs.backend.Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ClubDataLayer implements IClubDataLayer, IClubSecondDataLayer
{
    private static final Logger logger= LogManager.getLogger(ClubDataLayer.class);
    private IDatabaseConnection iDatabaseConnection;
    private Connection connection;
    private String callProcedure;
    private  CallableStatement callableStatement;
    public ClubDataLayer()
    {
        iDatabaseConnection=new DatabaseConnection();
        connection=iDatabaseConnection.getDatabaseConnection();
    }

    /**
     * This method calls a stored procedure to get the last row of the table that stores the
     * new club requests
     * @return the request id of the last row of the table that stores the new club requests
     * @throws SQLException
     */
    public String getLatestRequestId() throws SQLException
    {
        logger.info("Entered DataLayer: Entered getLatestRequestId()");
        logger.info("Executing stored procedure to get latest request id");
        callProcedure="{CALL getLatestRequestId()}";
        callableStatement=connection.prepareCall(callProcedure);
        boolean procedureCallStatus=callableStatement.execute();
        logger.info("Procedure to get latest request id called with status "+procedureCallStatus);
        if(procedureCallStatus)
        {
            ResultSet resultSet = callableStatement.getResultSet();
            boolean resultStatus = resultSet.next();
            if (resultStatus)
            {
                String latestRequestId = resultSet.getString("requestID");
                logger.info("Latest request id fetched is: "+latestRequestId);
                logger.info("Exiting Datalayer: returning latest request id to Service Layer");
                return latestRequestId;
            }
        }
        logger.info("Exiting DataLayer: returning request id as null to Service Layer");
        return  null;
    }

    /**
     * This method calls a stored procedure to get the last row of the table that stores the
     * new club requests
     * @return club id of the last row of the table that stores the new club requests
     * @throws SQLException
     */
    public String getLatestClubId() throws SQLException
    {
        logger.info("Entered DataLayer: Entered getLatestClubId()");
        logger.info("Executing stored procedure to get latest club id");
        callProcedure="{CALL getLatestClubId()}";
        callableStatement=connection.prepareCall(callProcedure);
        boolean procedureCallStatus=callableStatement.execute();
        logger.info("Procedure to get latest club id called with status "+procedureCallStatus);
        if(procedureCallStatus)
        {
            ResultSet resultSet = callableStatement.getResultSet();
            boolean resultStatus = resultSet.next();
            if (resultStatus)
            {
                String latestClubId = resultSet.getString("ClubID");
                logger.info("Latest club id fetched is: "+latestClubId);
                logger.info("Exiting Datalayer: returning latest club id to Service Layer");
                return latestClubId;
            }

        }
        logger.info("Exiting DataLayer: returning club id as null to Service Layer");
        return null;
    }

    /**
     *
     * @param requestId passed as an argument from the service layer
     * @param club object that has all club details passed from the service layer
     * @param requestType parameter passed from the service layer. It will be NEW_REQUEST in this case.
     * @param requestStatus parameter passed from the service layer. It will be PENDING initially.
     * @return true if the request is created else an exception will be thrown.
     * @throws SQLException
     */
    public boolean createNewClubRequest(String requestId, Club club, String requestType, String requestStatus)throws SQLException
    {
        logger.info("Entered DataLayer: Entered createNewClubRequest()");
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
        callableStatement.setString(11,club.getClubImage());
        callableStatement.setString(12,club.getRules());
        callableStatement.setString(13,requestType);
        callableStatement.setString(14,requestStatus);
        logger.info("Executing stored procedure to create a new record for new club request");
        boolean procedureCallStatus=callableStatement.execute();
        logger.info("Procedure to create a new club request called with status "+procedureCallStatus);
        if(procedureCallStatus)
        {
            logger.info("Exiting DataLayer: returning true to Service Layer");
            return true;
        }
        logger.info("Exiting DataLayer: returning false to Service Layer");
        return false;

    }

    /**
     * Retrieves all club categories from the category table by calling the stored procedure.
     * @return A list of maps containing category names and corresponding category IDs.
     * @throws SQLException If an error occurs while executing the stored procedure.
     */
    @Override
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

    /**
     * This method fetches the records of all the clubs from the database table
     * @return list of clubs fetched
     * @throws SQLException
     */
    @Override
    public List<Club> getAllClubs() throws SQLException
    {
        logger.info("Entered DataLayer: Entered getAllClubs()");
        callProcedure="{CALL getAllClubs()}";
        callableStatement=connection.prepareCall(callProcedure);
        boolean procedureCallStatus=callableStatement.execute();
        logger.info("Stored procedure for getAllClubs() executed with status "+procedureCallStatus);
        ResultSet resultSet=callableStatement.getResultSet();
        List<Club> listOfAllClubs=new ArrayList<>();
        if(procedureCallStatus)
        {
            while(resultSet.next())
            {
                Club club=new Club();
                club.setClubID(resultSet.getString(1));
                club.setClubName(resultSet.getString(2));
                club.setDescription(resultSet.getString(3));
                club.setPresidentEmailID(resultSet.getString(4));
                club.setFacebookLink(resultSet.getString(5));
                club.setInstagramLink(resultSet.getString(6));
                club.setCategoryID(resultSet.getString(7));
                club.setLocation(resultSet.getString(8));
                club.setMeetingTime(resultSet.getString(9));
                club.setClubImage(resultSet.getString(10));
                club.setRules(resultSet.getString(11));
                listOfAllClubs.add(club);
            }
            logger.info("getAllClubs(): list of all clubs created successfully");
            logger.info("Exiting DataLayer: returning list of all clubs to Service Layer");
            return listOfAllClubs;
        }
        else
        {
            logger.error("Problem with procedure call or database connection");
            return null;
        }

    }
}
