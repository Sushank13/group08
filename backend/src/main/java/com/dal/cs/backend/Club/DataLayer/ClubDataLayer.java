package com.dal.cs.backend.Club.DataLayer;

import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.ServiceLayer.ClubServiceLayer;
import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Component
public class ClubDataLayer implements IClubDataLayer, IClubSecondDataLayer
{
    private static final Logger logger= LogManager.getLogger(ClubServiceLayer.class);
    private IDatabaseConnection iDatabaseConnection;
    private Connection connection;
    private String callProcedure;
    private  CallableStatement callableStatement;
    public ClubDataLayer()
    {
        iDatabaseConnection = new DatabaseConnection();
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
        logger.info("inside getLatestRequestId() in ClubDataLayer");
        callProcedure="{CALL getLatestRequestId()}";
        callableStatement=connection.prepareCall(callProcedure);
        callableStatement.registerOutParameter(1,Types.VARCHAR);
        callableStatement.execute();
        String latestRequestId=callableStatement.getString(1);
        callableStatement.close();
        iDatabaseConnection.closeDatabaseConnection();
        logger.info("Exiting getLatestRequestId() in ClubDataLayer");
        return latestRequestId;
    }

    /**
     * This method calls a stored procedure to get the last row of the table that stores the
     * new club requests
     * @return club id of the last row of the table that stores the new club requests
     * @throws SQLException
     */
    public String getLatestClubId() throws SQLException
    {
        logger.info("inside getLatestClubId() in ClubDataLayer");
        callProcedure="{CALL getLatestClubId()}";
        callableStatement=connection.prepareCall(callProcedure);
        callableStatement.registerOutParameter(1,Types.VARCHAR);
        callableStatement.execute();
        String latestClubId=callableStatement.getString(1);
        callableStatement.close();
        iDatabaseConnection.closeDatabaseConnection();
        logger.info("Exiting getLatestClubId() in ClubDataLayer");
        return latestClubId;
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
        logger.info("inside createNewClubRequest() in ClubDataLayer");
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
        callableStatement.execute();
        callableStatement.close();
        iDatabaseConnection.closeDatabaseConnection();
        logger.info("Exiting createNewClubRequest() in ClubDataLayer");
        return true;
    }
}
