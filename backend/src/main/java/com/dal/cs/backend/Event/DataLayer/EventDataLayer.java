package com.dal.cs.backend.Event.DataLayer;

import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDataLayer implements IEventDataLayer
{
    private static final Logger logger= LogManager.getLogger(ClubDataLayer.class);
    private IDatabaseConnection iDatabaseConnection;
    private Connection connection;
    private String callProcedure;
    private CallableStatement callableStatement;

    public EventDataLayer()
    {
        iDatabaseConnection=new DatabaseConnection();
        connection=iDatabaseConnection.getDatabaseConnection();
    }

    /**
     * This method fetches the records of all the events from the database table
     * @return list of events fetched
     * @throws SQLException
     */
    @Override
    public List<Event> getAllEvents() throws SQLException
    {
        logger.info("Entered DataLayer: Entered getAllEvents()");
        callProcedure="{CALL getAllEvents()}";
        callableStatement=connection.prepareCall(callProcedure);
        boolean procedureCallStatus=callableStatement.execute();
        logger.info("Stored procedure for getAllEvents() executed with status "+procedureCallStatus);
        ResultSet resultSet=callableStatement.getResultSet();
        List<Event> listOfAllEvents=new ArrayList<>();
        if(procedureCallStatus)
        {
            while(resultSet.next())
            {
                Event event = new Event();
                event.setOrganizerEmailID(resultSet.getString(1));
                event.setEventName(resultSet.getString(2));
                event.setDescription(resultSet.getString(3));
                event.setVenue(resultSet.getString(4));
                event.setImage(resultSet.getString(5));
                event.setStartDate(resultSet.getString(6));
                event.setEndDate(resultSet.getString(7));
                event.setStartTime(resultSet.getString(8));
                event.setEndTime(resultSet.getString(9));
                event.setEventTopic(resultSet.getString(10));
                listOfAllEvents.add(event);
            }
            logger.info("getAllEvents(): list of all events created successfully");
            logger.info("Exiting DataLayer: returning list of all events to Service Layer");
            return listOfAllEvents;
        }
        else
        {
            logger.error("Problem with procedure call or database connection");
            return null;
        }
    }

    /**
     * This method calls a stored procedure that inserts a record for a new event into the events table
     * @param event This is event object that has all the event details
     * @return true if the event record is insert successfully, else return false
     * @throws SQLException
     */
    @Override
    public boolean createEvent(Event event) throws SQLException {
        if (connection != null) {
            logger.info("Entered DataLayer: Entered createEvent()");
            callProcedure="{CALL createEvent(?,?,?,?,?,?,?,?,?,?,?,?)}";
            callableStatement=connection.prepareCall(callProcedure);
            callableStatement.setString(1, event.getEventID());
            callableStatement.setString(2, event.getClubID());
            callableStatement.setString(3, event.getOrganizerEmailID());
            callableStatement.setString(4, event.getEventName());
            callableStatement.setString(5, event.getDescription());
            callableStatement.setString(6, event.getVenue());
            callableStatement.setString(7, event.getImage());
            callableStatement.setString(8, event.getStartDate());
            callableStatement.setString(9, event.getEndDate());
            callableStatement.setString(10, event.getStartTime());
            callableStatement.setString(11, event.getEndTime());
            callableStatement.setString(12, event.getEventTopic());
            int result = callableStatement.executeUpdate();
            boolean resultStatus = (result == 1);
            logger.info("createEvent- Procedure execution call successful, resultStatus = " + resultStatus);
            logger.info("Exiting Data Layer: Returning boolean result status to Service Layer");
            return resultStatus;
        }
        else {
            logger.error("Exception: Database Connection not established.");
            return false;
        }
    }

    @Override
    public String getLatestEventId() {
        try {
            callProcedure = "{CALL getLatestEventId()}";
            callableStatement = connection.prepareCall(callProcedure);
            boolean procedureCallStatus = callableStatement.execute();
            if (procedureCallStatus) {
                ResultSet resultSet = callableStatement.getResultSet();
                boolean resultStatus = resultSet.next();
                if (resultStatus) {
                    String latestEventId = resultSet.getString("eventID");
                    return latestEventId;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
