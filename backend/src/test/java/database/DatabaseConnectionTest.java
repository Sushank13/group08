package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    private DatabaseConnection databaseConnection;
    private Connection connection;

    @BeforeEach
    public void setUp() {
        this.databaseConnection = new DatabaseConnection();
    }

    @Test
    public void checkCreateDatabaseConnection() {
        Assertions.assertDoesNotThrow(() -> this.databaseConnection.getDatabaseConnection());
    }
}
