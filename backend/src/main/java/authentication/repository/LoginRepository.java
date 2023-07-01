package authentication.repository;

import authentication.model.Login;
import database.DatabaseConnection;
import database.IDatabaseConnection;

import java.sql.*;

public class LoginRepository {
    private Connection connection;

    public LoginRepository() throws Exception {
        connection = new DatabaseConnection().getDatabaseConnection();
    }

    public void createCredential(Login login) throws SQLException {
        CallableStatement cs = connection.prepareCall("{call AuthenticationSaveEmailAndPassword(?,?)}");
        cs.setString(1, login.getemailID());
        cs.setString(2, login.getPassword());

        ResultSet resultSet = cs.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

    }
}
