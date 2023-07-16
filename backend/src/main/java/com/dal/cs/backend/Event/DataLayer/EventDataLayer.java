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
                event.setOrganizerEmailID(resultSet.getString(1));
                event.setEventName(resultSet.getString(2));
                event.setDescription(resultSet.getString(3));
                event.setVenue(resultSet.getString(4));
                event.setStartDate(resultSet.getString(5));
                event.setEndDate(resultSet.getString(6));
                event.setStartTime(resultSet.getString(7));
                event.setEndTime(resultSet.getString(8));
                event.setEventTopic(resultSet.getString(9));
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