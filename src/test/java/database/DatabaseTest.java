package database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DatabaseTest {
    @Test
    public void checkDatabaseConnection() throws Exception {
        Connection connection = new Database().getConnection();
        Assertions.assertTrue(connection.isValid(60));
    }
}
