package member.repository;

import database.DatabaseConnection;
import database.IDatabaseConnection;
import member.model.Member;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class MemberRepository {

    private Connection connection;

    public MemberRepository() throws Exception {
        connection = new DatabaseConnection().getDatabaseConnection();

    }

    public boolean createNewMember(Member member){
        try
        {
            CallableStatement cs=connection.prepareCall("{call MemberSaveNewMember(?,?,?,?,?,?,?,?)}");
            cs.setInt(1,101);
            cs.setDouble(2,0.1);
            cs.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
}
