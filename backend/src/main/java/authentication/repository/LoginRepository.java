package authentication.repository;

import database.IDatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;

public class LoginRepository {
    private Connection connection;

    public LoginRepository(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getPassword(){

        return null;
    }
}
