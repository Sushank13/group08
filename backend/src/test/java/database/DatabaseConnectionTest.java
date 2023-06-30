package database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DatabaseConnectionTest {
    private DatabaseConnection databaseConnection;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getDatabaseConnection();
    }

    @Test
    public void checkCreateDatabaseConnection() throws Exception {
        Assertions.assertTrue(connection.isValid(60));
    }

    @Test
    public void checkCloseDatabaseConnection() throws Exception {
        databaseConnection.closeDatabaseConnection();
        Assertions.assertTrue(connection.isClosed());
    }

}
