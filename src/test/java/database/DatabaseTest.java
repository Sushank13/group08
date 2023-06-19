package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;

public class DatabaseTest {

    private Database database;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        this.database = new Database();
        this.connection = this.database.getDatabaseConnection();
    }

    @AfterEach
    public void TestTearDown() throws Exception {
        this.database.closeDatabaseConnection();
    }

    @Test
    public void checkCreateDatabaseConnection() throws Exception {
        Assertions.assertTrue(connection.isValid(60));
    }

    @Test
    public void checkCloseDatabaseConnection() throws Exception {
        database.closeDatabaseConnection();
        Assertions.assertTrue(connection.isClosed());
    }
}
