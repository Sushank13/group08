package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private Connection connection;

    private static final String dbDriverProperty = "db.driver";
    private static final String dbURLProperty = "db.url";
    private static final String dbTypeProperty = "db.type";
    private static final String dbDatabaseNameProperty = "database";
    private static final String dbUsernameProperty = "username";
    private static final String dbPasswordProperty = "password";


    private final String CONFIGURATION_FILE = "src/main/resources/database.properties";


    public Connection getConnection() throws Exception {
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
}
