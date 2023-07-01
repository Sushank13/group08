package member.repository;

import database.DatabaseConnection;
import member.model.Member;

import java.sql.*;

public class MemberRepository implements IMemberRepository {

    private Connection connection;

    public MemberRepository() {
        connection = new DatabaseConnection().getDatabaseConnection();
    }

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

            ResultSet resultSet = cs.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//            int columnCount = resultSetMetaData.getColumnCount();
//            for (int i = 1; i <= columnCount; i++) {
//                System.out.print(resultSetMetaData.getColumnName(i) + "\t");
//            }
//            System.out.println();
//
//            while (resultSet.next()) {
//                for (int i = 1; i < columnCount; i++) {
//                    System.out.print(resultSet.getObject(i) + "\t");
//                }
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
