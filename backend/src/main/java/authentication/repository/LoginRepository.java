package authentication.repository;

import authentication.model.Login;
import database.DatabaseConnection;
import database.IDatabaseConnection;

import java.sql.*;

public class LoginRepository implements ILoginRepository{
    private Connection connection;

    public LoginRepository() {
        connection = new DatabaseConnection().getDatabaseConnection();
    }

    public void createCredential(Login login) {
        try {
            CallableStatement cs = connection.prepareCall("{call AuthenticationSaveEmailAndPassword(?,?)}");
            cs.setString(1, login.getemailID());
            cs.setString(2, login.getPassword());

            ResultSet resultSet = cs.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
