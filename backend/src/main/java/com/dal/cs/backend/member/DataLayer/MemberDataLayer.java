package com.dal.cs.backend.member.DataLayer;

import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import com.dal.cs.backend.member.MemberObject.Member;
import com.dal.cs.backend.member.ServiceLayer.MemberServiceLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MemberDataLayer implements IMemberDataLayer {
    private static final Logger logger= LogManager.getLogger(MemberServiceLayer.class);
    private IDatabaseConnection iDatabaseConnection;
    private Connection connection;

    public MemberDataLayer()
    {
        iDatabaseConnection=new DatabaseConnection();
        connection=iDatabaseConnection.getDatabaseConnection();
    }
    /**
     *This method will take the user input for user registration
     * new club requests
     * @param member member object
     * @return true if user registered successfully
     *
     */

    public boolean createNewMember(Member member)  {
        try {
            CallableStatement cs = connection.prepareCall("{call MemberSaveNewMember(?,?,?,?,?,?,?,?)}");
            cs.setString(1, member.getEmailId());
            cs.setString(2, member.getFirstName());
            cs.setString(3, member.getLastName());
            cs.setString(4, member.getMemberType().toString());
            cs.setString(5, member.getProgram());
            cs.setInt(6, member.getTerm());
            cs.setString(7, member.getMobile());
            cs.setDate(8, Date.valueOf(member.getDob()));
            cs.execute();

        } catch (SQLException e) {
            logger.info("Creation filed new member"+ e.getMessage());
            return false;
        }
        logger.info("Exiting createNewMember() in MemberDataLayer");
        return true;
    }

}
