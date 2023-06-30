package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private Connection connection;

    public Connection getDatabaseConnection() throws Exception {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("","","");
        }
        catch(ClassNotFoundException e)
        {
            throw new Exception(e);
        }

        return this.connection;
    }

}
