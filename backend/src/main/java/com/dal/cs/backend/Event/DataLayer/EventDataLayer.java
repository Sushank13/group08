package com.dal.cs.backend.Event.DataLayer;

import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.Event.ObjectBuilder.EventBuilder;
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
                Event event = new EventBuilder().setOrganizerEmailID(resultSet.getString(1)).setEventName(resultSet.getString(2)).setDescription(resultSet.getString(3)).setVenue(resultSet.getString(4)).setStartDate(resultSet.getString(6)).setEndDate(resultSet.getString(7)).setStartTime(resultSet.getString(8)).setEndTime(resultSet.getString(9)).setEventTopic(resultSet.getString(10)).createEvent();
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

    /**
     * retrieve the latest event id by calling a stored procedure
     * @return event id of the latest event to add into table
     */
    @Override
    public String getLatestEventId() {
        try {
            logger.info("Entered DataLayer: Entered getLatestEventId)");
            callProcedure = "{CALL getLatestEventId()}";
            callableStatement = connection.prepareCall(callProcedure);
            boolean procedureCallStatus = callableStatement.execute();
            logger.info("getLatestEventId- Procedure call to get latest event id");
            if (procedureCallStatus) {
                ResultSet resultSet = callableStatement.getResultSet();
                boolean resultStatus = resultSet.next();
                if (resultStatus) {
                    String latestEventId = resultSet.getString("eventID");
                    logger.info("Latest event id fetched is: "+latestEventId);
                    logger.info("Exiting Datalayer: returning latest event id to Service Layer");
                    return latestEventId;
                }
            }
        } catch (SQLException e) {
            logger.info("Exiting DataLayer: returning event id as null to Service Layer");
            System.out.println(e.getMessage());
            return null;
        }
        logger.info("Exiting DataLayer: returning event id as null to Service Layer");
        return null;
    }

    /**
     * This method fetches list of events that user has registered in from the database
     * @param userEmailId is the email id of the user using which they signed up to DalClubs
     * @return list of events to the service layer
     * @throws SQLException
     */
    @Override
    public List<Event> getEventsByUser(String userEmailId) throws SQLException {
        logger.info("Entered DataLayer: Entered getEventsByUser()");
        callProcedure = "{CALL getEventsByUserEmailID(?)}";
        callableStatement = connection.prepareCall(callProcedure);
        callableStatement.setString(1, userEmailId);
        boolean procedureCallStatus = callableStatement.execute();
        logger.info("Stored procedure for getEventsByUser() executed with status "+procedureCallStatus);
        ResultSet resultSet = callableStatement.getResultSet();
        List<Event> listOfAllEvents = new ArrayList<>();
        if (procedureCallStatus) {
            while (resultSet.next()) {
                Event event = new EventBuilder().setOrganizerEmailID(resultSet.getString(1)).setEventName(resultSet.getString(2)).setDescription(resultSet.getString(3)).setVenue(resultSet.getString(4)).setStartDate(resultSet.getString(6)).setEndDate(resultSet.getString(7)).setStartTime(resultSet.getString(8)).setEndTime(resultSet.getString(9)).setEventTopic(resultSet.getString(10)).createEvent();
                listOfAllEvents.add(event);
            }
            logger.info("getEventsByUser(): list of all events created successfully");
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
     * This method register the events
     * @param eventID is the event id of  any event host by the club
     * @param emailID is the email id of the user using which they signed up to DalClubs
     * @return true if events successfully register
     * @throws SQLException
     */

    @Override
    public boolean registerEvents(String eventID, String emailID) throws SQLException {
        logger.info("Entered DataLayer: Entered registerEvents()");
        callProcedure = "{CALL registerEvents(?,?)}";
        callableStatement = connection.prepareCall(callProcedure);
        callableStatement.setString(1,eventID);
        callableStatement.setString(2,emailID);
        int  procedureCallStatus = callableStatement.executeUpdate();
        logger.info("Stored procedure for registerEvents() executed with status "+procedureCallStatus);
        if (procedureCallStatus > 0)
        {
            logger.info("registerEvents(): event register successfully");
            logger.info("Exiting DataLayer: returning boolean status Service Layer");
            return true;
        }
        else
        {
            logger.error("Problem with procedure call or database connection");
            return false;
        }

    }

    /**
     * This method returns the details of event
     * @param nameOfEvent it will take the name of event user searching for
     * @return all the details of the event user search for
     * @throws SQLException
     */
    @Override
    public List<Event> getEventDetails(String nameOfEvent) throws SQLException {
        logger.info("Entered DataLayer: Entered getEventDetails()");
        callProcedure = "{CALL getEventDetails(?)}";
        callableStatement = connection.prepareCall(callProcedure);
        callableStatement.setString(1, nameOfEvent);
        boolean procedureCallStatus = callableStatement.execute();
        logger.info("Stored procedure for getEventDetails() executed with status "+procedureCallStatus);
        ResultSet resultSet = callableStatement.getResultSet();
        List<Event> eventDetails = new ArrayList<>();
        if (procedureCallStatus) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setEventName(resultSet.getString(1));
                event.setEventTopic(resultSet.getString(2));
                event.setDescription(resultSet.getString(3));
                event.setStartDate(resultSet.getString(4));
                event.setEndDate(resultSet.getString(5));
                event.setStartTime(resultSet.getString(6));
                event.setEndTime(resultSet.getString(7));
                event.setVenue(resultSet.getString(8));
                event.setOrganizerEmailID(resultSet.getString(9));
                eventDetails.add(event);
            }
            logger.info("getEventDetails(): get the list of all events details successfully");
            logger.info("Exiting DataLayer: returning list of all events details to Service Layer");

            return eventDetails;
        }
        else
        {
            return null;
        }
    }


}