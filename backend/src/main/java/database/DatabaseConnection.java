package database;

import java.awt.*;
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


    private Connection createConnection() {
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
        catch (IOException | SQLException | ClassNotFoundException e){
            System.err.println("Error while creating SQL connection "+e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public Connection getDatabaseConnection() {
        this.connection = createConnection();
        return this.connection;
    }


    public void closeDatabaseConnection() {
        try {
            this.connection.close();
        }
        catch (SQLException e){
            System.err.println("Error while closing SQL connection "+e.getMessage());
            System.exit(1);
        }
    }
}
