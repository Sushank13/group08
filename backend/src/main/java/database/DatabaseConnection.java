package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection implements IDatabaseConnection {

    private Connection connection;

    private final String dbDriverProperty = "db.driver";
    private final String dbURLProperty = "db.url";
    private final String dbTypeProperty = "db.type";
    private final String dbDatabaseNameProperty = "database";
    private final String dbUsernameProperty = "username";
    private final String dbPasswordProperty = "password";
    private final String CONFIGURATION_FILE = "src/main/resources/database.properties";


    private Connection createConnection() throws Exception {
        try (InputStream configFile = new FileInputStream(CONFIGURATION_FILE)){
            Properties configProperties = new Properties();
            configProperties.load(configFile);

            Class.forName(configProperties.getProperty(dbDriverProperty));

            String dbType = configProperties.getProperty(dbTypeProperty);

            String dbDatabaseName = configProperties.getProperty(String.format("%s.%s",dbType,dbDatabaseNameProperty));
            String dbURL = String.format("%s%s",configProperties.getProperty(dbURLProperty),dbDatabaseName);
            String dbUsername = configProperties.getProperty(String.format("%s.%s",dbType,dbUsernameProperty));
            String dbPassword = configProperties.getProperty(String.format("%s.%s",dbType,dbPasswordProperty));

            return DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        }
        catch (IOException | SQLException e){
            throw new Exception(e);
        }
    }

    public Connection getDatabaseConnection()
    {
        try {
            this.connection = createConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.connection;
    }


    public void closeDatabaseConnection() throws Exception {
        try {
            this.connection.close();
        }
        catch (SQLException e){
            throw new Exception(e);
        }
    }
}
